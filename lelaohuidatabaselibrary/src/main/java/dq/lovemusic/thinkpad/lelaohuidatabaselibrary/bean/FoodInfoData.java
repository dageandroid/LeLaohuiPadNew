package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;

/**
 * Created by ZTF on 2016/11/20.
 */
@Entity
public class FoodInfoData extends BaseBean implements Parcelable {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
         * cateId : 320
         * cateName : 菜
         * proId : 10147
         * proName : 京酱肉丝
         * supplierId : 10000
         * proPrice : 12
         * proPic : /images/diet/pretermitFoodImg.jpg
         * mealTime : 3
         * mealType : 0
         * remark :
         * supplierName : 罗庄餐厅A
         * supplierType : 4
         */
        @Id(autoincrement = true)
        private Long id;
    @Expose
    private int cateId;
    @Expose
    private String cateName;
    @Expose
    private String proId;
    @Expose
    private String proName;
    @Expose
    private String supplierId;
    @Expose
    private int proPrice;
    @Expose
    private String proPic;
    @Expose
    private String mealTime;
    @Expose
    private String mealType;
    @Expose
    private String remark;
    @Expose
    private String supplierName;
    @Expose
    private String supplierType;
    @Expose
    private int buyNum;
    @Expose
    private String isScope;

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public String getIsScope() {
        return isScope;
    }

    public void setIsScope(String isScope) {
        this.isScope = isScope;
    }

    public int getCateId() {
            return cateId;
        }

        public void setCateId(int cateId) {
            this.cateId = cateId;
        }

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public String getProId() {
            return proId;
        }

        public void setProId(String proId) {
            this.proId = proId;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public int getProPrice() {
            return proPrice;
        }

        public void setProPrice(int proPrice) {
            this.proPrice = proPrice;
        }

        public String getProPic() {
            return proPic;
        }

        public void setProPic(String proPic) {
            this.proPic = proPic;
        }

        public String getMealTime() {
            return mealTime;
        }

        public void setMealTime(String mealTime) {
            this.mealTime = mealTime;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getSupplierType() {
            return supplierType;
        }

        public void setSupplierType(String supplierType) {
            this.supplierType = supplierType;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cateId);
        dest.writeString(this.cateName);
        dest.writeString(this.proId);
        dest.writeString(this.proName);
        dest.writeString(this.supplierId);
        dest.writeInt(this.proPrice);
        dest.writeString(this.proPic);
        dest.writeString(this.mealTime);
        dest.writeString(this.mealType);
        dest.writeString(this.remark);
        dest.writeString(this.supplierName);
        dest.writeString(this.supplierType);
        dest.writeInt(this.buyNum);
        dest.writeString(this.isScope);
    }

    public FoodInfoData() {
    }

    protected FoodInfoData(Parcel in) {
        this.cateId = in.readInt();
        this.cateName = in.readString();
        this.proId = in.readString();
        this.proName = in.readString();
        this.supplierId = in.readString();
        this.proPrice = in.readInt();
        this.proPic = in.readString();
        this.mealTime = in.readString();
        this.mealType = in.readString();
        this.remark = in.readString();
        this.supplierName = in.readString();
        this.supplierType = in.readString();
        this.buyNum = in.readInt();
        this.isScope = in.readString();
    }

    @Generated(hash = 1381895384)
    public FoodInfoData(Long id, int cateId, String cateName, String proId, String proName,
            String supplierId, int proPrice, String proPic, String mealTime, String mealType,
            String remark, String supplierName, String supplierType, int buyNum,
            String isScope) {
        this.id = id;
        this.cateId = cateId;
        this.cateName = cateName;
        this.proId = proId;
        this.proName = proName;
        this.supplierId = supplierId;
        this.proPrice = proPrice;
        this.proPic = proPic;
        this.mealTime = mealTime;
        this.mealType = mealType;
        this.remark = remark;
        this.supplierName = supplierName;
        this.supplierType = supplierType;
        this.buyNum = buyNum;
        this.isScope = isScope;
    }

    public static final Creator<FoodInfoData> CREATOR = new Creator<FoodInfoData>() {
        @Override
        public FoodInfoData createFromParcel(Parcel source) {
            return new FoodInfoData(source);
        }

        @Override
        public FoodInfoData[] newArray(int size) {
            return new FoodInfoData[size];
        }
    };
}
