package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by ZTF on 2016/12/12.
 * 提交订餐相关数据
 */

public class SubShopFoodBean extends BaseOrderCate {
    /**
     *  * (addressType,payType,totalMoney,userAddressStr,userPhoneStr,isScope,Integer.parseInt(mealTime),userId,buyUserId,data);
     */
    private  int isDistr;
    private  int payType;
    private   int totalMoney;
    private    String addressStr;
    private  String phone;
    private  String  isScope;
    private   int mealtime;
    private   String userIdStr;
    private  String buyUserId;
    private  ArrayList<Bundle> cofirmOrderData;

    public int getIsDistr() {
        return isDistr;
    }

    public void setIsDistr(int isDistr) {
        this.isDistr = isDistr;
    }

    public ArrayList<Bundle> getCofirmOrderData() {
        return cofirmOrderData;
    }

    public void setCofirmOrderData(ArrayList<Bundle> cofirmOrderData) {
        this.cofirmOrderData = cofirmOrderData;
    }

    public String getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(String buyUserId) {
        this.buyUserId = buyUserId;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getIsScope() {
        return isScope;
    }

    public void setIsScope(String isScope) {
        this.isScope = isScope;
    }

    public int getMealtime() {
        return mealtime;
    }

    public void setMealtime(int mealtime) {
        this.mealtime = mealtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
}
