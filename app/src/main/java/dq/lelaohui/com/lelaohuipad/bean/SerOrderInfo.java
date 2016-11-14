package dq.lelaohui.com.lelaohuipad.bean;

import java.io.Serializable;
import java.util.Date;

public class SerOrderInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long orderId;
	private Long orderPersonId;
	private String orderPersonName;
    public Long getOrderPersonId() {
		return orderPersonId;
	}

	public void setOrderPersonId(Long orderPersonId) {
		this.orderPersonId = orderPersonId;
	}

	public String getOrderPersonName() {
		return orderPersonName;
	}

	public void setOrderPersonName(String orderPersonName) {
		this.orderPersonName = orderPersonName;
	}

	private String orderCode;

    private Double amountTotal;

    private Integer isProm;

    private Double promPrice;

    private Double amountPayable;

    private int payStatus;
    
    private String payDateStr;
    private Date payDate;

    private Double payAmount;

    private int payStyle;

    private int orderStatus;

    private String reamark;

    private Long contactId;

    private String contactAddress;

    private Long customerId;

    private String customerName;

    private String customerPhone;

    private Long orderPersionId;

    private String orderPersionName;

    private Date orderPersionTime;

    private int orderType;

    private String reason;

    private String channel;

    private int isDelete;

    private Date updTime;

    private Date addTime;
    private Long supplierId;
    private int supplierTypeId;
    public int getSupplierTypeId() {
		return supplierTypeId;
	}

	public void setSupplierTypeId(int supplierTypeId) {
		this.supplierTypeId = supplierTypeId;
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
		this.supplierName = supplierName;
	}

	private String supplierName;
    private String orgName;

    private int orgId;
    private int orgTypeId;
    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


    public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(int orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getPayDateStr() {
		return payDateStr;
	}

	public void setPayDateStr(String payDateStr) {
		this.payDateStr = payDateStr;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Double getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Double amountTotal) {
        this.amountTotal = amountTotal;
    }

    public Integer getIsProm() {
        return isProm;
    }

    public void setIsProm(Integer isProm) {
        this.isProm = isProm;
    }

    public Double getPromPrice() {
        return promPrice;
    }

    public void setPromPrice(Double promPrice) {
        this.promPrice = promPrice;
    }

    public Double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(Integer payStyle) {
        this.payStyle = payStyle;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getReamark() {
        return reamark;
    }

    public void setReamark(String reamark) {
        this.reamark = reamark == null ? null : reamark.trim();
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress == null ? null : contactAddress.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public Long getOrderPersionId() {
        return orderPersionId;
    }

    public void setOrderPersionId(Long orderPersionId) {
        this.orderPersionId = orderPersionId;
    }

    public String getOrderPersionName() {
        return orderPersionName;
    }

    public void setOrderPersionName(String orderPersionName) {
        this.orderPersionName = orderPersionName == null ? null : orderPersionName.trim();
    }

    public Date getOrderPersionTime() {
        return orderPersionTime;
    }

    public void setOrderPersionTime(Date orderPersionTime) {
        this.orderPersionTime = orderPersionTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}