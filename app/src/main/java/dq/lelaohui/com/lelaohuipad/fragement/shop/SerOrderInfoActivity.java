package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.BaseOrderInfoActivity;
import dq.lelaohui.com.lelaohuipad.base.BaseOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.BaseOrderCate;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfo;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.ServerOrderPayment;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.lelaohuipad.controler.SerOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoActivity extends BaseOrderInfoActivity {

    @Override
    protected void initPageData() {
        if (getIntent() != null) {
            SerOrderInfoData infoData = getIntent().getParcelableExtra("infoData");
            setInfoData(infoData);
            if (infoData!=null){
                String orderCode = infoData.getSerOrderInfo().getOrderCode();
                int payStatus = infoData.getSerOrderInfo().getPayStatus();
                setOrderInfoStatus(orderCode,infoData.getSerOrderInfo().getAmountPayable(), payStatus);
            }
        }
    }

    @Override
    protected List<OrderInfoBean> getOrderInfoList() {
        SerOrderInfoData infoData= (SerOrderInfoData) getInfoData();
        if (infoData == null) {
            return null;
        }
        List<OrderInfoBean> orderInfoBeanList=new ArrayList<>();
        List<SerOrderInfoData.SerOrderInfoDetailBeanListBean> temp=infoData.getSerOrderInfoDetailBeanList();
        OrderInfoBean orderInfoBean=null;
        for(SerOrderInfoData.SerOrderInfoDetailBeanListBean serOrderInfoDetailBeanListBean:temp){
            orderInfoBean=new OrderInfoBean();
            orderInfoBean.setNum(serOrderInfoDetailBeanListBean.getSerOrderInfoDetail().getSerNum());
            orderInfoBean.setOrderCode(serOrderInfoDetailBeanListBean.getSerOrderInfoDetail().getOrderCode());
            orderInfoBean.setCateName(serOrderInfoDetailBeanListBean.getSerOrderInfoDetail().getPackageName());
            orderInfoBean.setPrice(serOrderInfoDetailBeanListBean.getSerOrderInfoDetail().getPrice()+"");
            orderInfoBean.setOrderCate(serOrderInfoDetailBeanListBean);
            orderInfoBeanList.add(orderInfoBean);
        }
        return orderInfoBeanList;
    }

    @Override
    public IControler getControler() {
        return SerOrderInfoControler.getControler();
    }

    @Override
    protected boolean upLoadFinshOrder(String action) {
        return ServiceNetContant.ServiceResponseAction.UPLOAD_SERVER_ORDER_PAYMENY.equals(action);
    }

    @Override
   protected BaseOrderCate getBaseOrderCate() {
        SerOrderInfoData infoData= (SerOrderInfoData) getInfoData();
        SerOrderInfoData.SerOrderInfoBean serOrderInfo = infoData.getSerOrderInfo();
        String address = getAddressType();
        serOrderInfo.setContactAddress(address);
        serOrderInfo.setCustomerName(getUserName());
        serOrderInfo.setCustomerPhone(getUserPhone());
        serOrderInfo.setCustomerId(Long.valueOf( SysVar.getInstance().getUserId()));
        if ("3".equals( SysVar.getInstance().getOrgId())) {
            serOrderInfo.setOrgId(Integer.valueOf( SysVar.getInstance().getOrgId()));
            serOrderInfo.setOrgTypeId(Integer.valueOf( SysVar.getInstance().getOrgType()));
            serOrderInfo.setOrgName( SysVar.getInstance().getOrgName());
        } else {
            serOrderInfo.setSupplierId(Long.valueOf( SysVar.getInstance().getOrgId()));
            serOrderInfo.setSupplierTypeId(Integer.valueOf( SysVar.getInstance().getOrgType()));
            serOrderInfo.setSupplierName( SysVar.getInstance().getOrgName());
            serOrderInfo.setOrgId(Integer.valueOf( SysVar.getInstance().getCenterId()));
            serOrderInfo.setOrgTypeId(Integer.valueOf(3));
            serOrderInfo.setOrgName( SysVar.getInstance().getCenterName());
        }
        serOrderInfo.setOrderPersionId(Long.parseLong( SysVar.getInstance().getUserId()));
        serOrderInfo.setOrderPersionName( SysVar.getInstance().getUserName());
        serOrderInfo.setPayStyle(1);
        infoData.setSerOrderInfo(serOrderInfo);
        return  infoData;
    }

}
