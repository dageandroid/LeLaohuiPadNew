package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/11/30.
 */

public class ServerPersonData  extends BaseBean implements Parcelable {


    /**
     * userId : 101000100002000006
     * userCode : 2000006
     * userName : 1103入院测试老人1
     * telephone : 15010797278
     * sfz : 110110195212125651
     * sex : 1
     * experience : 2.5
     */

    private String userId;
    private String userCode;
    private String userName;
    private String telephone;
    private String sfz;
    private String sex;
    private String experience;
    private String isChange;//是否允许更换服务员
    private int assignType;//预约方式

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public int getAssignType() {
        return assignType;
    }

    public void setAssignType(int assignType) {
        this.assignType = assignType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
    public ServerPersonData() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userCode);
        dest.writeString(this.userName);
        dest.writeString(this.telephone);
        dest.writeString(this.sfz);
        dest.writeString(this.sex);
        dest.writeString(this.experience);
        dest.writeString(this.isChange);
        dest.writeInt(this.assignType);
    }

    protected ServerPersonData(Parcel in) {
        this.userId = in.readString();
        this.userCode = in.readString();
        this.userName = in.readString();
        this.telephone = in.readString();
        this.sfz = in.readString();
        this.sex = in.readString();
        this.experience = in.readString();
        this.isChange = in.readString();
        this.assignType = in.readInt();
    }

    public static final Parcelable.Creator<ServerPersonData> CREATOR = new Parcelable.Creator<ServerPersonData>() {
        @Override
        public ServerPersonData createFromParcel(Parcel source) {
            return new ServerPersonData(source);
        }

        @Override
        public ServerPersonData[] newArray(int size) {
            return new ServerPersonData[size];
        }
    };
}
