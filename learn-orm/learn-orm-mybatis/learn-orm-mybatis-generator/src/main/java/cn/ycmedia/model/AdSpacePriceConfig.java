package cn.ycmedia.model;

import java.math.BigDecimal;
import java.util.Date;

public class AdSpacePriceConfig {
    private Integer itemId;

    private String adspaceId;

    private Integer adxMediaId;

    private String adspaceName;

    private String adxAdspaceName;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Integer adPriceType;

    private BigDecimal fixedPrice;

    private BigDecimal avgRtb;

    private Integer isControl;

    private String isActive;

    private Date creationDate;

    private Integer createdBy;

    private Date lastUpdateDate;

    private Integer lastUpdatedBy;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getAdspaceId() {
        return adspaceId;
    }

    public void setAdspaceId(String adspaceId) {
        this.adspaceId = adspaceId == null ? null : adspaceId.trim();
    }

    public Integer getAdxMediaId() {
        return adxMediaId;
    }

    public void setAdxMediaId(Integer adxMediaId) {
        this.adxMediaId = adxMediaId;
    }

    public String getAdspaceName() {
        return adspaceName;
    }

    public void setAdspaceName(String adspaceName) {
        this.adspaceName = adspaceName == null ? null : adspaceName.trim();
    }

    public String getAdxAdspaceName() {
        return adxAdspaceName;
    }

    public void setAdxAdspaceName(String adxAdspaceName) {
        this.adxAdspaceName = adxAdspaceName == null ? null : adxAdspaceName.trim();
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getAdPriceType() {
        return adPriceType;
    }

    public void setAdPriceType(Integer adPriceType) {
        this.adPriceType = adPriceType;
    }

    public BigDecimal getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(BigDecimal fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public BigDecimal getAvgRtb() {
        return avgRtb;
    }

    public void setAvgRtb(BigDecimal avgRtb) {
        this.avgRtb = avgRtb;
    }

    public Integer getIsControl() {
        return isControl;
    }

    public void setIsControl(Integer isControl) {
        this.isControl = isControl;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive == null ? null : isActive.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}