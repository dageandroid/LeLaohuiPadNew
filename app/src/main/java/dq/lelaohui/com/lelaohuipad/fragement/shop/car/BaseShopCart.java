package dq.lelaohui.com.lelaohuipad.fragement.shop.car;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ListPopupWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.bean.ShoppingCarListBean;
import dq.lelaohui.com.lelaohuipad.view.MyListPopWindowAdapter;
import dq.lelaohui.com.lelaohuipad.view.MyPoPuWindow;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;

/**
 * Created by ThinkPad on 2016/11/3.
 */
public class BaseShopCart {
    private ListPopupWindow popupWindow;
    private Context mContext;
    private Vector<ShoppingCarListBean> data;


    private ShowTip showTip;
    private UiOperator uiOperator;
    private View orderView;
    /**
     * 购物车里含有商品数量
     */
    private int cartCount;
    public BaseShopCart(Context context){
        this.mContext=context;
    }
    /**
     * 添加购物车
     * 逻辑判断：
     * 1、购物车含有相同产品，则购物车里此商品数量加一
     * 2、如果购物不含有相同产品，则直接添加此商品到购物车。购物车含有的商品数量加一处理
     *
     * @param bean
     */
    public synchronized  void addShop(ShoppingCarListBean bean) {//添加购物车。如果购物车里有相同商品，则给此商品的数量加一，没有就直接添加
        if (data == null) {
            data = new Vector<>();
        }
        int i = 0;
        boolean flag=true;
        for (; i < data.size(); i++) {
            if (data.get(i).equals(bean)) {
                int proNum = data.get(i).getProNum();
                data.get(i).setProNum(proNum + 1);
                flag=false;
                break;
            }
        }
        if(flag){
            data.add(bean);
            cartCount++;
        }
        if(uiOperator!=null){
            uiOperator.setPromot(String.valueOf(computerAllPrice()));
        }
    }

    /**
     * 计算购物车里的价格
     * @return
     */
    private double computerAllPrice() {
        if(data==null||data.size()==0){
            return 0f;
        }
        float sum = 0;
        for (ShoppingCarListBean bean : data
                ) {
            sum+=bean.getProNum()*bean.getProPrice();
        }
        return sum;
    }

    /**
     * 从购物车里删除商品
     * @param bean
     */
    public synchronized void removeShop(ShoppingCarListBean bean) {
        if (data == null || data.size() == 0) {
            if (showTip != null) {//如果自定义了购物车的提示信息展示内容，就设置showTip这个接口
                showTip.isShopCartEmptyPtomot();
            } else {
                if (mContext == null) {
                    throw new RuntimeException(getClass().getSimpleName() + "异常：Context is null");
                }
                if (uiOperator == null) {
                    throw new RuntimeException(getClass().getSimpleName() + "异常：uiOperator is null");
                }
                Snackbar.make(uiOperator.getPormotView(), "当前购物车没有数据，请您选择商品", Snackbar.LENGTH_SHORT).show();
            }
        }else {
            Iterator iter=data.iterator();
            SparseIntArray sa=new SparseIntArray();
            while(iter.hasNext()){
                ShoppingCarListBean beanData= (ShoppingCarListBean) iter.next();
                if(beanData.equals(bean)){
                    beanData.setProNum(beanData.getProNum()-1);
                }
                if(beanData.getProNum()<=0){
                    iter.remove();
                }
            }
        }
        if(uiOperator!=null){
            uiOperator.setPromot(String.valueOf(computerAllPrice()));
        }
    }
    public void init(){
        if(uiOperator==null){
            throw new RuntimeException(getClass().getSimpleName()+":is erro 因为 uiOperator is null");
        }
        if(uiOperator!=null){
            uiOperator.getCardView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopWindow();
                }
            });
            uiOperator.getOrderView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   RequestParam rp= uiOperator.getOrderParam(data);
                  LeLaohuiApp app= (LeLaohuiApp) mContext.getApplicationContext();
                    app.reqData(rp);
                }
            });
        }

    }
    /**
     * 点击底部按钮弹出pop框
     */
    MyListPopWindowAdapter myAdapter=null;

    private void showPopWindow(){
//        ListPopupWindow lpwindo=new ListPopupWindow(mContext);
        MyPoPuWindow mlistPPW=new MyPoPuWindow(mContext);
        myAdapter=new MyListPopWindowAdapter(mContext,data);
        mlistPPW.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    public UiOperator getUiOperator() {
        return uiOperator;
    }

    public void setUiOperator(UiOperator uiOperator) {
        this.uiOperator = uiOperator;
    }

    public ShowTip getShowTip() {
        return showTip;
    }

    public void setShowTip(ShowTip showTip) {
        this.showTip = showTip;
    }
    /**
     *
     */
    public interface ShowTip {
        public void isShopCartEmptyPtomot();
    }

    public interface UiOperator {
        /**
         * 更改购物车里的数量时，显示在下面提示的内容
         *
         * @param promot
         */
        public void setPromot(String promot);

        /**
         * 获取底部购物车的父布局
         *
         * @return
         */
        public View getParnetView();

        public View getCardView();

        public View getOrderView();

        public View getPormotView();
        public RequestParam getOrderParam(Vector<ShoppingCarListBean> data);
    }
}
