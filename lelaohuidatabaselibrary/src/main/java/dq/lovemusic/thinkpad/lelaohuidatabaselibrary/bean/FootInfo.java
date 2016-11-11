package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;

/**
 * Created by ThinkPad on 2016/10/20.
 */
@Entity
public class FootInfo extends BaseBean{

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * cateId : 342
     * cateName : 餐饮
     * proId : 3418
     * proName : 肉丝拉皮
     * supplierId : 10003
     * proPrice : 15
     * proPic : /images/diet/pretermitFoodImg.jpg
     * mealTime : 3
     * mealType : 0
     * remark : 肉丝拉皮
     * supplierName : 餐厅B
     * supplierType : 4
     * proNum : 1
     */
    @Id(autoincrement = true)
    @Generated
    @NoToJson
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
    private int mealTime;
    @Expose
    private int mealType;
    @Expose
    private String remark;
    @Expose
    private String supplierName;
    @Expose
    private String supplierType;
    @Expose
    private String proNum;

    @Generated(hash = 1606913812)
    public FootInfo(Long id, int cateId, String cateName, String proId,
            String proName, String supplierId, int proPrice, String proPic,
            int mealTime, int mealType, String remark, String supplierName,
            String supplierType, String proNum) {
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
        this.proNum = proNum;
    }

    @Generated(hash = 339284770)
    public FootInfo() {
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

    public int getMealTime() {
        return mealTime;
    }

    public void setMealTime(int mealTime) {
        this.mealTime = mealTime;
    }

    public int getMealType() {
        return mealType;
    }

    public void setMealType(int mealType) {
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

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }
    @Keep
    @Override
    public String toString() {
        return "FootInfo{" +
                "id=" + id +
                ", cateId=" + cateId +
                ", cateName='" + cateName + '\'' +
                ", proId='" + proId + '\'' +
                ", proName='" + proName + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", proPrice=" + proPrice +
                ", proPic='" + proPic + '\'' +
                ", mealTime=" + mealTime +
                ", mealType=" + mealType +
                ", remark='" + remark + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierType='" + supplierType + '\'' +
                ", proNum='" + proNum + '\'' +
                '}';
    }
}
