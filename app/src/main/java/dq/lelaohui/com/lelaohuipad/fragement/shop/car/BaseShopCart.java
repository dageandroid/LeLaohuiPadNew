package dq.lelaohui.com.lelaohuipad.fragement.shop.car;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.View;
import android.widget.ListPopupWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.bean.ShoppingCarListBean;
import dq.lelaohui.com.lelaohuipad.view.BadgeView;
import dq.lelaohui.com.lelaohuipad.view.MyListPopWindowAdapter;
import dq.lelaohui.com.lelaohuipad.view.MyPoPuWindow;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;


/**
 * Created by ThinkPad on 2016/11/3.
 */
public class BaseShopCart {
    private static final String TAG="MyServerContentRecyleViewAdapter";
    private Context mContext;
    private Map<String,Integer> countCache=null;
    public Vector<ShoppingCarListBean> getData() {
        return data;
    }

    public void setCardDataChange(CardDataChange cardDataChange) {
        this.cardDataChange = cardDataChange;
    }

    private CardDataChange cardDataChange;

    private ShowTip showTip;
    private UiOperator uiOperator;
    private BadgeView badgeView=null;
    private Vector<ShoppingCarListBean>data;
    /**
     * 购物车里含有商品数量
     */
    private int cartCount;
    public BaseShopCart(Context context){
        this.mContext=context;
        countCache=new ConcurrentHashMap<String, Integer>();
        data = new Vector<>();
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

        int i = 0;
        boolean flag=true;
        for (; i < getCartSize(); i++) {
            boolean isEqulas=data.get(i).equals(bean);

            if (isEqulas) {
                int proNum = data.get(i).getProNum();
                data.get(i).setProNum(proNum + 1);
                countCache.put(bean.getKey(), data.get(i).getProNum());
                flag=false;
                break;
            }
        }
        if(flag){
            bean.setProNum(1);
            data.addElement(bean);
            countCache.put(bean.getKey(),  bean.getProNum());

        }
        if(uiOperator!=null){
            uiOperator.setPromot(String.valueOf(computerAllPrice()));
            setBadgeView();
        }

    }
    private void setBadgeView(){
        if(badgeView==null){
            if(uiOperator.getCardView()==null){
                throw new RuntimeException(getClass().getSimpleName() + "异常：getCardView is null");
            }

            badgeView=new BadgeView(mContext);
            badgeView.setGravity(Gravity.TOP|Gravity.LEFT|Gravity.CENTER);
            badgeView.setTargetView(uiOperator.getCardView());
        }
        int size=getCartSize();
        if(size<=0){
            badgeView.setHideOnNull(true);
        }else{
            badgeView.setHideOnNull(false);
        }
        badgeView.setBadgeCount(getCartSize());
        init();
    }
    public int getCartSize(){
        return data.size();
    }
    /**
     * 计算购物车里的价格
     * @return
     */
    private double computerAllPrice() {
        Log.i(TAG, "computerAllPrice: data==null||data.size()==0 is "+(data==null||data.size()==0));
        if(getCartSize()==0){
            return 0f;
        }
        float sum = 0;
       Vector<ShoppingCarListBean> temp=data;
        for (ShoppingCarListBean bean : temp) {
            sum+=bean.getProNum()*bean.getProPrice();
        }
        Log.i(TAG, "computerAllPrice: 111111 size "+(data.size()));
        return sum;
    }

    public int getShopItemCount(String key){

        if(countCache==null||countCache.size()==0||countCache.get(key)==null){
            return 0;
        }

       int count =countCache.get(key)<0?0:countCache.get(key);
        return count;
    }
    /**
     * 从购物车里删除商品
     * @param bean
     */
    public synchronized void removeShop(ShoppingCarListBean bean) {
        if (getCartSize() == 0) {
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
                    countCache.put(bean.getKey(), beanData.getProNum());
                }
                if(beanData.getProNum()<=0){
                    iter.remove();
                    countCache.remove(bean.getKey());
                    cartCount--;
                }
            }
        }
        if(data.isEmpty()){
            dismissPopuWindow();
        }
        if(uiOperator!=null){
            uiOperator.setPromot(String.valueOf(computerAllPrice()));
            setBadgeView();
        }
    }
    private void init(){
        if(uiOperator==null){
            throw new RuntimeException(getClass().getSimpleName()+":is erro 因为 uiOperator is null");
        }
        if(uiOperator!=null){

            uiOperator.getCardView().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.i(TAG, " getCardView onClick: "+data.size());
                    showPopWindow( view);
                }
            });
            uiOperator.getOrderView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data!=null){
                        RequestParam rp= uiOperator.getOrderParam(data);
                        Log.i(TAG,"TF  data.size=="+data.size());
                        LeLaohuiApp app= (LeLaohuiApp) mContext.getApplicationContext();
                        app.reqData(rp);
                    }
                }
            });
        }

    }
    /**
     * 点击底部按钮弹出pop框
     */
    MyListPopWindowAdapter myAdapter=null;
    MyPoPuWindow mlistPPW=null;
    private void showPopWindow(View v){
//        ListPopupWindow lpwindo=new ListPopupWindow(mContext);
        if (data==null||data.size()<=0){
            Snackbar.make(uiOperator.getPormotView(), "当前购物车没有数据，请您选择商品", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if( mlistPPW!=null&&mlistPPW.isShowing()){
            dismissPopuWindow();
        }else
        {
            mlistPPW=new MyPoPuWindow(mContext);
            myAdapter=new MyListPopWindowAdapter(mContext,this);
            if( this.cardDataChange!=null){
                myAdapter.setChanger(cardDataChange);
            }
            mlistPPW.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
            mlistPPW.setAnchorView(v);
            mlistPPW.show();
        }


    }
    public  void dismissPopuWindow(){
        if (mlistPPW!=null&& mlistPPW.isShowing()){
            mlistPPW.dismiss();
            mlistPPW=null;
        }
       if(cardDataChange!=null){
           cardDataChange.notifyCardDataChanger(0);
       }
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
    public interface CardDataChange{

        void notifyCardDataChanger(int posion);
        void notifyCardDataChanger();
    }
    /**
     *
     */
    public interface ShowTip {
        void isShopCartEmptyPtomot();
    }

    public interface UiOperator {
        /**
         * 更改购物车里的数量时，显示在下面提示的内容
         *
         * @param promot
         */
        void setPromot(String promot);

        /**
         * 获取底部购物车的父布局
         *
         * @return
         */
        View getParnetView();

        View getCardView();

        View getOrderView();

        View getPormotView();
        RequestParam getOrderParam(Vector<ShoppingCarListBean> data);
    }
}
