package dq.lelaohui.com.lelaohuipad.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FoodInfoData;

/**
 * Created by ZTF on 2016/11/20.
 */

public class FoodInfoCate {


    /**
     * code : 0
     * msg : 获取数据成功
     * data : [{"cateId":320,"cateName":"菜","proId":"10147","proName":"京酱肉丝","supplierId":"10000","proPrice":12,"proPic":"/images/diet/pretermitFoodImg.jpg","mealTime":"3","mealType":"0","remark":"","supplierName":"罗庄餐厅A","supplierType":"4"},{"cateId":320,"cateName":"菜","proId":"10146","proName":"鱼香肉丝","supplierId":"10000","proPrice":12,"proPic":"/images/diet/pretermitFoodImg.jpg","mealTime":"3","mealType":"0","remark":"","supplierName":"罗庄餐厅A","supplierType":"4"},{"cateId":241,"cateName":"汤/粥","proId":"10149","proName":"粥","supplierId":"10000","proPrice":2,"proPic":"/images/diet/pretermitFoodImg.jpg","mealTime":"2","mealType":"0","remark":"","supplierName":"罗庄餐厅A","supplierType":"4"},{"cateId":360,"cateName":"主食","proId":"10148","proName":"米饭","supplierId":"10000","proPrice":2,"proPic":"/images/diet/pretermitFoodImg.jpg","mealTime":"2","mealType":"0","remark":"","supplierName":"罗庄餐厅A","supplierType":"4"},{"cateId":320,"cateName":"菜","proId":"10147","proName":"京酱肉丝","supplierId":"10000","proPrice":12,"proPic":"/images/diet/pretermitFoodImg.jpg","mealTime":"1","mealType":"0","remark":"","supplierName":"罗庄餐厅A","supplierType":"4"},{"cateId":320,"cateName":"菜","proId":"10146","proName":"鱼香肉丝","supplierId":"10000","proPrice":12,"proPic":"/images/diet/pretermitFoodImg.jpg","mealTime":"1","mealType":"0","remark":"","supplierName":"罗庄餐厅A","supplierType":"4"}]
     */
    @Expose
    private int code;
    @Expose
    private String msg;

    public List<FoodInfoData> getData() {
        return data;
    }

    public void setData(List<FoodInfoData> data) {
        this.data = data;
    }
    @Expose
    private List<FoodInfoData> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "FoodInfoCate{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
