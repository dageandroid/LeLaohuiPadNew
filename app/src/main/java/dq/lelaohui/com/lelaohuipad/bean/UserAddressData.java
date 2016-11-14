package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/11/13.
 */

public class UserAddressData  extends BaseBean implements Parcelable {

    /**
     * userId : 101000000002000001
     * userName : 张学友
     * telephone : 13788888888
     * address : 地球亚洲中国
     * addressType : 0
     * addressId : 24
     */
    @Expose
    private String userId;
    @Expose
    private String userName;
    @Expose
    private String telephone;
    @Expose
    private String address;
    @Expose
    private int addressType;
    @Expose
    private int addressId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "UserAddressData{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", addressType=" + addressType +
                ", addressId=" + addressId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.telephone);
        dest.writeString(this.address);
        dest.writeInt(this.addressType);
        dest.writeInt(this.addressId);
    }

    public UserAddressData() {
    }

    protected UserAddressData(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.telephone = in.readString();
        this.address = in.readString();
        this.addressType = in.readInt();
        this.addressId = in.readInt();
    }

    public static final Parcelable.Creator<UserAddressData> CREATOR = new Parcelable.Creator<UserAddressData>() {
        @Override
        public UserAddressData createFromParcel(Parcel source) {
            return new UserAddressData(source);
        }

        @Override
        public UserAddressData[] newArray(int size) {
            return new UserAddressData[size];
        }
    };
}
