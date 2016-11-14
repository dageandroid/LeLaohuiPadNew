package dq.lelaohui.com.lelaohuipad.bean;

import java.io.Serializable;
import java.util.Date;

public class SerProPackDetailOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long orderDeltailId;

    private Long orderSerId;

    private Long packDetailId;

    private String serviceId;

    private String serviceName;

    private Double servicePrice;

    private Long unit;

    private Long packId;

    private Integer orgTypeId;

    private Long orgId;

    private Double depositPrice;

    private Integer standardTime;

    private Long standardUnit;

    private Integer proWaitersNum;

    private Integer isDoor;

    private String reamark;

    private String pinYin;

    private String pY;

    private Long supplierId;

    private String supplierName;

    private Integer proLowerNum;

    private Integer isHasDefalutTime;

    private int  serNum;

    private Integer supplierType;

    private String orgName;

    private Integer timeRule;

    private Integer execDays;

    private String execDates;

    private Integer cycleDate;

    private Integer execNumDay;

    private String execTimeDay;

    private Integer isOverTimeCharge;

    private Integer isUseStand;

    private Integer overTimeStandard;

    private Long overTimeUnit;

    private Double overTimePrice;

    private Double price;

    private Integer waiterLevelId;

    private Date addTime;

    private Date updTime;

    private Integer detailPhone;

    public Long getOrderDeltailId() {
        return orderDeltailId;
    }

    public void setOrderDeltailId(Long orderDeltailId) {
        this.orderDeltailId = orderDeltailId;
    }

    public Long getOrderSerId() {
        return orderSerId;
    }

    public void setOrderSerId(Long orderSerId) {
        this.orderSerId = orderSerId;
    }

    public Long getPackDetailId() {
        return packDetailId;
    }

    public void setPackDetailId(Long packDetailId) {
        this.packDetailId = packDetailId;
    }


    public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
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

    public Double getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(Double depositPrice) {
        this.depositPrice = depositPrice;
    }

    public Integer getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(Integer standardTime) {
        this.standardTime = standardTime;
    }

    public Long getStandardUnit() {
        return standardUnit;
    }

    public void setStandardUnit(Long standardUnit) {
        this.standardUnit = standardUnit;
    }

    public Integer getProWaitersNum() {
        return proWaitersNum;
    }

    public void setProWaitersNum(Integer proWaitersNum) {
        this.proWaitersNum = proWaitersNum;
    }

    public Integer getIsDoor() {
        return isDoor;
    }

    public void setIsDoor(Integer isDoor) {
        this.isDoor = isDoor;
    }

    public String getReamark() {
        return reamark;
    }

    public void setReamark(String reamark) {
        this.reamark = reamark == null ? null : reamark.trim();
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public Integer getProLowerNum() {
        return proLowerNum;
    }

    public void setProLowerNum(Integer proLowerNum) {
        this.proLowerNum = proLowerNum;
    }

    public Integer getIsHasDefalutTime() {
        return isHasDefalutTime;
    }

    public void setIsHasDefalutTime(Integer isHasDefalutTime) {
        this.isHasDefalutTime = isHasDefalutTime;
    }


    public int getSerNum() {
		return serNum;
	}

	public void setSerNum(int serNum) {
		this.serNum = serNum;
	}

	public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getTimeRule() {
        return timeRule;
    }

    public void setTimeRule(Integer timeRule) {
        this.timeRule = timeRule;
    }

    public Integer getExecDays() {
        return execDays;
    }

    public void setExecDays(Integer execDays) {
        this.execDays = execDays;
    }

    public String getExecDates() {
        return execDates;
    }

    public void setExecDates(String execDates) {
        this.execDates = execDates == null ? null : execDates.trim();
    }

    public Integer getCycleDate() {
        return cycleDate;
    }

    public void setCycleDate(Integer cycleDate) {
        this.cycleDate = cycleDate;
    }

    public Integer getExecNumDay() {
        return execNumDay;
    }

    public void setExecNumDay(Integer execNumDay) {
        this.execNumDay = execNumDay;
    }

    public String getExecTimeDay() {
        return execTimeDay;
    }

    public void setExecTimeDay(String execTimeDay) {
        this.execTimeDay = execTimeDay == null ? null : execTimeDay.trim();
    }

    public Integer getIsOverTimeCharge() {
        return isOverTimeCharge;
    }

    public void setIsOverTimeCharge(Integer isOverTimeCharge) {
        this.isOverTimeCharge = isOverTimeCharge;
    }

    public Integer getIsUseStand() {
        return isUseStand;
    }

    public void setIsUseStand(Integer isUseStand) {
        this.isUseStand = isUseStand;
    }

    public Integer getOverTimeStandard() {
        return overTimeStandard;
    }

    public void setOverTimeStandard(Integer overTimeStandard) {
        this.overTimeStandard = overTimeStandard;
    }

    public Long getOverTimeUnit() {
        return overTimeUnit;
    }

    public void setOverTimeUnit(Long overTimeUnit) {
        this.overTimeUnit = overTimeUnit;
    }

    public Double getOverTimePrice() {
        return overTimePrice;
    }

    public void setOverTimePrice(Double overTimePrice) {
        this.overTimePrice = overTimePrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWaiterLevelId() {
        return waiterLevelId;
    }

    public void setWaiterLevelId(Integer waiterLevelId) {
        this.waiterLevelId = waiterLevelId;
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

    public Integer getDetailPhone() {
        return detailPhone;
    }

    public void setDetailPhone(Integer detailPhone) {
        this.detailPhone = detailPhone;
    }
}