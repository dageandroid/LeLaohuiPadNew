package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.FoodInfoAdapter;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FoodInfoData;

/**
 * Created by ZTF on 2016/11/21.
 * 早餐
 */

public class BreakFastActivity extends Fragment   {

    private RecyclerView foot_content;
    private SwipeRefreshLayout get_data_refresh;

    public void setFoodInfoCursor(Cursor foodInfoCursor) {
        this.foodInfoCursor = foodInfoCursor;
    }

    //    FoodInfoAdapter foodInfoAdapter = null;
    private Cursor foodInfoCursor=null;

    public void setFoodInfoDataList(List<FoodInfoData> foodInfoDataList) {
//        if(foodInfoAdapter!=null){
////            foodInfoAdapter.setInfoDatas(foodInfoDataList);
//            foodInfoAdapter.notifyDataSetChanged();
//        }
    }


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
//        foodInfoDataList = FoodActivity.foodInfoDatas;
//        if (foodInfoDataList == null && foodInfoDataList.size() == 0) {
//            Toast.makeText(this.getActivity(), "餐品信息为空", Toast.LENGTH_SHORT).show();
//            return;
//        } else {
//            foodInfoAdapter = new FoodInfoAdapter();
//            foodInfoAdapter.setContext(getContext());
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
//            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            foot_content.setLayoutManager(linearLayoutManager);
//            foot_content.setAdapter(foodInfoAdapter);
//            foodInfoAdapter.notifyDataSetChanged();
//        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
