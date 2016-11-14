package dq.lelaohui.com.lelaohuipad.bean;

import java.io.Serializable;
import java.util.Date;

public class SerProPackageOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long orderSerId;

    private String orderCode;

    private Long packId;

    private Long levelId;

    private String packName;

    private String packTitle;

    private Double depositPrice;

    private String orgName;

    private Integer orgTypeId;

    private Long orgId;

    private Long serviceCateId;

    private Integer isPro;

    private Integer servicePhone;

    private String pictureName;

    private String pictureUrl;

    private Integer packageType;

    private Integer isUseEnd;

    private Integer validityTime;

    private Integer validityUnit;

    private Date endDate;
    private String endDateStr;

    private String reamark;

    private Date addTime;

    private Date updTime;

    private String pinYin;

    private String pY;

    private String attentions;
    
    public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Long getOrderSerId() {
        return orderSerId;
    }

    public void setOrderSerId(Long orderSerId) {
        this.orderSerId = orderSerId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName == null ? null : packName.trim();
    }

    public String getPackTitle() {
        return packTitle;
    }

    public void setPackTitle(String packTitle) {
        this.packTitle = packTitle == null ? null : packTitle.trim();
    }

    public Double getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(Double depositPrice) {
        this.depositPrice = depositPrice;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getOrgTypeId() {
        return orgTypeId;
    }

    public void setOrgTypeId(Integer orgTypeId) {
        this.orgTypeId = orgTypeId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getServiceCateId() {
        return serviceCateId;
    }

    public void setServiceCateId(Long serviceCateId) {
        this.serviceCateId = serviceCateId;
    }

    public Integer getIsPro() {
        return isPro;
    }

    public void setIsPro(Integer isPro) {
        this.isPro = isPro;
    }

    public Integer getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(Integer servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public Integer getPackageType() {
        return packageType;
    }

    public void setPackageType(Integer packageType) {
        this.packageType = packageType;
    }

    public Integer getIsUseEnd() {
        return isUseEnd;
    }

    public void setIsUseEnd(Integer isUseEnd) {
        this.isUseEnd = isUseEnd;
    }

    public Integer getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Integer validityTime) {
        this.validityTime = validityTime;
    }

    public Integer getValidityUnit() {
        return validityUnit;
    }

    public void setValidityUnit(Integer validityUnit) {
        this.validityUnit = validityUnit;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReamark() {
        return reamark;
    }

    public void setReamark(String reamark) {
        this.reamark = reamark == null ? null : reamark.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin == null ? null : pinYin.trim();
    }

    public String getpY() {
        return pY;
    }

    public void setpY(String pY) {
        this.pY = pY == null ? null : pY.trim();
    }

    public String getAttentions() {
        return attentions;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions == null ? null : attentions.trim();
    }
}