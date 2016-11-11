package dq.lelaohui.com.lelaohuipad.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.bean.ShoppingCarListBean;
import dq.lelaohui.com.lelaohuipad.fragement.shop.car.BaseShopCart;


/**
 * 购物车显示
 */
public class MyListPopWindowAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    BaseShopCart shopCartBase;
    private String TAG=getClass().getSimpleName();

    public void setChanger(BaseShopCart.CardDataChange changer) {
        this.changer = changer;
    }

    private BaseShopCart.CardDataChange changer=null;

    public MyListPopWindowAdapter(Context context,  BaseShopCart shopCartBase) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.shopCartBase=shopCartBase;
    }

    @Override
    public int getCount() {
        return shopCartBase.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return shopCartBase.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
       final  ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.lv_popwindow_item, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) view.findViewById(R.id.pro_name);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            holder.tv_num = (TextView) view.findViewById(R.id.tv_num);
            holder.im_reduce = (ImageView) view.findViewById(R.id.im_reduce);
            holder.im_add = (ImageView) view.findViewById(R.id.im_add);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
       final ShoppingCarListBean carListBean= (ShoppingCarListBean) getItem(i);
        holder.tv_name.setText(carListBean.getProName());
        holder.tv_price.setText(carListBean.getProPrice()+"");
        holder.tv_num.setText(carListBean.getProNum()+"");
        holder.im_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopCartBase.removeShop(carListBean);
                int buyNum=carListBean.proNum;;
                holder.tv_num.setText(buyNum+"");
                if(buyNum<=0){
                    notifyDataSetChanged();
                }
                Log.i(TAG, "onClick: remove "+carListBean.posion);
                if(changer !=null){
                    changer.notifyCardDataChanger(carListBean.posion);
                }

            }
        });
        holder.im_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopCartBase.addShop(carListBean);
                int buyNum=carListBean.proNum;;
                holder.tv_num.setText(""+ (buyNum));
                  Log.i(TAG, "onClick: add "+carListBean.posion);

                if(changer !=null){
                    changer.notifyCardDataChanger(carListBean.posion);

                }
            }
        });
        return view;
    }

    private static class ViewHolder {
        TextView tv_name, tv_price, tv_num;
        ImageView im_reduce, im_add;
    }
}
