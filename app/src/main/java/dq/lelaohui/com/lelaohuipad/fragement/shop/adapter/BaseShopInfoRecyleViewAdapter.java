package dq.lelaohui.com.lelaohuipad.fragement.shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import org.greenrobot.greendao.AbstractDao;

import java.lang.ref.SoftReference;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.bean.ServerCartBean;
import dq.lelaohui.com.lelaohuipad.fragement.shop.car.BaseShopCart;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.SerInitProPackDao;

/**
 * Created by ThinkPad on 2016/11/26.
 */

public  class BaseShopInfoRecyleViewAdapter extends BaseDataBaseAdapter<BaseShopInfoRecyleViewAdapter.ViewHolder> implements  BaseShopCart.CardDataChange{
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
        super(context, c);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setDao( AbstractDao dao) {
        softReference = new SoftReference< >(dao);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        {
            onBindViewHolder(holder,cursor,0);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor, int postion) {
        daoToEntity(holder, cursor, postion);
    }

    protected void daoToEntity(ViewHolder holder, Cursor cursor,int postion) {
        if (softReference != null) {
            SerInitProPackDao dao = (SerInitProPackDao) getDao();
            if (dao != null) {
                Log.i("cursor tf:", "" + cursor.getColumnCount());
                SerInitProPack serInitProPack = dao.readEntity(cursor, 0);
                holder.setData(serInitProPack,postion);
            }
        }

    }
    protected AbstractDao getDao(){
        if(softReference != null){
            return softReference.get();
        }
        return null;
    }



    @Override
    public View getItemView() {
        return layoutInflater.inflate(R.layout.llh_food_cv_item, null);
    }

    @Override
    public BaseShopInfoRecyleViewAdapter.ViewHolder onCreatViewHolder(View view) {
        return new BaseShopInfoRecyleViewAdapter.ViewHolder(view);
    }
    @NonNull
    public ServerCartBean getServerCartBean(BaseBean baseBean,int position) {
        SerInitProPack serInitProPack= (SerInitProPack) baseBean;
        int proId = serInitProPack.getPackageId();
        String proName = serInitProPack.getPackageName();
        double proPrice = serInitProPack.getPrice();
        int proNum = serInitProPack.getSaleNums();
        ServerCartBean shoppingCarListBean = new ServerCartBean(proName, proPrice, proNum, proId, serInitProPack.getReamark() );
        shoppingCarListBean.setBean(serInitProPack);
        shoppingCarListBean.posion = position;
        return shoppingCarListBean;
    }

    @Override
    public void notifyCardDataChanger(int posion) {
        notifyItemChanged(posion);
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
//            SerInitProPack serInitProPack= (SerInitProPack) baseBean;

        final  ServerCartBean shoppingCarListBean=getServerCartBean(baseBean,position);

            final String key=shoppingCarListBean.getKey();
            Log.i(TAG, "setData: "+key);
            food_name.setText(shoppingCarListBean.getProName());
            food_price.setText("价格： ￥" + shoppingCarListBean.getProPrice() + "元");
            food_remark.setText("详情：" + shoppingCarListBean.getProDetail());
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
