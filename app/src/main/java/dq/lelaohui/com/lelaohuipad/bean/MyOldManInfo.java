package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZTF on 2017/1/6.
 */

public class MyOldManInfo implements Parcelable {
    @Override
    public String toString() {
        return "MyOldManInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    private String userId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
    }

    public MyOldManInfo() {
    }

    protected MyOldManInfo(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
    }

    public static final Parcelable.Creator<MyOldManInfo> CREATOR = new Parcelable.Creator<MyOldManInfo>() {
        @Override
        public MyOldManInfo createFromParcel(Parcel source) {
            return new MyOldManInfo(source);
        }

        @Override
        public MyOldManInfo[] newArray(int size) {
            return new MyOldManInfo[size];
        }
    };
}
