package dq.lelaohui.com.lelaohuipad.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.bean.FoodInfoData;

/**
 * Created by ZTF on 2016/11/21.
 */

public class FoodInfoAdapter extends RecyclerView.Adapter<FoodInfoAdapter.ViewHolder>{
    Context context;
    List<FoodInfoData> infoDatas;

    public FoodInfoAdapter(Context context, List<FoodInfoData> infoDatas) {
        this.context = context;
        this.infoDatas = infoDatas;
    }

    public FoodInfoAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.llh_food_cv_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public AppCompatTextView food_name;
        public AppCompatTextView food_price;
        public AppCompatTextView food_remark;
        public AppCompatTextView product_num;
        public AppCompatImageView add_product;
        public AppCompatImageView subtract_product;
        public AppCompatImageView food_img;
        public CardView card_view;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.food_name = (AppCompatTextView) rootView.findViewById(R.id.food_name);
            this.food_price = (AppCompatTextView) rootView.findViewById(R.id.food_price);
            this.food_remark = (AppCompatTextView) rootView.findViewById(R.id.food_remark);
            this.product_num = (AppCompatTextView) rootView.findViewById(R.id.product_num);
            this.add_product = (AppCompatImageView) rootView.findViewById(R.id.add_product);
            this.subtract_product = (AppCompatImageView) rootView.findViewById(R.id.subtract_product);
            this.food_img = (AppCompatImageView) rootView.findViewById(R.id.food_img);
            this.card_view = (CardView) rootView.findViewById(R.id.card_view);
        }
     public void    setData(FoodInfoData foodInfoData){
         this.food_name.setText(foodInfoData.getProName());
         this.food_price.setText("价格："+foodInfoData.getProPrice()+"元");
         this.food_remark.setText("描述："+foodInfoData.getRemark());
         this.product_num.setText("供应商："+foodInfoData.getSupplierName());
        }

    }
}
