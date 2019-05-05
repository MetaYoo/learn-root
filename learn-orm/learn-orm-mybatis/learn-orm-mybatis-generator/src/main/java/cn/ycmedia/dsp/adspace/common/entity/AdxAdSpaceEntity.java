package cn.ycmedia.dsp.adspace.common.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AdxAdSpaceEntity {
    private Integer itemid;

    private Integer mediaId;

    private String mediaName;

    private Long websiteId;

    private String websiteName;

    private Long mediaAdspaceId;

    private String mediaAdsapceName;

    private String mediaMinCpmPrice;

    private String mediaInformationFlowTypeIdList;

    private String mediaAdspaceLevel;

    private BigDecimal minCpm;

    private BigDecimal mPrice;

    private String openType;

    private String dspAdspaceId;

    private String dspAdspaceHashid;

    private String dspDiyName;

    private String excludeLevel;

    private String showType;

    private String mediaType;

    private String mediaExcludedCategory;

    private BigDecimal pvLoss;

    private BigDecimal clickLoss;

    private String isDiy;

    private String remark;

    private Date updatetime;

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName == null ? null : mediaName.trim();
    }

    public Long getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Long websiteId) {
        this.websiteId = websiteId;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName == null ? null : websiteName.trim();
    }

    public Long getMediaAdspaceId() {
        return mediaAdspaceId;
    }

    public void setMediaAdspaceId(Long mediaAdspaceId) {
        this.mediaAdspaceId = mediaAdspaceId;
    }

    public String getMediaAdsapceName() {
        return mediaAdsapceName;
    }

    public void setMediaAdsapceName(String mediaAdsapceName) {
        this.mediaAdsapceName = mediaAdsapceName == null ? null : mediaAdsapceName.trim();
    }

    public String getMediaMinCpmPrice() {
        return mediaMinCpmPrice;
    }

    public void setMediaMinCpmPrice(String mediaMinCpmPrice) {
        this.mediaMinCpmPrice = mediaMinCpmPrice == null ? null : mediaMinCpmPrice.trim();
    }

    public String getMediaInformationFlowTypeIdList() {
        return mediaInformationFlowTypeIdList;
    }

    public void setMediaInformationFlowTypeIdList(String mediaInformationFlowTypeIdList) {
        this.mediaInformationFlowTypeIdList = mediaInformationFlowTypeIdList == null ? null : mediaInformationFlowTypeIdList.trim();
    }

    public String getMediaAdspaceLevel() {
        return mediaAdspaceLevel;
    }

    public void setMediaAdspaceLevel(String mediaAdspaceLevel) {
        this.mediaAdspaceLevel = mediaAdspaceLevel == null ? null : mediaAdspaceLevel.trim();
    }

    public BigDecimal getMinCpm() {
        return minCpm;
    }

    public void setMinCpm(BigDecimal minCpm) {
        this.minCpm = minCpm;
    }

    public BigDecimal getmPrice() {
        return mPrice;
    }

    public void setmPrice(BigDecimal mPrice) {
        this.mPrice = mPrice;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    public String getDspAdspaceId() {
        return dspAdspaceId;
    }

    public void setDspAdspaceId(String dspAdspaceId) {
        this.dspAdspaceId = dspAdspaceId == null ? null : dspAdspaceId.trim();
    }

    public String getDspAdspaceHashid() {
        return dspAdspaceHashid;
    }

    public void setDspAdspaceHashid(String dspAdspaceHashid) {
        this.dspAdspaceHashid = dspAdspaceHashid == null ? null : dspAdspaceHashid.trim();
    }

    public String getDspDiyName() {
        return dspDiyName;
    }

    public void setDspDiyName(String dspDiyName) {
        this.dspDiyName = dspDiyName == null ? null : dspDiyName.trim();
    }

    public String getExcludeLevel() {
        return excludeLevel;
    }

    public void setExcludeLevel(String excludeLevel) {
        this.excludeLevel = excludeLevel == null ? null : excludeLevel.trim();
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType == null ? null : showType.trim();
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType == null ? null : mediaType.trim();
    }

    public String getMediaExcludedCategory() {
        return mediaExcludedCategory;
    }

    public void setMediaExcludedCategory(String mediaExcludedCategory) {
        this.mediaExcludedCategory = mediaExcludedCategory == null ? null : mediaExcludedCategory.trim();
    }

    public BigDecimal getPvLoss() {
        return pvLoss;
    }

    public void setPvLoss(BigDecimal pvLoss) {
        this.pvLoss = pvLoss;
    }

    public BigDecimal getClickLoss() {
        return clickLoss;
    }

    public void setClickLoss(BigDecimal clickLoss) {
        this.clickLoss = clickLoss;
    }

    public String getIsDiy() {
        return isDiy;
    }

    public void setIsDiy(String isDiy) {
        this.isDiy = isDiy == null ? null : isDiy.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}