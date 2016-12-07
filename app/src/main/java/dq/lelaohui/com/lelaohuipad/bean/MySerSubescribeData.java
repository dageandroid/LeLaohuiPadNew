package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/12/3.
 */

public class MySerSubescribeData  extends BaseBean implements Parcelable {

    /**
     * serTransId : 428
     * stockDetailId : 7702
     * serviceId : 乐老汇冶金养老中心|105
     * serviceName : 中心刮痧
     * customerId : 101002000002000197
     * customerName : 吕清风
     * customerPhone : 17310535372
     * orgName : 乐老汇冶金养老中心
     * orgTypeId : 3
     * orgId : 1002
     * proWaitersNum : 1
     * serEndTime : Nov 29, 2016 11:00:00 AM
     * serStartTime : Nov 29, 2016 9:30:00 AM
     * transStatus : 2
     * addTime : Nov 28, 2016 9:39:17 PM
     * waitersName : 康体部
     * packDetailId : 223
     * isEval : 0
     */

    private int serTransId;
    private int stockDetailId;
    private String serviceId;
    private String serviceName;
    private long customerId;
    private String customerName;
    private String customerPhone;
    private String orgName;
    private int orgTypeId;
    private int orgId;
    private int proWaitersNum;
    private Date serEndTime;
    private Date serStartTime;
    private String transStatus;
    private Date addTime;
    private String waitersName;
    private int packDetailId;
    private int isEval;

    protected MySerSubescribeData(Parcel in) {
        serTransId = in.readInt();
        stockDetailId = in.readInt();
        serviceId = in.readString();
        serviceName = in.readString();
        customerId = in.readLong();
        customerName = in.readString();
        customerPhone = in.readString();
        orgName = in.readString();
        orgTypeId = in.readInt();
        orgId = in.readInt();
        proWaitersNum = in.readInt();
        transStatus = in.readString();
        waitersName = in.readString();
        packDetailId = in.readInt();
        isEval = in.readInt();
    }

    public static final Creator<MySerSubescribeData> CREATOR = new Creator<MySerSubescribeData>() {
        @Override
        public MySerSubescribeData createFromParcel(Parcel in) {
            return new MySerSubescribeData(in);
        }

        @Override
        public MySerSubescribeData[] newArray(int size) {
            return new MySerSubescribeData[size];
        }
    };

    public int getSerTransId() {
        return serTransId;
    }

    public void setSerTransId(int serTransId) {
        this.serTransId = serTransId;
    }

    public int getStockDetailId() {
        return stockDetailId;
    }

    public void setStockDetailId(int stockDetailId) {
        this.stockDetailId = stockDetailId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getOrgTypeId() {
        return orgTypeId;
    }

    public void setOrgTypeId(int orgTypeId) {
        this.orgTypeId = orgTypeId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getProWaitersNum() {
        return proWaitersNum;
    }

    public void setProWaitersNum(int proWaitersNum) {
        this.proWaitersNum = proWaitersNum;
    }

    public Date getSerEndTime() {
        return serEndTime;
    }

    public void setSerEndTime(Date serEndTime) {
        this.serEndTime = serEndTime;
    }

    public Date getSerStartTime() {
        return serStartTime;
    }

    public void setSerStartTime(Date serStartTime) {
        this.serStartTime = serStartTime;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getWaitersName() {
        return waitersName;
    }

    public void setWaitersName(String waitersName) {
        this.waitersName = waitersName;
    }

    public int getPackDetailId() {
        return packDetailId;
    }

    public void setPackDetailId(int packDetailId) {
        this.packDetailId = packDetailId;
    }

    public int getIsEval() {
        return isEval;
    }

    public void setIsEval(int isEval) {
        this.isEval = isEval;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(serTransId);
        parcel.writeInt(stockDetailId);
        parcel.writeString(serviceId);
        parcel.writeString(serviceName);
        parcel.writeLong(customerId);
        parcel.writeString(customerName);
        parcel.writeString(customerPhone);
        parcel.writeString(orgName);
        parcel.writeInt(orgTypeId);
        parcel.writeInt(orgId);
        parcel.writeInt(proWaitersNum);
        parcel.writeString(transStatus);
        parcel.writeString(waitersName);
        parcel.writeInt(packDetailId);
        parcel.writeInt(isEval);
    }
}
