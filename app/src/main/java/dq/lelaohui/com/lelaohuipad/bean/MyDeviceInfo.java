package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/12/28.
 */

public class MyDeviceInfo  extends BaseBean implements Parcelable {


    /**
     * deviceId : 12
     * deviceCode : VSTB067491YSELU
     * deviceName : 冶金展厅摄像头
     * deviceModel : pnp
     * deviceType : 5
     * status : 2
     * useUserId :
     * useUserName :
     * price : 0
     * deposit : 0
     * probation :
     * orgId : 1002
     * orgType : 3
     * orgName :
     */

    private int deviceId;
    private String deviceCode;
    private String deviceName;
    private String deviceModel;
    private String deviceType;
    private String status;
    private String useUserId;
    private String useUserName;
    private int price;
    private int deposit;
    private String probation;
    private int orgId;
    private int orgType;
    private String orgName;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUseUserId() {
        return useUserId;
    }

    public void setUseUserId(String useUserId) {
        this.useUserId = useUserId;
    }

    public String getUseUserName() {
        return useUserName;
    }

    public void setUseUserName(String useUserName) {
        this.useUserName = useUserName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getProbation() {
        return probation;
    }

    public void setProbation(String probation) {
        this.probation = probation;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "MyDeviceInfo{" +
                "deviceId=" + deviceId +
                ", deviceCode='" + deviceCode + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", status='" + status + '\'' +
                ", useUserId='" + useUserId + '\'' +
                ", useUserName='" + useUserName + '\'' +
                ", price=" + price +
                ", deposit=" + deposit +
                ", probation='" + probation + '\'' +
                ", orgId=" + orgId +
                ", orgType=" + orgType +
                ", orgName='" + orgName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.deviceId);
        dest.writeString(this.deviceCode);
        dest.writeString(this.deviceName);
        dest.writeString(this.deviceModel);
        dest.writeString(this.deviceType);
        dest.writeString(this.status);
        dest.writeString(this.useUserId);
        dest.writeString(this.useUserName);
        dest.writeInt(this.price);
        dest.writeInt(this.deposit);
        dest.writeString(this.probation);
        dest.writeInt(this.orgId);
        dest.writeInt(this.orgType);
        dest.writeString(this.orgName);
    }

    public MyDeviceInfo() {
    }

    protected MyDeviceInfo(Parcel in) {
        this.deviceId = in.readInt();
        this.deviceCode = in.readString();
        this.deviceName = in.readString();
        this.deviceModel = in.readString();
        this.deviceType = in.readString();
        this.status = in.readString();
        this.useUserId = in.readString();
        this.useUserName = in.readString();
        this.price = in.readInt();
        this.deposit = in.readInt();
        this.probation = in.readString();
        this.orgId = in.readInt();
        this.orgType = in.readInt();
        this.orgName = in.readString();
    }

    public static final Creator<MyDeviceInfo> CREATOR = new Creator<MyDeviceInfo>() {
        @Override
        public MyDeviceInfo createFromParcel(Parcel source) {
            return new MyDeviceInfo(source);
        }

        @Override
        public MyDeviceInfo[] newArray(int size) {
            return new MyDeviceInfo[size];
        }
    };
}
