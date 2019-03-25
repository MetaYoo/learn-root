package com.kotall.learn.ml;

import java.io.*;
import java.util.HashSet;

/**
 * desc:
 *  https://blog.csdn.net/luoyexuge/article/details/53837063
 * @author zpwang
 * @create 2019/3/25 18:00
 * @since 1.0.0
 */
public class FTRLModel {
    private static final String tagid = "TAGID";
    private static final String hour = "HOUR";
    private static final String province = "PROVINCE";
    private static final String meidia = "MEDIA";
    private static final String[] FEATURE = {tagid, hour, province, meidia};

    /**
     * learning rate
     */
    private double alpha = 0.1;
    /**
     * smoothing rate
      */
    private double belta = 1;
    private double L1 = 1;
    private double L2 = 1;
    /**
     * number of weights
     */
    private int D = 1000000;

    private double[] N;
    private double[] Z;
    private double[] W;

    /**
     * z更新权重用到的,存放梯度累加的n，最后的w
     * @param D
     */
    public FTRLModel(int D) {
        this.D = D;
        // 存放累加的w
        this.N = new double[D];
        // sum of the gradient^2
        this.Z = new double[D];
        // 模型最后的参数
        this.W = new double[D];
    }

    /**
     * @param set   : hash trick
     * @param label : click=1,unclick=0
     */
    public void train(HashSet<Integer> set, int label) {
        Double p = 0.0;
        for (Integer i : set) {
            int sign = Z[i] < 0 ? -1 : 1;
            if (Math.abs(Z[i]) <= L1) {
                W[i] = 0.0;
            } else {
                W[i] = (sign * L1 - Z[i]) / ((belta + Math.sqrt(N[i])) / alpha + L2);
            }
            p += W[i];
        }

        // predict
        p = 1 / (1 + Math.exp(-p));

        // update
        Double g = p - label;
        for (Integer i : set) {
            Double sigma = (Math.sqrt(N[i] + g * g) - Math.sqrt(N[i])) / alpha;
            Z[i] += g - sigma * W[i];
            N[i] += g * g;
        }
        set.clear();
    }

    public double predict(HashSet<Integer> set) {
        Double p = 0.0;
        for (Integer i : set) {
            p += W[i];
        }
        // predict
        p = 1 / (1 + Math.exp(-p));
        return p;
    }

    public double logloss(double p, int label) {
        if (label == 1) {
            p = -Math.log(p);
        } else {
            p = -Math.log(1.0 - p);
        }
        return p;
    }

    public static void main(String args[]) {
        Double p = 0.0;

        // repeat train times
        int epoch = 1;

        FTRLModel ftrl = new FTRLModel(100000);

        String trPath = "D:\\tmp\\ftrl\\train\\part-r-00298";
        String tePath = "D:\\tmp\\ftrl\\test\\part-r-00299";
        String submissionPath = "D:\\tmp\\ftrl\\result\\result.csv";

        BufferedReader br;
        String str = null;
        // train model
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(trPath), "UTF-8"));
            str = br.readLine();
            String name[] = str.split(",");
            String value[] = null;
            HashSet<Integer> set = new HashSet<>();
            for (int epo = 0; epo < epoch; epo++) {
                while ((str = br.readLine()) != null) {
                    value = str.split(",");
                    set.add(Math.abs((FEATURE[0] + "_" + name[0]).hashCode()) % ftrl.D);
                    set.add(Math.abs((FEATURE[1] + "_" + name[1]).hashCode()) % ftrl.D);
                    set.add(Math.abs((FEATURE[2] + "_" + name[3]).hashCode()) % ftrl.D);
                    set.add(Math.abs((FEATURE[3] + "_" + name[6]).hashCode()) % ftrl.D);
                    ftrl.train(set, Integer.parseInt(value[10]));
                    set.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // predict result
        BufferedOutputStream bos;
        String string = null;
        byte[] newLine = "\r\n".getBytes();
        int count = 0;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(submissionPath));
            bos.write(("true,ftrl,logisitc").getBytes());
            bos.write(newLine);

            br = new BufferedReader(new InputStreamReader(new FileInputStream(tePath), "UTF-8"));
            string = br.readLine();
            String name[] = string.split(",");
            String value[] = null;
            HashSet<Integer> set = new HashSet<>();

            while ((string = br.readLine()) != null) {
                count++;
                value = string.split(",");

                // private static final String[]
                // FEATURE={tagid,hour,province,url};
                set.add(Math.abs((FEATURE[0] + "_" + name[0]).hashCode()) % ftrl.D);
                set.add(Math.abs((FEATURE[1] + "_" + name[1]).hashCode()) % ftrl.D);
                set.add(Math.abs((FEATURE[2] + "_" + name[3]).hashCode()) % ftrl.D);
                set.add(Math.abs((FEATURE[3] + "_" + name[6]).hashCode()) % ftrl.D);
                p = ftrl.predict(set);
                String result = name[10] + "," + p + "," + name[name.length - 1];
                bos.write(result.getBytes());
                bos.write(newLine);
                set.clear();
            }

            bos.flush();
            bos.close();
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
