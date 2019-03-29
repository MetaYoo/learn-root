package cn.ycmedia.model;

import java.math.BigDecimal;
import java.util.Date;

public class MediaPriceControlEntity {
    private Integer itemid;

    private String mediaid;

    private String medianame;

    private BigDecimal pricerate;

    private Boolean iscontrol;

    private BigDecimal avgRtb;

    private Integer daybidnum;

    private Integer bidtimeinterval;

    private BigDecimal bidpvrate;

    private Integer pvtimeinterval;

    private Boolean isflowcontrol;

    private Boolean isusercontrol;

    private Boolean ispmp;

    private Integer minprice;

    private Integer maxprice;

    private Double maxpricerate;

    private Integer minPriceAdder;

    private Integer specialMaxPrice;

    private Boolean isaliprice;

    private String remark;

    private Date createtime;

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid == null ? null : mediaid.trim();
    }

    public String getMedianame() {
        return medianame;
    }

    public void setMedianame(String medianame) {
        this.medianame = medianame == null ? null : medianame.trim();
    }

    public BigDecimal getPricerate() {
        return pricerate;
    }

    public void setPricerate(BigDecimal pricerate) {
        this.pricerate = pricerate;
    }

    public Boolean getIscontrol() {
        return iscontrol;
    }

    public void setIscontrol(Boolean iscontrol) {
        this.iscontrol = iscontrol;
    }

    public BigDecimal getAvgRtb() {
        return avgRtb;
    }

    public void setAvgRtb(BigDecimal avgRtb) {
        this.avgRtb = avgRtb;
    }

    public Integer getDaybidnum() {
        return daybidnum;
    }

    public void setDaybidnum(Integer daybidnum) {
        this.daybidnum = daybidnum;
    }

    public Integer getBidtimeinterval() {
        return bidtimeinterval;
    }

    public void setBidtimeinterval(Integer bidtimeinterval) {
        this.bidtimeinterval = bidtimeinterval;
    }

    public BigDecimal getBidpvrate() {
        return bidpvrate;
    }

    public void setBidpvrate(BigDecimal bidpvrate) {
        this.bidpvrate = bidpvrate;
    }

    public Integer getPvtimeinterval() {
        return pvtimeinterval;
    }

    public void setPvtimeinterval(Integer pvtimeinterval) {
        this.pvtimeinterval = pvtimeinterval;
    }

    public Boolean getIsflowcontrol() {
        return isflowcontrol;
    }

    public void setIsflowcontrol(Boolean isflowcontrol) {
        this.isflowcontrol = isflowcontrol;
    }

    public Boolean getIsusercontrol() {
        return isusercontrol;
    }

    public void setIsusercontrol(Boolean isusercontrol) {
        this.isusercontrol = isusercontrol;
    }

    public Boolean getIspmp() {
        return ispmp;
    }

    public void setIspmp(Boolean ispmp) {
        this.ispmp = ispmp;
    }

    public Integer getMinprice() {
        return minprice;
    }

    public void setMinprice(Integer minprice) {
        this.minprice = minprice;
    }

    public Integer getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Integer maxprice) {
        this.maxprice = maxprice;
    }

    public Double getMaxpricerate() {
        return maxpricerate;
    }

    public void setMaxpricerate(Double maxpricerate) {
        this.maxpricerate = maxpricerate;
    }

    public Integer getMinPriceAdder() {
        return minPriceAdder;
    }

    public void setMinPriceAdder(Integer minPriceAdder) {
        this.minPriceAdder = minPriceAdder;
    }

    public Integer getSpecialMaxPrice() {
        return specialMaxPrice;
    }

    public void setSpecialMaxPrice(Integer specialMaxPrice) {
        this.specialMaxPrice = specialMaxPrice;
    }

    public Boolean getIsaliprice() {
        return isaliprice;
    }

    public void setIsaliprice(Boolean isaliprice) {
        this.isaliprice = isaliprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}