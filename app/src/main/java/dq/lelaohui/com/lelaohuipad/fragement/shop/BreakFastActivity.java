package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.SoftReference;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.adapter.FoodInfoAdapter;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FoodInfoData;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FoodInfoDataDao;

/**
 * Created by ZTF on 2016/11/21.
 * 早餐
 */

public class BreakFastActivity extends Fragment   {

    private RecyclerView foot_content;
    private SwipeRefreshLayout get_data_refresh;

    public void setFoodInfoCursor(Cursor foodInfoCursor) {
        if (foodInfoCursor!=null){
            myFoodInfoAdapter.setCursor(foodInfoCursor);
            myFoodInfoAdapter.notifyDataSetChanged();
        }
    }
    private Cursor foodInfoCursor=null;
    MyFoodInfoAdapter myFoodInfoAdapter=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.llh_food_content, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
            get_data_refresh = (SwipeRefreshLayout) v.findViewById(R.id.get_data_refresh);
            foot_content = (RecyclerView) v.findViewById(R.id.foot_content);
            myFoodInfoAdapter = new MyFoodInfoAdapter();
            myFoodInfoAdapter.setContext(getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            foot_content.setLayoutManager(linearLayoutManager);
            foot_content.setAdapter(myFoodInfoAdapter);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 显示服务信息
     */
    public  class  MyFoodInfoAdapter extends BaseDataBaseAdapter<MyFoodInfoAdapter.ViewHolder> {
    private SoftReference<FoodInfoDataDao> softReference = null;
    private LayoutInflater layoutInflater=null;
    private String TAG="MyFoodTypeRecyleViewAdapter";
    private Context context;
    public void setContext(Context context) {
         this.context = context;
    }

    private Cursor cursor=null;
    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public void onBindViewHolder(MyFoodInfoAdapter.ViewHolder holder, Cursor cursor) {
        if (softReference != null) {
            FoodInfoDataDao dao = softReference.get();
            if (dao != null) {
                FoodInfoData pc = dao.readEntity(cursor, 0);
                holder.setData(pc);
            }
        }
    }
    public    MyFoodInfoAdapter(){

    }
    public MyFoodInfoAdapter(Context context, Cursor c) {
        super(context, c);
        layoutInflater= LayoutInflater.from(context);
        this.cursor=c;
    }
    @Override
    public View getItemView() {
        return layoutInflater.inflate(R.layout.llh_food_cv_item,null);
    }

    @Override
    public ViewHolder onCreatViewHolder(View view) {
        return new ViewHolder(view);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
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
//         this.product_num.setText("供应商："+foodInfoData.getSupplierName());
        }

    }
}

}
