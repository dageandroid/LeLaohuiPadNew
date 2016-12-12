package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.bean.FoodOrederData;

/**
 * Created by ZTF on 2016/12/9.
 * 设置上传餐品数据
 */

public class UploadFoodOrderInfo {
    public  static ArrayList<Bundle> foodOrderInfo(List<FoodOrederData> orderFoodList){
        ArrayList<Bundle> data = new ArrayList<Bundle>();
        for (int i = 0; i < orderFoodList.size(); i++) {
            FoodOrederData orderFoodData = orderFoodList.get(i);
            Bundle bundle = new Bundle();
            bundle.putString("total",
                    String.valueOf(orderFoodData.getTotal()));
            ArrayList<Bundle> listInfo = new ArrayList<Bundle>();
            for (int j = 0; j < orderFoodList.get(i).getSupplierInfo()
                    .size(); j++) {
                Bundle bundleInfo = new Bundle();
                bundleInfo.putString("supplierId", orderFoodData
                        .getSupplierInfo().get(j).getSupplierId());
                bundleInfo.putString("supplierName", orderFoodData
                        .getSupplierInfo().get(j).getSupplierName());
                bundleInfo.putInt("supplierType", orderFoodData
                        .getSupplierInfo().get(j).getSupplierType());
                bundleInfo.putInt("supplierQty", orderFoodData
                        .getSupplierInfo().get(j).getSupplierQty());
                bundleInfo.putInt("distrPrice", orderFoodData
                        .getSupplierInfo().get(j).getDistrPrice());
                bundleInfo.putString(
                        "supplierAmt",
                        String.valueOf(orderFoodData.getSupplierInfo()
                                .get(j).getSupplierAmt()));
                ArrayList<Bundle> bundleInfoList = new ArrayList<Bundle>();
                for (int j2 = 0; j2 < orderFoodList.get(i)
                        .getSupplierInfo().get(j).getProduct().size(); j2++) {
                    Bundle bundProduct = new Bundle();
                    bundProduct.putInt("cateId", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getCateId());
                    bundProduct.putInt("mealTime", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getMealTime());
                    bundProduct.putInt("mealType", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getMealType());
                    bundProduct.putString(
                            "proPrice",
                            String.valueOf(orderFoodData.getSupplierInfo()
                                    .get(j).getProduct().get(j2)
                                    .getProPrice()));
                    bundProduct.putString("cateName", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getCateName());
                    bundProduct.putString("proId", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getProId());
                    bundProduct.putString("proName", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getProName());
                    bundProduct.putString("proNum", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getProNum());
                    bundProduct.putString("proPic", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getProPic());
                    bundProduct.putString("supplierId", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getSupplierId());
                    bundProduct.putString("remark", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getRemark());
                    bundProduct.putString("supplierName", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getSupplierName());
                    bundProduct.putString("supplierType", orderFoodData
                            .getSupplierInfo().get(j).getProduct().get(j2)
                            .getSupplierType());
                    bundleInfoList.add(bundProduct);
                }
                bundleInfo.putSerializable("product", bundleInfoList);
                listInfo.add(bundleInfo);
            }
            bundle.putSerializable("supplierInfo", listInfo);
            data.add(bundle);
        }

        return  data;
    }

}
