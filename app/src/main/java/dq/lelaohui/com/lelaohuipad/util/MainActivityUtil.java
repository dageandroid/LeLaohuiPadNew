package dq.lelaohui.com.lelaohuipad.util;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.fragement.shop.FooterActivity;
import dq.lelaohui.com.lelaohuipad.testactivity.TestMainActivity;

/**
 * Created by ThinkPad on 2016/10/17.
 */

public final class MainActivityUtil {
    private static final List<TestMainActivity.ItemModel> data=new ArrayList<>();
    private static final String[]labelarray={"订餐","订服务","购物","养生","理财","资讯","娱乐游戏"};
    private static final int []img_id={R.mipmap.myorder,R.mipmap.old_product,R.mipmap.olde_school,R.mipmap.myorder,R.mipmap.old_product,R.mipmap.olde_school,R.mipmap.myorder};

        public final static List<TestMainActivity.ItemModel> getData(){
            if(data.size()==0){
                for(int i=0;i<labelarray.length;i++){
                    TestMainActivity.ItemModel orderModel=new TestMainActivity.ItemModel();
                    orderModel.action=FooterActivity.class;
                    orderModel.lable=labelarray[i];
                    orderModel.imageId= img_id[i];
                    data.add(orderModel);
                }
            }
            return data;
        }
}
