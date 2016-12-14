package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.bean.ServerCartBean;
import dq.lelaohui.com.lelaohuipad.controler.FootterControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.adapter.BaseShopInfoRecyleViewAdapter;
import dq.lelaohui.com.lelaohuipad.fragement.shop.car.BaseShopCart;
import dq.lelaohui.com.nettylibrary.socket.NetManager;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FoodInfoData;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FoodInfoDataDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/11/21.
 * 早餐
 */

public class BreakFastActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener,NetManager.ProgressBarListener {

    private ListView foot_content;
    private SwipeRefreshLayout get_data_refresh;
    private FootInfoCursor footInfoCursor = null;

    public void setFootInfoCursor(FootInfoCursor footInfoCursor) {
        this.footInfoCursor = footInfoCursor;
    }


    MyFoodInfoAdapter myFoodInfoAdapter = null;

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
        foot_content = (ListView) v.findViewById(R.id.foot_content);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        foot_content.setLayoutManager(linearLayoutManager);
        myFoodInfoAdapter = new MyFoodInfoAdapter(getContext(), null);
        myFoodInfoAdapter.setDao(footInfoCursor.getDao().get(FoodInfoData.class));
//        footInfoCursor.getShopCart().setCardDataChange(this);
        myFoodInfoAdapter.setShopCartBase(footInfoCursor.getShopCart());
        foot_content.setAdapter(myFoodInfoAdapter);
        get_data_refresh.setOnRefreshListener(this);
        get_data_refresh.setRefreshing(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void notifyDataChanger() {
        if (this.footInfoCursor != null) {
            myFoodInfoAdapter.changeCursor(this.footInfoCursor.getCuror());
        }
    }
    public void notifyDataChanger(Cursor cursor) {
        if (cursor != null) {
            myFoodInfoAdapter.changeCursor(cursor);
        }
    }

    public void reset() {
        myFoodInfoAdapter.changeCursor(null);
    }
    @Override
    public void onRefresh() {

    }

    @Override
    public void showProgress() {
            if(get_data_refresh!=null){
                get_data_refresh.setRefreshing(true);
            }
    }

    @Override
    public void hideProgress() {
        if(get_data_refresh!=null){
            get_data_refresh.setRefreshing(false);
        }
    }


    /**
     * 显示服务信息
     */
    public class MyFoodInfoAdapter extends BaseShopInfoRecyleViewAdapter {
        public MyFoodInfoAdapter(Context context, Cursor c) {
            super(context, c);
        }
        protected void daoToEntity(ViewHolder holder, Cursor cursor,int postion) {
            FoodInfoData foodInfoData=readEntity( cursor,  0);
            holder.setData(foodInfoData,postion);
        }
        public FoodInfoData readEntity(Cursor cursor, int offset) {
            FoodInfoData entity = new FoodInfoData( //
                    cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
                    cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // cateId
                    cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cateName
                    cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // proId
                    cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // proName
                    cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // supplierId
                    cursor.getInt(offset + 6), // proPrice
                    cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // proPic
                    cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // mealTime
                    cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // mealType
                    cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // remark
                    cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // supplierName
                    cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // isScore
                    cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // supplierType
            );
            return entity;
        }
        @NonNull
        public ServerCartBean getServerCartBean(BaseBean baseBean, int position) {
            FoodInfoData serInitProPack= ( FoodInfoData) baseBean;
            int proId = Integer.parseInt(TextUtils.isEmpty(serInitProPack.getProId())?"0":serInitProPack.getProId());
            String proName = serInitProPack.getProName();
            double proPrice = serInitProPack.getProPrice();
            int proNum = -1;
            ServerCartBean shoppingCarListBean = new ServerCartBean(proName, proPrice, proNum, proId,serInitProPack.getRemark() );
            shoppingCarListBean.setBean(serInitProPack);
            shoppingCarListBean.uniqueKey=serInitProPack.getUnineqKey();
            shoppingCarListBean.posion = position;
            return shoppingCarListBean;
        }
        public void setShopCartBase(BaseShopCart shopCartBase) {
            shopCartBase.setCardDataChange(this);
           super.setShopCartBase(shopCartBase);
        }

    }

    public interface FootInfoCursor {
        Cursor getCuror();

        BaseDaoOperator getDao();

        FootterControler getFootControler();

        BaseShopCart getShopCart();
    }

}
