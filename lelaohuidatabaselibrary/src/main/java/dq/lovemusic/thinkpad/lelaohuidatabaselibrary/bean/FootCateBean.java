package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ThinkPad on 2016/11/23.
 */
@Entity
public class FootCateBean extends  BaseBean{
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
    @Id(autoincrement = false)
    @Property( nameInDb="_id")
    private int cateId;
    @Keep
    public  FootCateBean(FoodInfoData data){
        setCateId(data.getCateId());
        setCateName(data.getCateName());
        setMealTime(data.getMealTime());
        setMealType(data.getMealType());
    }
    private String cateName;
    @Generated(hash = 1985388432)
    public FootCateBean(int cateId, String cateName, String mealTime,
            String mealType) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.mealTime = mealTime;
        this.mealType = mealType;
    }

    @Generated(hash = 1003289416)
    public FootCateBean() {
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

    @Expose
    private String mealTime;
    @Expose
    private String mealType;
}