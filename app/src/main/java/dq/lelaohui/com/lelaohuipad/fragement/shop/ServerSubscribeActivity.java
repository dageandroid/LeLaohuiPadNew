package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.ServerSubscribeControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;

/**
 * Created by ZTF on 2016/11/25.
 * 服务预约
 */

public class ServerSubscribeActivity extends LeLaoHuiBaseActivity {

    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private TabLayout server_subscribe_tab;
    private ViewPager ctrl_server_subscribe;
    private ServerSubscribeControler serverSubscribeControler=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverSubscribeControler=(ServerSubscribeControler)getControler();
        initView();
        initViewPager();
    }

    public  void initView(){
        reight_tv= (AppCompatTextView) findViewById(R.id.reight_tv);
        left_btn= (AppCompatImageButton) findViewById(R.id.left_btn);
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        title_tv= (AppCompatTextView) findViewById(R.id.title_tv);
        title_tv.setText("服务预约");
        server_subscribe_tab= (TabLayout) findViewById(R.id.server_subscribe_tab);
    }
    public  void  initViewPager(){
        if(ctrl_server_subscribe==null){
            ctrl_server_subscribe= (ViewPager) findViewById(R.id.ctrl_server_subscribe);
        }
        initTitleData();
        server_subscribe_tab.setupWithViewPager(ctrl_server_subscribe);
        server_subscribe_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    /**
     * 初始化TabLayout
     *
     * 测试
     */
    public void initTitleData() {
        List<String> list_title = new ArrayList<>();
        list_title.add("客户预约");
        list_title.add("预约查询");
        list_title.add("完成任务");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SerSubscribeStockActivity());
        fragments.add(new SerSubscribeStockActivity());
        fragments.add(new SerSubscribeStockActivity());
        setViewPager(list_title,fragments);
    }
    private void setViewPager( List<String>   list_title,List<Fragment> fragments) {
        ctrl_server_subscribe.setAdapter(new ServerSubscribeActivity.PagerAdapter(getSupportFragmentManager(), list_title, fragments));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    /**
     * 页面切换
     */
    public class PagerAdapter extends FragmentPagerAdapter {
        List<String> titles;


        List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
            super(fm);
            this.titles = titles;
            this.fragments = fragments;
        }
        public PagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
        public void setFragments(List<Fragment> fragments) {
            this.fragments = fragments;
        }

        public void setTitles(List<String> titles) {
            this.titles = titles;
        }

    }
    @Override
    public IControler getControler() {
        return  ServerSubscribeControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_server_subscribe;
    }

    @Override
    public void usable() {

    }

}
