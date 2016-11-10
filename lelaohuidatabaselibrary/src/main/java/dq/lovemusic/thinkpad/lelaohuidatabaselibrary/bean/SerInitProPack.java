package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;
import org.greenrobot.greendao.DaoException;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.DaoSession;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.SerInitProPackDetailListBeanDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.SerInitProPackDao;

/**
 * Created by ThinkPad on 2016/11/2.
 */
@Entity
public class SerInitProPack extends BaseBean implements Parcelable{

    @Id(autoincrement = true)
    @NoToJson
    private Long  id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * packId : 27
     * packName : 上门修脚单次包
     * orgName : 乐老汇罗庄养老中心
     * orgTypeId : 3
     * orgId : 1001
     * serviceCateId : 139
     * isPro : 0
     * pictureUrl : LelaoHuiWebApp/folder/xiujiao.jpg
     * reamark : 足部保健单次服务
     * saleNums : 1000
     * serInitProPackDetailList : [{"packDetailId":50,"packId":27,"serviceId":"乐老汇罗庄养老中心|103","serviceName":"上门修脚","pictureUrl":"LelaoHuiWebApp/folder/xiujiao.jpg"}]
     * price : 48
     */

    public int packId;
    public String packName;
    public String orgName;
    public int orgTypeId;
    public int orgId;
    public int serviceCateId;
    public int isPro;
    public String pictureUrl;
    public String reamark;
    public int saleNums;
    public int price;
    public int buyNum;


    /**
     * packDetailId : 50
     * packId : 27
     * serviceId : 乐老汇罗庄养老中心|103
     * serviceName : 上门修脚
     * pictureUrl : LelaoHuiWebApp/folder/xiujiao.jpg
     */
    @ToMany(joinProperties={@JoinProperty(name="packId",referencedName="packId"),@JoinProperty(name="orgId",referencedName="orgId"),@JoinProperty(name="orgTypeId",referencedName="orgTypeId")})
    public List<SerInitProPackDetailListBean> serInitProPackDetailList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    public transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 823979279)
    public transient SerInitProPackDao myDao;
    @Generated(hash = 1811115027)
    public SerInitProPack(Long id, int packId, String packName, String orgName, int orgTypeId, int orgId, int serviceCateId, int isPro, String pictureUrl, String reamark, int saleNums,
            int price,int buyNum) {
        this.id = id;
        this.packId = packId;
        this.packName = packName;
        this.orgName = orgName;
        this.orgTypeId = orgTypeId;
        this.orgId = orgId;
        this.serviceCateId = serviceCateId;
        this.isPro = isPro;
        this.pictureUrl = pictureUrl;
        this.reamark = reamark;
        this.saleNums = saleNums;
        this.price = price;
        this.buyNum=buyNum;
    }

    @Generated(hash = 726855854)
    public SerInitProPack() {
    }
    public int getBuyNum() {
        return buyNum;
    }
    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }
    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
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

    public int getServiceCateId() {
        return serviceCateId;
    }

    public void setServiceCateId(int serviceCateId) {
        this.serviceCateId = serviceCateId;
    }

    public int getIsPro() {
        return isPro;
    }

    public void setIsPro(int isPro) {
        this.isPro = isPro;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getReamark() {
        return reamark;
    }

    public void setReamark(String reamark) {
        this.reamark = reamark;
    }

    public int getSaleNums() {
        return saleNums;
    }

    public void setSaleNums(int saleNums) {
        this.saleNums = saleNums;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1057147922)
    public List<SerInitProPackDetailListBean> getSerInitProPackDetailList() {
        if (serInitProPackDetailList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SerInitProPackDetailListBeanDao targetDao = daoSession.getSerInitProPackDetailListBeanDao();
            List<SerInitProPackDetailListBean> serInitProPackDetailListNew = targetDao._querySerInitProPack_SerInitProPackDetailList(packId, orgId, orgTypeId);
            synchronized (this) {
                if (serInitProPackDetailList == null) {
                    serInitProPackDetailList = serInitProPackDetailListNew;
                }
            }
        }
        return serInitProPackDetailList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 424410257)
    public synchronized void resetSerInitProPackDetailList() {
        serInitProPackDetailList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 191248925)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSerInitProPackDao() : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeInt(this.packId);
        dest.writeString(this.packName);
        dest.writeString(this.orgName);
        dest.writeInt(this.orgTypeId);
        dest.writeInt(this.orgId);
        dest.writeInt(this.serviceCateId);
        dest.writeInt(this.isPro);
        dest.writeString(this.pictureUrl);
        dest.writeString(this.reamark);
        dest.writeInt(this.saleNums);
        dest.writeInt(this.price);
        dest.writeInt(this.buyNum);
        dest.writeList(this.serInitProPackDetailList);
    }

    protected SerInitProPack(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.packId = in.readInt();
        this.packName = in.readString();
        this.orgName = in.readString();
        this.orgTypeId = in.readInt();
        this.orgId = in.readInt();
        this.serviceCateId = in.readInt();
        this.isPro = in.readInt();
        this.pictureUrl = in.readString();
        this.reamark = in.readString();
        this.saleNums = in.readInt();
        this.price = in.readInt();
        this.buyNum=in.readInt();
        this.serInitProPackDetailList = new ArrayList<SerInitProPackDetailListBean>();
        in.readList(this.serInitProPackDetailList, SerInitProPackDetailListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SerInitProPack> CREATOR = new Parcelable.Creator<SerInitProPack>() {
        @Override
        public SerInitProPack createFromParcel(Parcel source) {
            return new SerInitProPack(source);
        }

        @Override
        public SerInitProPack[] newArray(int size) {
            return new SerInitProPack[size];
        }
    };
}
