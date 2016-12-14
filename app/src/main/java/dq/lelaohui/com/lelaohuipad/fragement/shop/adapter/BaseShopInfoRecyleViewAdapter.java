package dq.lelaohui.com.lelaohuipad.fragement.shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.greendao.AbstractDao;

import java.lang.ref.SoftReference;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.bean.ServerCartBean;
import dq.lelaohui.com.lelaohuipad.fragement.shop.car.BaseShopCart;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;

/**
 * Created by ThinkPad on 2016/11/26.
 */

public  class BaseShopInfoRecyleViewAdapter extends CursorAdapter implements  BaseShopCart.CardDataChange{
    private LayoutInflater layoutInflater = null;
    private String TAG = "MyServerContentRecyleViewAdapter";
    private SoftReference< AbstractDao> softReference = null;
    private Context context;

    public void setShopCartBase(BaseShopCart shopCartBase) {
        this.shopCartBase = shopCartBase;
        this.shopCartBase.setCardDataChange(this);
    }

    private BaseShopCart shopCartBase;

    public BaseShopInfoRecyleViewAdapter(Context context, Cursor c) {
        super(context, c,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view=getItemView();
        ViewHolder holder=new ViewHolder(view);
        view.setTag(holder);
        return  view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Object tag=view.getTag();
        if (tag != null) {
            ViewHolder holder = (ViewHolder) tag;
            daoToEntity( holder, cursor,-1);
        }
    }

    public void setDao( AbstractDao dao) {
        softReference = new SoftReference< >(dao);
    }


    protected void daoToEntity(ViewHolder holder, Cursor cursor,int postion) {
        SerInitProPack serInitProPack = (SerInitProPack) readEntity( cursor,postion);
        holder.setData(serInitProPack,postion);
        Log.i(TAG, "daoToEntity:serInitProPack toString= "+serInitProPack .toString());
    }
    protected AbstractDao getDao(){
        if(softReference != null){
            return softReference.get();
        }
        return null;
    }



    public View getItemView() {
        return layoutInflater.inflate(R.layout.llh_food_cv_item, null);
    }

    public BaseShopInfoRecyleViewAdapter.ViewHolder onCreatViewHolder(View view) {
        return new BaseShopInfoRecyleViewAdapter.ViewHolder(view);
    }
    @NonNull
    public ServerCartBean getServerCartBean(BaseBean baseBean,int position) {
        SerInitProPack serInitProPack= (SerInitProPack) baseBean;
        int proId = serInitProPack.getPackId();
        String proName = serInitProPack.getPackName();
        double proPrice = serInitProPack.getPrice();
        int proNum = serInitProPack.getSaleNums();
       String remark= serInitProPack.getReamark();
        Log.i(TAG,"serInitProPack=="+serInitProPack.toString());
        Log.i(TAG,"proName=="+proName+" proPrice=="+proPrice+" proNum=="+proNum+" remark:=="+serInitProPack.getReamark() );
        ServerCartBean shoppingCarListBean = new ServerCartBean(proName, proPrice, proNum, proId, remark );
        shoppingCarListBean.setBean(serInitProPack);
        shoppingCarListBean.posion = position;
        return shoppingCarListBean;
    }
    public BaseBean readEntity(Cursor cursor, int offset1) {
        int offset=0;
        SerInitProPack entity = new SerInitProPack( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getInt(offset + 1), // packId
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // packName
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // orgName
                cursor.getInt(offset + 4), // orgTypeId
                cursor.getInt(offset + 5), // orgId
                cursor.getInt(offset + 6), // serviceCateId
                cursor.getInt(offset + 7), // isPro
                cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // pictureUrl
                cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // reamark
                cursor.getInt(offset + 10), // saleNums
                cursor.getInt(offset + 11), // price
                cursor.getInt(offset + 12) // serNum
        );
        return entity;
    }
    @Override
    public void notifyCardDataChanger(int posion) {
        notifyDataSetChanged();
//        notifyItemChanged(posion);
    }

    @Override
    public void notifyCardDataChanger() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public AppCompatImageView food_img;
        public AppCompatTextView food_name;
        public AppCompatTextView food_price;
        public AppCompatTextView food_remark;
        public AppCompatTextView product_num;
        public View add_product;
        public View subtract_product;
        public int position;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.food_img = (AppCompatImageView) rootView.findViewById(R.id.food_img);
            this.food_name = (AppCompatTextView) rootView.findViewById(R.id.food_name);
            this.food_price = (AppCompatTextView) rootView.findViewById(R.id.food_price);
            this.food_remark = (AppCompatTextView) rootView.findViewById(R.id.food_remark);
            this.product_num = (AppCompatTextView) rootView.findViewById(R.id.product_num);
            this.add_product =  rootView.findViewById(R.id.add_product);
            this.add_product.setFocusable(true);
            this.add_product.setFocusableInTouchMode(true);
            this.subtract_product = rootView.findViewById(R.id.subtract_product);
            this.subtract_product .setFocusable(true);
            this.subtract_product .setFocusableInTouchMode(true);
        }

        public void setData(final  BaseBean baseBean,final int position) {
            final  ServerCartBean shoppingCarListBean=getServerCartBean(baseBean,position);
            final String key=shoppingCarListBean.getKey();
            Log.i(TAG, "setData: "+key);
            food_name.setText(shoppingCarListBean.getProName());

            Log.i(TAG,"shoppingCarListBean.getProName =="+shoppingCarListBean.getProName());
            Log.i(TAG,"shoppingCarListBean.getProPrice =="+shoppingCarListBean.getProPrice());
            Log.i(TAG,"shoppingCarListBean.getProDetail：" + shoppingCarListBean.getProDetail());

            food_price.setText("价格： ￥" + shoppingCarListBean.getProPrice() + "元");
            food_remark.setText("详情：" + shoppingCarListBean.getProDetail());
            Log.i(TAG," shopCartBase is null：" + (shopCartBase==null));
            if (shopCartBase == null) {
                return;
            }
            int count = shopCartBase.getShopItemCount(shoppingCarListBean.getKey());
            product_num.setText("" + count);
            add_product.setOnClickListener(new View.OnClickListener() {

                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View v) {
                    ServerCartBean shoppingCarListBean=getServerCartBean(baseBean,position);
                    shopCartBase.addShop( shoppingCarListBean);
                    int count = shopCartBase.getShopItemCount(shoppingCarListBean.getKey());
                    Log.i(TAG, "onClick: " + count+",key="+shoppingCarListBean.getKey());
                    product_num.setText("" + (count));

                }
            });
            subtract_product.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ServerCartBean shoppingCarListBean=getServerCartBean(baseBean,position);
                    shopCartBase.removeShop(shoppingCarListBean);
                    int count = shopCartBase.getShopItemCount(shoppingCarListBean.getKey());
                    product_num.setText("" + (count));
                    Log.i(TAG, "onClick: " + count+",key="+shoppingCarListBean.getKey());
                }
            });
        }


    }

}
