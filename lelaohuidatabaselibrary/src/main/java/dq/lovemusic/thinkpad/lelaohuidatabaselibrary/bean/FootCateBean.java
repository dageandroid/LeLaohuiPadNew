package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import android.util.Log;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import static android.content.ContentValues.TAG;

/**
 * Created by ThinkPad on 2016/11/23.
 */
@Entity
public class FootCateBean extends  BaseBean{
    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    private Long cateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id(autoincrement = true)
    @Property( nameInDb="_id")
    private Long id;
    public void setUnineqKey(String unineqKey) {
        this.unineqKey = unineqKey;
    }
    @Unique
    @Expose(serialize = false,deserialize = false)
    protected String unineqKey;

    @Keep
    public  FootCateBean(FoodInfoData data){
        setCateId(Long.valueOf(data.getCateId()+""));
        setCateName(data.getCateName());
        setMealTime(data.getIsScore());
        setMealType(data.getMealType());
        setUnineqKey(this.mealTime+"_"+this.cateId);
        Log.i(TAG, "FootCateBean: " +data.toString());
    }

    @Generated(hash = 4747128)
    public FootCateBean(Long cateId, Long id, String unineqKey, String cateName,
            String mealTime, String mealType) {
        this.cateId = cateId;
        this.id = id;
        this.unineqKey = unineqKey;
        this.cateName = cateName;
        this.mealTime = mealTime;
        this.mealType = mealType;
    }

    @Generated(hash = 1003289416)
    public FootCateBean() {
    }
    private String cateName;

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

    public String getUnineqKey() {
        return unineqKey;
    }
}
