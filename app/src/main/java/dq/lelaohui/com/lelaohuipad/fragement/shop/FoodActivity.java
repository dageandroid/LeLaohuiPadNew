package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.adapter.FoodTimeSpinnerAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.FootterControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FoodInfoData;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FootCateBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FoodInfoDataDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FootCateBeanDao;


/**
 * Created by ZTF on 2016/11/18.
 */

public class FoodActivity extends LeLaoHuiBaseActivity implements  LoaderManager.LoaderCallbacks<Cursor> {
    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private AppCompatSpinner select_time;
    private RecyclerView food_type;
    private TabLayout sliding_tabs;
    private ViewPager viewpager;
    private ImageView show_pp;
    private AppCompatTextView shopping_num_txt;
    private FrameLayout shopping_num;
    private AppCompatTextView shopping_product_price;
    private AppCompatButton upload_shopping_car;
    private FootterControler footterControler = null;
    private FoodTimeSpinnerAdapter spinnerAdapter = null;
    private String[] dataStringArray = new String[3];//时间数组
    private final static String TODAY_FOOD = "0";//餐品选择时间
    private final static String TOMORROW_FOOD = "1";
    private final static String POSTNATAL_FOOD = "2";
    private String isScope = TODAY_FOOD;//是否选择
    private final static String BREAK_FOOD = "1";
    private String curMealTime = BREAK_FOOD;//当前吃饭时间
    private static final String TAG = "FoodActivity";
    private SysVar var = null;
    private int mealTime = 1;//早中晚时间标示
    private String curFoodType;//当前食物类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footterControler = (FootterControler) getControler();
        var = SysVar.getInstance();
        initView();
        setSelectTime();
        initFootType();
        initInfoDetailView();
    }
    MyFoodTypeRecyleViewAdapter footCateAdapter =null;
    /**
     * 初始化左侧菜单
     */
    private void initFootType() {
        if(food_type==null){
            food_type = (RecyclerView) findViewById(R.id.food_type);
        }

        Cursor cursor =footterControler.getFoodTypeCursor(isScope);
        footCateAdapter =new MyFoodTypeRecyleViewAdapter(this,cursor);
//       if(cursor.getCount()!=0){
//           footCateAdapter.setDao( FootCateBeanDao) footterControler.getBaseDaoOperator().get(FootCateBean.class)));
//       }

        final FootCateBeanDao footCateBeanDao=     (FootCateBeanDao) footterControler.getBaseDaoOperator().get(FootCateBean.class);
        footCateAdapter.setDao(footCateBeanDao);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        food_type.setAdapter(  footCateAdapter);
        food_type.setLayoutManager(linearLayoutManager);
        getSupportLoaderManager().initLoader(0, null, this);
        footterControler.doQueryFoodInfo(String.valueOf(0), var.getUserId());

        footCateAdapter.setmOnItemClickListener(new BaseDataBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Cursor c) {
                FootCateBean footCateBean= footCateBeanDao.readEntity(c,0);
                if (footCateBean!=null){
                    curFoodType=  footCateBean.getCateName();
                }
            }
        });

    }


    /**
     * 设置食品详细页面
     */
    private void initInfoDetailView() {
        if(viewpager==null){
            viewpager = (ViewPager) findViewById(R.id.viewpager);
        }
        initTitleData();
        sliding_tabs.setupWithViewPager(viewpager);
        sliding_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                initFoodInfoListData(position,position+1);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setViewPager( List<String>   list_title,List<Fragment> fragments) {
        viewpager.setAdapter(new PagerAdapter(getSupportFragmentManager(), list_title, fragments));
    }

    /**
     * 设置日期选择spinner控件
     */
    private void setSelectTime() {
        if( select_time==null){
            select_time = (AppCompatSpinner) findViewById(R.id.select_time);
        }
        setTimeData();
        spinnerAdapter = new FoodTimeSpinnerAdapter(this, dataStringArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_time.setAdapter(spinnerAdapter);
        select_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                isScope=String.valueOf(i);
                footterControler.doQueryFoodInfo(String.valueOf(i), var.getUserId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView() {
        title_tv = (AppCompatTextView) findViewById(R.id.title_tv);
        title_tv.setText("美食");
        reight_tv = (AppCompatTextView) findViewById(R.id.reight_tv);
        left_btn = (AppCompatImageButton) findViewById(R.id.left_btn);
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        sliding_tabs = (TabLayout) findViewById(R.id.sliding_tabs);

        show_pp = (ImageView) findViewById(R.id.show_pp);
        shopping_num_txt = (AppCompatTextView) findViewById(R.id.shopping_num_txt);
        shopping_num = (FrameLayout) findViewById(R.id.shopping_num);
        shopping_product_price = (AppCompatTextView) findViewById(R.id.shopping_product_price);
        upload_shopping_car = (AppCompatButton) findViewById(R.id.upload_shopping_car);

    }


    /**
     * 初始化TabLayout
     */
    public void initTitleData() {
        List<String>   list_title = new ArrayList<>();
        list_title.add("早餐");
        list_title.add("午餐");
        list_title.add("晚餐");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new BreakFastActivity());
        fragments.add(new BreakFastActivity());
        fragments.add(new BreakFastActivity());
        setViewPager(list_title,fragments);
    }


    @Override
    public IControler getControler() {
        return FootterControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {

    }

    MyFoodTypeRecyleViewAdapter foodTypeAdapter = null;

    /**
     * 获取想对时间段相对餐品类型下的餐品信息
     * @param mealTime
     */
    protected void initFoodInfoListData(int postion,int mealTime) {
        curMealTime = "" + this.mealTime;
        Cursor foodInfoDatas=sortFoodCursor(curFoodType, String.valueOf(mealTime));
        PagerAdapter pa= (PagerAdapter) viewpager.getAdapter();
        BreakFastActivity foodFleatemnt= (BreakFastActivity) pa.getItem(postion);
        if(foodInfoDatas!=null){
            final FoodInfoDataDao foodInfoDataDao = (FoodInfoDataDao) footterControler.getBaseDaoOperator().get();
            foodFleatemnt.setFoodInfoCursor(foodInfoDatas,foodInfoDataDao);
        }
    }

    public Cursor sortFoodCursor(String cateName, String mealTime){
        Cursor cursorFoodInfo=footterControler.getFoodInfoCursor(mealTime,cateName,isScope);
        Log.i(TAG,"cursorFoodInfo==="+cursorFoodInfo.getCount());
        return cursorFoodInfo;
    }


    private int changeId=-1;
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        changeId=id;
        return null;
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        footCateAdapter.setDao((FootCateBeanDao) footterControler.getBaseDaoOperator().get(FootCateBean.class));
        foodTypeAdapter.swapCursor(data);
        foodTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

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

    /**
     * 设置餐品时间 今天，明天，后天
     */
    public void setTimeData() {
        dataStringArray[0] = "今日";
        dataStringArray[1] = "明日";
        dataStringArray[2] = "后日";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.llh_food_main;
    }

    @Override
    public void usable() {

    }

    public static class MyFoodTypeRecyleViewAdapter extends BaseDataBaseAdapter<MyFoodTypeRecyleViewAdapter.ViewHolder> {
        private SoftReference<  FootCateBeanDao> softReference = null;
        private LayoutInflater layoutInflater=null;
        private String TAG="MyFoodTypeRecyleViewAdapter";
        @Override
        public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
            if (softReference != null) {
                FootCateBeanDao dao = softReference.get();
                if (dao != null) {
                   FootCateBean pc = dao.readEntity(cursor, 0);
                    holder.setData(pc);
                }
            }
        }

        public MyFoodTypeRecyleViewAdapter(Context context, Cursor c) {
            super(context, c);
            layoutInflater= LayoutInflater.from(context);
        }

        @Override
        public View getItemView() {
            return layoutInflater.inflate(R.layout.food_type_list_item,null);
        }

        public void setDao( FootCateBeanDao dao) {
            softReference = new SoftReference< FootCateBeanDao>(dao);
        }

        public MyFoodTypeRecyleViewAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
            layoutInflater= LayoutInflater.from(context);
        }


        @Override
        public ViewHolder onCreatViewHolder(View view) {
            return new ViewHolder(view);
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public TextView distance;
            public TextView food_type;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.distance = (TextView) rootView.findViewById(R.id.distance);
                this.food_type = (TextView) rootView.findViewById(R.id.food_type);
            }

            public void setData(FootCateBean pc) {
                this.food_type.setText(pc.getCateName());
            }
        }
    }
}

