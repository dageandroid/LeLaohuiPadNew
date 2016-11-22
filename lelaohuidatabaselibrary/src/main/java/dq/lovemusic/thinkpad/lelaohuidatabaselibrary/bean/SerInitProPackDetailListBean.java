package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ThinkPad on 2016/11/2.
 */
@Entity
public class SerInitProPackDetailListBean extends BaseBean implements Parcelable {
    @Expose
    private int packDetailId;
    @Expose
    private int packId;
    @Expose
    private String serviceId;
    @Expose
    private String serviceName;
    @Expose
    private String pictureUrl;

    @Expose
    private int orgTypeId;
    @Expose
    private int orgId;
    @Id(autoincrement = true)
    @NoToJson
    private Long  id;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    private int packageId;
    @Generated(hash = 1920477268)
    public SerInitProPackDetailListBean(int packDetailId, int packId, String serviceId, String serviceName, String pictureUrl, int orgTypeId,
            int orgId, Long id, int packageId) {
        this.packDetailId = packDetailId;
        this.packId = packId;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.pictureUrl = pictureUrl;
        this.orgTypeId = orgTypeId;
        this.orgId = orgId;
        this.id = id;
        this.packageId = packageId;
    }

    @Generated(hash = 955564226)
    public SerInitProPackDetailListBean() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPackDetailId() {
        return packDetailId;
    }

    public void setPackDetailId(int packDetailId) {
        this.packDetailId = packDetailId;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.packDetailId);
        dest.writeInt(this.packId);
        dest.writeString(this.serviceId);
        dest.writeString(this.serviceName);
        dest.writeString(this.pictureUrl);
        dest.writeInt(this.orgTypeId);
        dest.writeInt(this.orgId);
        dest.writeValue(this.id);
    }

    protected SerInitProPackDetailListBean(Parcel in) {
        this.packDetailId = in.readInt();
        this.packId = in.readInt();
        this.serviceId = in.readString();
        this.serviceName = in.readString();
        this.pictureUrl = in.readString();
        this.orgTypeId = in.readInt();
        this.orgId = in.readInt();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<SerInitProPackDetailListBean> CREATOR = new Parcelable.Creator<SerInitProPackDetailListBean>() {
        @Override
        public SerInitProPackDetailListBean createFromParcel(Parcel source) {
            return new SerInitProPackDetailListBean(source);
        }

        @Override
        public SerInitProPackDetailListBean[] newArray(int size) {
            return new SerInitProPackDetailListBean[size];
        }
    };
}
