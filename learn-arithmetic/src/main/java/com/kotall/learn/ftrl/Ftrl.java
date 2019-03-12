package com.kotall.learn.ftrl;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ftrl {
    private static final Logger logger = LoggerFactory.getLogger(Ftrl.class);
    private Double alpha;
    private Double beta;
    private Double l1;
    private Double l2;
    private Integer bits;
    private double[] z;
    private double[] n;
    private double samplingRate = 1.0;
    private double samplingAdjust = 0.;//抽样校准
    public Set<String> features;
    private String split = ",";
    public boolean loadSuccess = false;
    public long modelFileLastModified = 0L;
    public boolean isSaveing = false;//保存中

    public Map<Integer, String> XMap1 = new HashMap<Integer, String>();

    //用户历史
    final int maxListCapacity = 10;//每个用户最多保留的创意数
    final int maxUserCapacity = 1000000;
    final int maxUserPvCapacity = 2500000;
    public LRULinkedHashMap<String, List<String>> userClickHistory = new LRULinkedHashMap(maxUserCapacity) {
    };
    public LRULinkedHashMap<String, List<String>> userPvHistory = new LRULinkedHashMap(maxUserPvCapacity) {
    };
    public LRULinkedHashMap<String, String> userAdspaceHistory = new LRULinkedHashMap(maxUserCapacity) {
    };
    final int maxBidCapacity = 2000000;
    public LRULinkedHashMap<String, Integer> bidMap = new LRULinkedHashMap(maxBidCapacity) {
    };

    //统计实时auc
    private int intervalNum = 10000;

    //加载配置
    public boolean useRedis = false;
    public String serverId = "";
    public String modelName = "A";
    public String current_model = "modelParams.txt";
    public String bak_model = "modelParams.bak";
    public String disaster_model = "modelParams.disaster";
    public String current_userHistory = "modelParams_userHistory.txt";
    public String bak_userHistory = "modelParams_userHistory.bak";
    public String disaster_userHistory = "modelParams_userHistory.disaster";
    public Integer loadRedisIncrTime = 20;//加载redis增量时间间隔
    public Integer loadRedisAllTime = 30;//加载redis全量时间间隔

    // "bid,uid,time,ip,adspace_id,hour,city_id,adsize,app_name,model,os,creative_id,advertiser_id,ad_project_id,ad_schedule_id

    public Ftrl() {
        this.alpha = 0.1;
        this.beta = 1.0;
        this.l1 = 0.1;
        this.l2 = 0.1;
        this.bits = 200 * 10000;
        this.z = new double[bits];
        this.n = new double[bits];
//		this.w = new HashMap<Integer, Double>();
//		this.y = -1;
//		this.pred = -1.0;
        this.samplingRate = 1.0;
        this.samplingAdjust = Math.log(Math.max(0.0001, samplingRate));
        defaultFeatures();
    }

    // 0.1,1,0.1,0.1,100w
    public Ftrl(Double alpha, Double beta, Double l1, Double l2,
                Integer bits, Double samplingRate, Set<String> features) {
        this.alpha = alpha;
        this.beta = beta;
        this.l1 = l1;
        this.l2 = l2;
        this.bits = bits;
        this.z = new double[bits];
        this.n = new double[bits];
        this.features = features;
        this.samplingRate = samplingRate;
        this.samplingAdjust = Math.log(Math.max(0.0001, samplingRate));
    }

    //从模型文件初始化
    public Ftrl(String modelFile, String userHistoryFile) {
        this.samplingRate = 1.;
        this.samplingAdjust = 0.;

        try {
            File file = new File(modelFile);
            if (!file.exists()) {
                logger.error("ftrl init fail: modelFile[" + modelFile + "] not exist!");
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("feature=")) {
                    this.features = new HashSet<String>();
                    String featureStr = line.split("feature=")[1].trim();
                    for (String feature : featureStr.split(",")) {
                        this.features.add(feature);
                    }
                }
                if (line.startsWith("samplingRate=")) {
                    double sr = Double.valueOf(line.split("samplingRate=")[1].trim());
                    if (sr > 0 && sr <= 1.0) {
                        samplingRate = sr;
                        samplingAdjust = Math.log(Math.max(0.0001, samplingRate));
                    }
                }
                if (line.startsWith("l1=")) {
                    l1 = Double.valueOf(line.split("l1=")[1].trim());
                }
                if (line.startsWith("alpha=")) {
                    alpha = Double.valueOf(line.split("alpha=")[1].trim());
                }
                if (line.startsWith("l2=")) {
                    l2 = Double.valueOf(line.split("l2=")[1].trim());
                }
                if (line.startsWith("beta=")) {
                    beta = Double.valueOf(line.split("beta=")[1].trim());
                }
                if (line.startsWith("z=")) {
                    String zs = line.split("z=")[1].trim().substring(1, line.split("z=")[1].trim().length() - 1);
                    String[] zArr = zs.split(split);
                    bits = zArr.length;
                    z = new double[bits];
                    for (int i = 0; i < bits; i++) {
                        String v = zArr[i].trim();
                        if ("".equals(v) || "NaN".equals(v)) {
                            logger.error("ftrl init fail: z[" + i + "]=" + v);
                            return;
                        }
                        z[i] = Double.valueOf(v);
                    }
                }

                if (line.startsWith("n=")) {
                    String ns = line.split("n=")[1].trim().substring(1, line.split("n=")[1].trim().length() - 1);
                    String[] nArr = ns.split(split);
                    bits = nArr.length;
                    n = new double[bits];
                    for (int i = 0; i < bits; i++) {
                        String v = nArr[i].trim();
                        if ("".equals(v) || "NaN".equals(v) || Double.valueOf(v) < 0) {
                            logger.error("ftrl init fail: z[" + i + "]=" + v);
                            return;
                        }
                        n[i] = Double.valueOf(v);
                    }
                }
            }
            if (z == null || n == null) {
                logger.error("ftrl init fail: n or z is null!");
                return;
            }

            File userFile = new File(userHistoryFile);
            if (userFile.exists()) {
                reader = new BufferedReader(new FileReader(userFile));
                while ((line = reader.readLine()) != null) {
                    String[] arr = line.split(split);
                    if (arr.length == 3 && "1".equals(arr[0])) {//点击
                        putUserHistory(arr[1], arr[2].split("\\|"), userClickHistory, maxListCapacity);
                    } else if (arr.length == 3 && "0".equals(arr[0])) {//pv
                        putUserHistory(arr[1], arr[2].split("\\|"), userPvHistory, maxListCapacity);
                    } else if (arr.length == 3 && "2".equals(arr[0])) {//广告位
                        userAdspaceHistory.put(arr[1], arr[2]);
                    }
                }
            }
            if (features == null) {
                defaultFeatures();
            }
            loadSuccess = true;
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    //读取加载配置
    private void readConfigFile(String configName) {
        List<String> configList = readFile(configName);
        if (configList != null) {
            for (String config : configList) {
                if (config.startsWith("serverId=")) {
                    serverId = config.split("serverId=")[1].trim();
                }
                if (config.startsWith("useRedis=")) {
                    useRedis = Boolean.valueOf(config.split("useRedis=")[1].trim());
                }
                if (config.startsWith("modelName=")) {
                    modelName = config.split("modelName=")[1].trim();
                }
                if (config.startsWith("current_model=")) {
                    current_model = config.split("current_model=")[1].trim();
                }
                if (config.startsWith("bak_model=")) {
                    bak_model = config.split("bak_model=")[1].trim();
                }
                if (config.startsWith("disaster_model=")) {
                    disaster_model = config.split("disaster_model=")[1].trim();
                }
                if (config.startsWith("current_userHistory=")) {
                    current_userHistory = config.split("current_userHistory=")[1].trim();
                }
                if (config.startsWith("bak_userHistory=")) {
                    bak_userHistory = config.split("bak_userHistory=")[1].trim();
                }
                if (config.startsWith("disaster_userHistory=")) {
                    disaster_userHistory = config.split("disaster_userHistory=")[1].trim();
                }
                if (config.startsWith("loadRedisIncrTime=")) {
                    loadRedisIncrTime = Integer.valueOf(config.split("loadRedisIncrTime=")[1].trim());
                }
                if (config.startsWith("loadRedisAllTime=")) {
                    loadRedisAllTime = Integer.valueOf(config.split("loadRedisAllTime=")[1].trim());
                }
            }
        }
        //注册是否使用redis
        registerUseRedis();
    }

    //初始化方法
    public static Ftrl initFtrl(Ftrl ftrl, String modelPath, String modelConfig) {
        if (ftrl == null) {
            ftrl = new Ftrl();
        }
        ftrl.readConfigFile(modelPath + "/" + modelConfig);
        return loadFtrl(ftrl, modelPath, modelConfig);
    }

    //加载模型
    public static Ftrl loadFtrl(Ftrl ftrl, String modelPath, String modelConfig) {
        if (ftrl == null) {
            ftrl = new Ftrl();
        }
        //redis加载
        boolean loadSuccess = ftrl.loadAllFromRedis();
        if (loadSuccess) {
            return ftrl;
        }

        //文件加载
        if (ftrl.isSaveing) {
            return ftrl;
        }
        boolean newFtrlSuccess = false;
        long time0 = System.currentTimeMillis();
        String bidMapFileName = "bids.txt";
        String loadInfo = "config=" + modelConfig + " ";
        File modelFile = new File(modelPath + "/" + ftrl.current_model);
        if ((modelFile.exists() && modelFile.lastModified() != ftrl.modelFileLastModified)) {
            String lastModifiedStr = "(" + modelFile.lastModified() + "!=" + ftrl.modelFileLastModified + ")";
            Ftrl newFtrl = new Ftrl(modelPath + "/" + ftrl.current_model, modelPath + "/" + ftrl.current_userHistory);
            if (newFtrl.loadSuccess) {
                ftrl = newFtrl;
                ftrl.loadBidMap(modelPath + "/" + bidMapFileName);
                ftrl.modelFileLastModified = modelFile.lastModified();
                ftrl.readConfigFile(modelPath + "/" + modelConfig);
                loadInfo = ftrl.current_model + lastModifiedStr + "成功";
                newFtrlSuccess = true;
            }
        }
        if (ftrl.modelFileLastModified == 0L) {
            Ftrl bakFtrl = new Ftrl(modelPath + "/" + ftrl.bak_model, modelPath + "/" + ftrl.bak_userHistory);
            if (bakFtrl.loadSuccess) {
                ftrl = bakFtrl;
                ftrl.loadBidMap(modelPath + "/" + bidMapFileName);
                loadInfo = ftrl.current_model + "失败,备份模型" + ftrl.bak_model + "加载成功";
            } else {
                Ftrl disasterFtrl = new Ftrl(modelPath + "/" + ftrl.disaster_model, modelPath + "/" + ftrl.disaster_userHistory);
                if (disasterFtrl.loadSuccess) {
                    ftrl = disasterFtrl;
                    ftrl.loadBidMap(modelPath + "/" + bidMapFileName);
                    loadInfo = ftrl.current_model + "失败,备份加载" + ftrl.bak_model + "失败,容灾模型" + ftrl.disaster_model + "加载成功";
                } else {
                    //用空对象
                    ftrl = new Ftrl();
                    ftrl.loadBidMap(modelPath + "/" + bidMapFileName);
                    loadInfo = "失败,备份加载失败,容灾模型加载失败！！";
                    logger.error("加载ctr模型:" + modelPath + " " + loadInfo + "  loadTime=" + (System.currentTimeMillis() - time0) + "ms");
                }
            }
            ftrl.readConfigFile(modelPath + "/" + modelConfig);
        } else {
            if (modelFile.lastModified() == ftrl.modelFileLastModified) {
                loadInfo = ftrl.current_model + "不需要重新加载(" + ftrl.modelFileLastModified + ")";
            } else {
                if (!newFtrlSuccess) {
                    loadInfo = ftrl.current_model + "失败,沿用旧模型";
                }
            }
        }
        logger.info("从文件加载ctr模型:" + modelPath + " " + loadInfo + "  loadTime=" + (System.currentTimeMillis() - time0) + "ms");
        return ftrl;
    }

    //默认配置
    private void defaultFeatures() {
        String fieldStr = "ip,adspace_id,hour,city_id,adsize,app_name,model,os,creative_id,advertiser_id";
        String[] fieldArr = fieldStr.split(",");
        this.features = new HashSet<>();
        for (String field : fieldArr) {
            features.add(field);
        }
    }

    //读取bid文件
    public void loadBidMap(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String bid = line;
                Integer value = 0;
                String[] arr = line.split(",");
                if (arr.length == 2) {
                    bid = arr[0];
                    value = Integer.valueOf(arr[1]);
                }
                bidMap.put(bid, value);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    //保存bid文件
    public void saveBidMap(String fileName) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : bidMap.getAll().entrySet()) {
            result.add(entry.getKey() + "," + entry.getValue());
        }
        writeFile(fileName, result);
    }

    //保存用户历史
    public Integer saveUserHistory(String fileName) {
        List<String> result = new ArrayList<>();
        String[] typeArr = {"1", "0", "2"};//点击类型1,pv类型0,广告位类型2
        if (userClickHistory.size() > 0) {
            for (Map.Entry<String, List<String>> entry : userClickHistory.getAll().entrySet()) {
                String content = implode(entry.getValue(), "|");
                if (isEmpty(content)) {
                    continue;
                }
                result.add(typeArr[0] + split + entry.getKey() + split + content);
            }
        }
        if (userPvHistory.size() > 0) {
            for (Map.Entry<String, List<String>> entry : userPvHistory.getAll().entrySet()) {
                String content = implode(entry.getValue(), "|");
                if (isEmpty(content)) {
                    continue;
                }
                result.add(typeArr[1] + split + entry.getKey() + split + content);
            }
        }
        if (userAdspaceHistory.size() > 0) {
            for (Map.Entry<String, String> entry : userAdspaceHistory.getAll().entrySet()) {
                result.add(typeArr[2] + split + entry.getKey() + split + entry.getValue());
            }
        }
        if (result.size() > 10000) {
            writeFile(fileName, result);
            return result.size();
        }
        return -1;
    }

    //保存模型文件
    public void saveModel(String modelFileName) {
        List<String> list = new ArrayList<>();
        list.add("feature=" + implode(features, ",") + "\n");
        list.add("samplingRate=" + samplingRate + "\n");
        list.add("alpha=" + alpha + "\n");
        list.add("beta=" + beta + "\n");
        list.add("l1=" + l1 + "\n");
        list.add("l2=" + l2 + "\n");
        StringBuffer z_sb = new StringBuffer();
        StringBuffer n_sb = new StringBuffer();
        for (int i = 0; i < bits; i++) {
            if (i > 0) {
                z_sb.append(split);
                n_sb.append(split);
            }
            z_sb.append(short6D(z[i]));
            n_sb.append(shortD(n[i]));
        }
        list.add("z=[" + z_sb.toString() + "]\n");
        list.add("n=[" + n_sb.toString() + "]\n");

        writeFile(modelFileName, list);
        String dateStr = FastDateFormat.getInstance("yyyyMMdd").format(new Date());
        writeFile(modelFileName.replace("txt", dateStr), list);
    }

    //=========新增pvClick

    //实时更新
//	private long pvTotal = 0;
//	private double cpAuc = -1;
//	private double pvAuc = -1;
    public UpdateEntity putRealTimePvClick(Map<String, String> dataMap) {
        String isclick = dataMap.get("isclick");
        String bid = dataMap.get("bid");
        String uid = dataMap.get("uid");
        String full_adspace_id = dataMap.get("adspace_id");
        String ap_idx = full_adspace_id.contains("_mm_") ? full_adspace_id.substring(full_adspace_id.lastIndexOf("_mm_") + 4) : "";
        Long nowTime = System.currentTimeMillis() / 1000;

        if (isEmpty(bid)) {
            return null;
        }

        //bid去重
        String uniqueId = bid + "_" + ap_idx + "_" + isclick;
        if (bidMap.containsKey(uniqueId)) {
            return null;
        }
        bidMap.put(uniqueId, 0);


        String cp = dataMap.get("cp");
//		if(cp!=null&&!"".equals(cp)){
//			int bidPos = (int) Math.floor(Double.valueOf(cp)/100*intervalNum);
//			bidPos = Math.min(intervalNum-1,bidPos);
//			bidClickIntervalSum[bidPos] += "1".equals(isclick)?1:0;
//			bidPvIntervalSum[bidPos] += "1".equals(isclick)?0:1;
//		}
        //pvTotal += "1".equals(isclick)?0:1;

        UpdateEntity ue = fitAndPredict(dataMap);
        double pred = ue.adjustPred;

        ue.featureLog = bid + "|" + isclick + "|" + cp + "|" + short6D(pred) + "|"
                + uid + "|" + uniqueId + "||" + nowTime + "|" + ue.featureLog;
        //负样本抽样
        double rand = Math.random();
        if ("0".equals(isclick) && rand > samplingRate) {
            return ue;
        }

        //是否反向更新
        boolean isReverseUpdate = false;
        if ("1".equals(isclick) && bidMap.containsKey(bid + "_" + ap_idx + "_0") && bidMap.get(bid + "_" + ap_idx + "_0") == 1) {
            isReverseUpdate = true;
        }
        if (isReverseUpdate) {
            reverseClickUpdate(ue);
        } else {
            update(ue);
        }
        bidMap.put(uniqueId, 1);

//		int pos1 = (int) Math.floor(pred*intervalNum);
//		pos1 = Math.min(intervalNum-1,pos1);
//		pcClickIntervalSum[pos1] += "1".equals(isclick)?1:0;
//		pcPvIntervalSum[pos1] += "1".equals(isclick)?0:1;

//		int interval = 1000;
//		if(pvTotal%interval==0){
//			cpAuc = getAuc(bidClickIntervalSum,bidPvIntervalSum);
//			pvAuc = getAuc(pcClickIntervalSum,pcPvIntervalSum);
//		}


        return ue;
    }

    //=========模型predict和update
    public UpdateEntity fitAndPredict(Map<String, String> dataMap) {
        int y = dataMap.containsKey("isclick") ? Integer.valueOf(dataMap.get("isclick")) : -1;
        Map<String, String> featureMap = featureFilter(dataMap);
        int[] X = new int[featureMap.size() + 1];// X[0]=1 w_0截距项
        int i = 1;
        StringBuffer sb = new StringBuffer();
        XMap1.clear();
        for (Map.Entry<String, String> entry : featureMap.entrySet()) {
            X[i] = Math.abs((entry.getKey() + "_" + entry.getValue()).hashCode() % this.bits);
            sb.append((i > 1 ? "|" : "") + entry.getKey() + "#" + entry.getValue());
            XMap1.put(X[i], entry.getKey());
            i++;
        }

        double w = predictWeight(X);
        double up = shortD(1 / (1 + Math.exp(-Math.max(Math.min(w, 35), -35))), 8);
        double ap = shortD(1 / (1 + Math.exp(-Math.max(Math.min(w + samplingAdjust, 35), -35))), 8);
        UpdateEntity ue = new UpdateEntity(y, up, ap, X, sb.toString());
//		//TODO
//		String debugLog = getDebugLog(ue);
//		ue.featureLog += debugLog;
        return ue;
    }

    //获取特征权重和
    public double predictWeight(int[] X) {
        double w_dot_x = 0.0;
        for (int i : X) {
            w_dot_x += ftrlWeight(i);
        }
        return w_dot_x;
    }

    //获取特征权值
    private double ftrlWeight(int i) {
        if (Math.abs(this.z[i]) > this.l1) {
            return (this.sgn(this.z[i]) * this.l1 - this.z[i]) / (((this.beta + Math.sqrt(this.n[i])) / this.alpha) + this.l2);
        }
        return 0.;
    }


    // 更新z和n
    public void update(UpdateEntity ue) {
        double pred = ue.updatePred;
        int y = ue.label;
        if (pred >= 0 && pred <= 1 && y >= 0 && y <= 1) {
            double g = (pred - y);
            for (int i : ue.X) {
                double sigma = (1.0 / this.alpha) * (Math.sqrt(this.n[i] + g * g) - Math.sqrt(this.n[i]));
                this.z[i] += g - sigma * ftrlWeight(i);
                this.n[i] += g * g;// qi=ni=求和(gi^2)
                modifyIs.put(i, 0);
            }
        }
    }

    //反向点击更新
    public void reverseClickUpdate(UpdateEntity ue) {
        double p = ue.updatePred;
        int y = ue.label;
        if (p >= 0 && p <= 1 && y >= 0 && y <= 1) {
            for (int i : ue.X) {
                double sigma = (1.0 / this.alpha) * (Math.sqrt(n[i] + 1 - 2 * p) - Math.sqrt(n[i]));
                this.z[i] += -1 - sigma * ftrlWeight(i);
                this.n[i] += 1 - 2 * p;

                if (this.n[i] <= 0) {
                    this.z[i] = 0;
                    this.n[i] = 0;
                }
                modifyIs.put(i, 0);
            }
        }
    }


    public double logloss(double pred, int y) {
        double predicted = Math.max(Math.min(pred, 1.0 - 10e-15), 10e-15);
        return y == 1.0 ? -Math.log(predicted) : -Math.log(1.0 - predicted);
    }


    //参数更新实体
    public class UpdateEntity {
        public int label;
        public double updatePred;//模型预估值
        public double adjustPred;//抽样率修正后预估值
        public int[] X;
        public String featureLog;

        public UpdateEntity(int y, double up, double ap, int[] x, String log) {
            label = y;
            updatePred = up;
            adjustPred = ap;
            X = x;
            featureLog = log;
        }
    }
//=====================特征处理================

    public Map<String, String> featureFilter(Map<String, String> dataMap) {
        Map<String, String> featureMap = new HashMap<String, String>();
        String bid = dataMap.containsKey("bid") ? dataMap.get("bid") : "";
        int isclick = dataMap.containsKey("isclick") ? Integer.valueOf(dataMap.get("isclick")) : -1;
        String adspace_id = dataMap.containsKey("adspace_id") ? dataMap.get("adspace_id") : "";
        String ap_idx = adspace_id.contains("_mm_") ? adspace_id.substring(adspace_id.lastIndexOf("_mm_") + 4) : "";
        String ad_schedule_id = dataMap.containsKey("ad_schedule_id") ? dataMap.get("ad_schedule_id") : "";
        String creative_id = dataMap.containsKey("creative_id") ? dataMap.get("creative_id") : "";
        String request_time = dataMap.containsKey("request_time") ? dataMap.get("request_time") : "";
        String bid_time = dataMap.containsKey("bid_time") ? dataMap.get("bid_time") : "";
        String ip = dataMap.containsKey("ip") && dataMap.get("ip") != null ? dataMap.get("ip") : "";
        String uid = dataMap.containsKey("uid") ? dataMap.get("uid") : "";
        String rtb_price = dataMap.containsKey("rtb_price") ? dataMap.get("rtb_price") : "";

        for (String field : features) {
            //处理缺损值
            String value = dataMap.containsKey(field) && !"\\N".equals(dataMap.get(field)) ? dataMap.get(field) : "";
			/*if("adspace_id".equals(field)&&value.contains("_mm_")){
				value = value.substring(0,value.lastIndexOf("_mm_")+3);
				adspace_id = value;
			}*/
            if ("app_name".equals(field)) {
                value = value.toLowerCase();
            }
            featureMap.put(field, value);
        }
        if (features.contains("city_id")) {
            featureMap.put("city_id", "0".equals(featureMap.get("city_id")) ? "" : featureMap.get("city_id"));
        }
        if (features.contains("adsize")) {
            featureMap.put("adsize", featureMap.get("adsize").replace("*", "x"));
        }
        if (features.contains("hour") && !dataMap.containsKey("hour")) {
            String hour = dataMap.containsKey("bid_time") ? getTimeHour(request_time) : "";
            hour = "".equals(hour) ? getTimeHour(bid_time) : "";
            featureMap.put("hour", hour);
        }
        String removeLastCreativeId = "";
        if (isclick == 1 && bidMap.containsKey(bid + "_" + ap_idx + "_0")) {
            removeLastCreativeId = creative_id;
        }
        featureFilterForUserHistory(isclick, uid, creative_id, adspace_id, featureMap, removeLastCreativeId);

        //ip保留前三位
        if (features.contains("ipPart")) {
            String[] ipArr = ip.split("\\.");
            String newIp = "other";
            if (ipArr.length == 4) {
                newIp = "";
                for (int j = 0; j < 3; j++) {
                    newIp += ipArr[j] + ".";
                }
                newIp += "*";
            }
            featureMap.put("ipPart", newIp);
        }
//		if (features.contains("rtb_price")) {
//			featureMap.put("rtb_price", shortD(Double.valueOf(rtb_price),0));
//		}
        //小时

        return featureMap;
    }

    //用户历史行为特征
    Map<String, Integer> statUserHistoryMap = new HashMap<>();

    private Map<String, String> featureFilterForUserHistory(int isclick, String uid, String creative_id, String adspace_id, Map<String, String> featureMap, String removeLastCreativeId) {
        //用户行为历史
        List<String> userClickList = userClickHistory.containsKey(uid) ? userClickHistory.get(uid) : new ArrayList<String>();
        List<String> userPvList = userPvHistory.containsKey(uid) ? userPvHistory.get(uid) : new ArrayList<String>();
        String history_click_1 = "";
        String history_noclick_1 = "";
        if (features.contains("user_history")) {
            String isPv = "0";
            String otherIsPv = "0";
            int pvSize = userPvList.size();
            for (int i = 0; i < pvSize; i++) {
                String cidPv = userPvList.get(i);
                if (i == pvSize - 1 && removeLastCreativeId.equals(creative_id)) {
                    continue;
                }
                if (creative_id.equals(cidPv)) {
                    isPv = "1";// 看过本创意
                } else {
                    otherIsPv = "3";// 看过其他创意
                }
                history_noclick_1 = cidPv;
            }
            for (String cidClick : userClickList) {
                if (creative_id.equals(cidClick)) {
                    isPv = "2";// 点过
                } else {
                    otherIsPv = "4";// 点过
                }
                history_click_1 = cidClick;
            }
            String user_history = isPv + otherIsPv;
            featureMap.put("user_history", user_history);

            String click_key = "c_" + user_history;
            String pv_key = "p_" + user_history;
            statUserHistoryMap.put(click_key, statUserHistoryMap.containsKey(click_key) ? statUserHistoryMap.get(click_key) + isclick : isclick);
            statUserHistoryMap.put(pv_key, statUserHistoryMap.containsKey(pv_key) ? statUserHistoryMap.get(pv_key) + 1 : 1);
        }

        if (features.contains("history_click_1")) {
            featureMap.put("history_click_1", history_click_1);
        }
        if (features.contains("history_noclick_1")) {
            featureMap.put("history_noclick_1", history_noclick_1);
        }
        if (features.contains("history_adspace")) {
            String h_adspace = userAdspaceHistory.containsKey(uid) ? userAdspaceHistory.get(uid) : "";
            featureMap.put("history_adspace", h_adspace);
        }

        //保存新行为
        if (isclick >= 0 && !isEmpty(uid)) {
            if (isclick == 0) {
                userPvList.add(creative_id);
                if (userPvList.size() > maxListCapacity) {
                    userPvList.remove(0);
                }
                userPvHistory.put(uid, userPvList);
                modifyUHPs.add(uid);
            }
            if (isclick == 1) {
                userClickList.add(creative_id);
                if (userClickList.size() > maxListCapacity) {
                    userClickList.remove(0);
                }
                userClickHistory.put(uid, userClickList);
                userAdspaceHistory.put(uid, adspace_id);
                modifyUHCs.add(uid);
            }
        }
        return featureMap;
    }

    public String getDebugLog(UpdateEntity ue) {
        double w_dot_x = 0.0;
        StringBuffer sb = new StringBuffer();
        for (int i : ue.X) {
            double w = ftrlWeight(i);
            w_dot_x += w;
            sb.append("[" + i + "]=" + XMap1.get(i) + ":z=" + shortD(z[i]) + ",n=" + shortD(n[i]) + ",w=" + shortD(w) + ",");
        }
        double p = shortD(1 / (1 + Math.exp(-Math.max(Math.min(w_dot_x, 35), -35))), 8);
        sb.append(" wTotal=" + shortD(w_dot_x) + ",pred=" + shortD(p));
        return sb.toString();
    }

    //=====================redis 同步
    private String namespace = "CTRMODEL-0";
    private int hsetBatchSize = 10000;
    private long load_sign_incr = 0;
    private long load_sign_all = 0;
    private Map<Integer, Integer> modifyIs = new ConcurrentHashMap<>();
    private List<String> modifyUHPs = Collections.synchronizedList(new ArrayList<>());
    private List<String> modifyUHCs = Collections.synchronizedList(new ArrayList<>());
    private Map<String, Long> timeTotalMap = new HashMap<>();
    private Map<String, Long> timeNumMap = new HashMap<>();

    //注册本服务器id是否使用redis加载
    public void registerUseRedis() {
        DataUtil.hset(true, namespace, "load_sign", "use_redis_" + serverId, String.valueOf(useRedis));
        DataUtil.hset(true, namespace, "load_sign", "model_" + serverId, modelName);
    }

    //加载全量
    public boolean loadAllFromRedis() {
        try {
            if (!useRedis) {
                //logger.info("ftrl loadAllFromRedis fail: useRedis="+useRedis);
                return false;
            }
            String redis_sign_all = JedisUtil.hget(namespace, "load_sign", "save_all_" + modelName + "ftrl");
            if (redis_sign_all == null || load_sign_all >= Long.valueOf(redis_sign_all)) {
                //logger.info("ftrl loadAllFromRedis fail: redis_sign_all is null or "+load_sign_all+">="+redis_sign_all);
                return false;
            }

            long time0 = System.currentTimeMillis();
            Map<String, String> superParamMap = DataUtil.hgetAll(true, namespace, modelName + "ftrl_super_param");
            if (superParamMap == null) {
                logger.info("ftrl loadAllFromRedis fail: field[" + modelName + "ftrl_super_param] not exist!");
                return false;
            }
            int new_bits = Integer.valueOf(superParamMap.get("bits"));
            if (new_bits != bits) {
                bits = new_bits;
                z = new double[bits];
                n = new double[bits];
            }
            alpha = Double.valueOf(superParamMap.get("alpha"));
            beta = Double.valueOf(superParamMap.get("beta"));
            l1 = Double.valueOf(superParamMap.get("l1"));
            l2 = Double.valueOf(superParamMap.get("l2"));
            String logStr = loadFromRedis(modelName + "ftrl_z_all", modelName + "ftrl_n_all", modelName + "ftrl_up_all",
                    modelName + "ftrl_uc_all", modelName + "ftrl_us_all");
            if (z.length != n.length || z.length != bits) {
                logger.info("ftrl loadAllFromRedis fail: z.length[" + z.length + "] != n.length[" + n.length + "] != bits[" + bits + "]");
                return false;
            }

            load_sign_all = Long.valueOf(redis_sign_all);
            DataUtil.hset(true, namespace, "load_sign", "load_all_" + serverId, redis_sign_all);

            statExecuteTime("loadAllFromRedis" + modelName + " " + logStr, time0, 10);
            return true;
        } catch (Exception e) {
            logger.error("ftrl loadAllFromRedis Exception", e);
        } finally {
            return false;
        }
    }

    //从redis加载
    public String loadFromRedis(String field_z, String field_n, String field_up, String field_uc, String field_us) {
        long time0 = System.currentTimeMillis();
        Map<String, String> zMap = DataUtil.hgetAll(true, namespace, field_z);
        Map<String, String> nMap = DataUtil.hgetAll(true, namespace, field_n);
        long time1 = System.currentTimeMillis();
        StringBuffer logSB = new StringBuffer();
        if (zMap != null && nMap != null && zMap.size() > 0 && nMap.size() > 0) {
            for (Map.Entry<String, String> entry : zMap.entrySet()) {
                z[Integer.valueOf(entry.getKey())] = Double.valueOf(entry.getValue());
            }
            for (Map.Entry<String, String> entry : nMap.entrySet()) {
                n[Integer.valueOf(entry.getKey())] = Double.valueOf(entry.getValue());
            }
            logSB.append("z=" + zMap.size() + " n=" + nMap.size());
        }
        long time2 = System.currentTimeMillis();
        Map<String, String> upMap = DataUtil.hgetAll(true, namespace, field_up);
        Map<String, String> ucMap = DataUtil.hgetAll(true, namespace, field_uc);
        Map<String, String> usMap = DataUtil.hgetAll(true, namespace, field_us);
        long time3 = System.currentTimeMillis();
        if (upMap != null) {
            for (int i = 0; i < upMap.size(); i++) {
                String str = upMap.get(Integer.toString(i));
                if (isEmpty(str)) {
                    continue;
                }
                String[] arr = str.split(" ");
                if (arr.length != 2) {
                    continue;
                }
                List<String> creativeIds = arrToList(arr[1].split(","));
                userPvHistory.put(arr[0], creativeIds);
            }
            logSB.append(" up=" + upMap.size());
        }
        if (ucMap != null) {
            for (int i = 0; i < ucMap.size(); i++) {
                String str = ucMap.get(Integer.toString(i));
                if (isEmpty(str)) {
                    continue;
                }
                String[] arr = str.split(" ");
                if (arr.length != 2) {
                    continue;
                }
                List<String> creativeIds = arrToList(arr[1].split(","));
                userClickHistory.put(arr[0], creativeIds);
            }
            logSB.append(" uc=" + ucMap.size());
        }
        if (usMap != null) {
            for (int i = 0; i < usMap.size(); i++) {
                String str = usMap.get(Integer.toString(i));
                if (isEmpty(str)) {
                    continue;
                }
                String[] arr = str.split(" ");
                if (arr.length != 2) {
                    continue;
                }
                userAdspaceHistory.put(arr[0], arr[1]);
            }
            logSB.append(" us=" + usMap.size());
        }
        long time4 = System.currentTimeMillis();
        logSB.append(" time:" + (time1 - time0) + " " + (time2 - time1) + " " + (time3 - time2) + " " + (time4 - time3) + " =" + (time4 - time0));
        //logger.info("ftrl loadFromRedis size:"+logSB.toString()+" time:"+(time1-time0)+" "+(time2-time1)+" "+(time3-time2)+" "+(time4-time3)+" ="+(time4-time0));
        return logSB.toString();
    }

    //统计执行时间
    private void statExecuteTime(String timeName, long time0, int printNum) {
        long time1 = System.currentTimeMillis();
        long timeTotal = timeTotalMap.containsKey(timeName) ? timeTotalMap.get(timeName) : 0;
        long timeNum = timeNumMap.containsKey(timeName) ? timeNumMap.get(timeName) : 0;
        timeTotal += time1 - time0;
        timeNum++;
        timeTotalMap.put(timeName, timeTotal);
        timeNumMap.put(timeName, timeNum);
        if (timeNum % printNum == 0 || timeNum < 5) {
            logger.info("ftrl " + timeName + " time[" + timeNum + "] : " + (timeTotal * 1.0 / timeNum) + "ms");
            timeTotalMap.clear();
            timeNumMap.clear();
        }
    }

    private static void putUserHistory(String uid, String[] creativeIds, LRULinkedHashMap<String, List<String>> userHistory, Integer maxListCapacity) {
        List<String> creativeList = userHistory.containsKey(uid) ? userHistory.get(uid) : new ArrayList<>();
        for (String creative : creativeIds) {
            if ("1".equals(creative)) {
                continue;
            }
            creativeList.add(creative);
        }
        if (creativeList.size() == 0) {
            return;
        }
        if (creativeList.size() > maxListCapacity) {
            creativeList.subList(0, creativeList.size() - maxListCapacity).clear();
        }
        userHistory.put(uid, creativeList);
    }


    public static List<String> readFile(String fileName) {
        try {
            List<String> list = new ArrayList<>();
            File file = new File(fileName);
            if (!file.exists()) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 写文件
    public static void writeFile(String fileName, List<String> list) {
        BufferedWriter writer;
        try {
            File file = new File(fileName);
            File tmpFile = new File(fileName + ".tmp");
            writer = new BufferedWriter(new FileWriter(tmpFile));
            for (String str : list) {
                writer.write(str);
                writer.newLine();
            }
            writer.flush();
            writer.close();
            tmpFile.renameTo(file);
            logger.info("ftrl writeFile:" + fileName + " rows:" + list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String shortD(double d) {
        return String.format("%.4f", d);
    }

    public static String short6D(double d) {
        return String.format("%.6f", d);
    }

    public static String short8D(double d) {
        return String.format("%.8f", d);
    }

    private static Double shortD(double d, int n) {
        return Double.valueOf(String.format("%." + n + "f", d));
    }

    private static double tanh(double w) {
        return (Math.exp(-w) - Math.exp(-w)) / (Math.exp(-w) + Math.exp(-w));
    }

    private static double LR(double w) {
        return 1 / (1 + Math.exp(-w));
    }

    private double sigmoid(double w) {
        return 1 / (1 + Math.exp(-Math.max(Math.min(w, 35), -35)));
    }

    private Integer sgn(Double x) {
        if (x < 0)
            return -1;
        return 1;
    }

    private boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    private List<String> arrToList(String[] arr) {
        List<String> list = new ArrayList<>();
        for (String str : arr) {
            list.add(str);
        }
        return list;
    }

    public static String implode(List<String> list, String split) {
        if (list == null || list.size() == 0) return null;
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String str : list) {
            if (i > 0) {
                sb.append(split);
            }
            sb.append(str);
            i++;
        }
        return sb.toString();
    }

    public static String implode(Set<String> list, String split) {
        if (list == null || list.size() == 0) return null;
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String str : list) {
            if (i > 0) {
                sb.append(split);
            }
            sb.append(str);
            i++;
        }
        return sb.toString();
    }

    private static String getTimeHour(String time) {
        if ("".equals(time) || "\\N".equals(time)) {
            return "";
        }
        return String.valueOf(Integer.valueOf(time.substring(11, 13)));//2018-01-23 15:00:00
    }

    public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        private static final long serialVersionUID = -952299094512767664L;
        private final int maxCapacity;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private final Lock lock = new ReentrantLock();

        public LRULinkedHashMap(int maxCapacity) {
            super((int) (maxCapacity / DEFAULT_LOAD_FACTOR) + 1, DEFAULT_LOAD_FACTOR, true);
            this.maxCapacity = maxCapacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxCapacity;
        }

        @Override
        public V get(Object key) {
            try {
                lock.lock();
                return super.get(key);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public V put(K key, V value) {
            try {
                lock.lock();
                return super.put(key, value);
            } finally {
                lock.unlock();
            }
        }

        public int size(Object key) {
            try {
                lock.lock();
                return super.size();
            } finally {
                lock.unlock();
            }
        }

        public Map<K, V> getAll() {
            try {
                lock.lock();
                LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
                for (Map.Entry<K, V> entry : super.entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
                return map;
            } finally {
                lock.unlock();
            }
        }
    }
}