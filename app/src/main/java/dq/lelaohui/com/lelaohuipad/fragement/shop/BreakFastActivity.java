package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dq.lelaohui.com.lelaohuipad.R;

/**
 * Created by ZTF on 2016/11/21.
 * 早餐
 */

public class BreakFastActivity extends Fragment {

    private RecyclerView foot_content;
    private SwipeRefreshLayout get_data_refresh;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
        foot_content = (RecyclerView) v.findViewById(R.id.foot_content);
        get_data_refresh = (SwipeRefreshLayout) v.findViewById(R.id.get_data_refresh);
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
