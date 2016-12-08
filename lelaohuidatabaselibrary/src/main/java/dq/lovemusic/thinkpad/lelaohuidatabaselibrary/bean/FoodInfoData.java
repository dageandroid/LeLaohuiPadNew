package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by ZTF on 2016/11/20.
 */
@Entity
public class FoodInfoData extends BaseBean implements Serializable {

    /**
     * cateId : 241
     * cateName : 汤/粥
     * proId : 10149
     * proName : 粥
     * supplierId : 10000
     * proPrice : 2
     * proPic : /images/diet/pretermitFoodImg.jpg
     * mealTime : 3
     * mealType : 0
     * remark :
     * supplierName : 罗庄餐厅A
     * supplierType : 4
     */
    @Id(autoincrement=true)
    private Long ID;
    private Long cateId;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    private String cateName;
    private String proId;
    private String proName;
    private String supplierId;
    private int proPrice;
    private String proPic;
    private String mealTime;
    private String mealType;
    private String remark;
    private String supplierName;
    private String isScore;

    public String getIsScore() {
        return isScore;
    }

    public void setIsScore(String isScore) {
        this.isScore = isScore;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    private String supplierType;

    @Generated(hash = 807037713)
    public FoodInfoData(Long ID, Long cateId, String cateName, String proId,
            String proName, String supplierId, int proPrice, String proPic,
            String mealTime, String mealType, String remark, String supplierName,
            String isScore, String supplierType) {
        this.ID = ID;
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
        this.isScore = isScore;
        this.supplierType = supplierType;
    }

    @Generated(hash = 1607671278)
    public FoodInfoData() {
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
}
