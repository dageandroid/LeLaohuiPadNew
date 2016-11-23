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
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FoodInfoDataDao;


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
    private boolean isScroll = true;
    private final static String TODAY_FOOD = "0";//餐品选择时间
    private final static String TOMORROW_FOOD = "1";
    private final static String POSTNATAL_FOOD = "2";
    private String isScope = TODAY_FOOD;//是否选择
    private final static String BREAK_FOOD = "1";
    private String curMealTime = BREAK_FOOD;//当前吃饭时间
    private static final String TAG = "FoodActivity";
    private String cacheKey = Protocol_KEY.CACHE_KEY.FOOT_TODAY_KEY;
    private SysVar var = null;
    private int mealTime = 1;//早中晚时间标示
    private String curFoodType;//当前食物类型
    private ArrayList<String> foodTypeList = new ArrayList<String>();//食物类型集合
    private List<FoodInfoData> curFoodList = new ArrayList<FoodInfoData>();// 当前食物列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footterControler = (FootterControler) getControler();
        var = SysVar.getInstance();
        getSupportLoaderManager().initLoader(0, null, this);
        Cursor cursor =footterControler.getFoodTypeCursor(isScope);
        Log.i(TAG,"cursor=="+cursor.getCount());
        initView();
        setTimeData();
        foodTypeAdapter=new MyFoodTypeRecyleViewAdapter(this,cursor);
        final FoodInfoDataDao dao=  (FoodInfoDataDao) footterControler.getBaseDaoOperator().get();
        foodTypeAdapter.setDao(dao);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FoodActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        food_type.setLayoutManager(linearLayoutManager);
        food_type.setAdapter(foodTypeAdapter);
        foodTypeAdapter.setmOnItemClickListener(new BaseDataBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Cursor c) {

            }
        });
        spinnerAdapter = new FoodTimeSpinnerAdapter(this, dataStringArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_time.setAdapter(spinnerAdapter);
        select_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 0:
//                        cacheKey = Protocol_KEY.CACHE_KEY.FOOT_TODAY_KEY;
////                        isScope = TODAY_FOOD;
//                        break;
//                    case 1:
//                        cacheKey = Protocol_KEY.CACHE_KEY.FOOT_TOMORROW_KEY;
////                        isScope = TOMORROW_FOOD;
//                        break;
//                    case 2:
//                        cacheKey = Protocol_KEY.CACHE_KEY.FOOT_AFTERTOMORROW_KEY;
////                        isScope = POSTNATAL_FOOD;
//                        break;
//                }
                isScope=String.valueOf(i);
                footterControler.doQueryFoodInfo(String.valueOf(i), var.getUserId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        initTitleData();
        viewpager.setAdapter(new PagerAdapter(getSupportFragmentManager(), list_title, fragments));
        sliding_tabs.setupWithViewPager(viewpager);
        sliding_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int postion=tab.getPosition();
                viewpager.setCurrentItem(postion);
//                if (tab.getPosition() == 0) {
//                    mealTime = 1;
//                } else if (tab.getPosition() == 1) {
//                    mealTime = 2;
//                } else {
//                    mealTime = 3;
//                }
                mealTime=postion+1;
                initFoodInfoListData(postion,"" + mealTime);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
        select_time = (AppCompatSpinner) findViewById(R.id.select_time);
        food_type = (RecyclerView) findViewById(R.id.food_type);
        sliding_tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        show_pp = (ImageView) findViewById(R.id.show_pp);
        shopping_num_txt = (AppCompatTextView) findViewById(R.id.shopping_num_txt);
        shopping_num = (FrameLayout) findViewById(R.id.shopping_num);
        shopping_product_price = (AppCompatTextView) findViewById(R.id.shopping_product_price);
        upload_shopping_car = (AppCompatButton) findViewById(R.id.upload_shopping_car);

    }

    private List<Fragment> fragments = new ArrayList<>();

    /**
     * 初始化TabLayout
     */
    public void initTitleData() {
        list_title = new ArrayList<>();
        list_title.add("早餐");
        list_title.add("午餐");
        list_title.add("晚餐");
        fragments.add(new BreakFastActivity());
        fragments.add(new BreakFastActivity());
        fragments.add(new BreakFastActivity());
    }

    private List<String> list_title = new ArrayList<>();//早餐午餐晚餐

    @Override
    public IControler getControler() {
        return FootterControler.getControler();
    }

    private HashMap<String,List<FoodInfoData>> leftMenum=new HashMap<>();
    @Override
    public void result(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.QUERY_FOOD_INFO_RESPONSE)) {
                ArrayList<FoodInfoData> foodInfoDataList = bundle.getParcelableArrayList("foodInfo");
                if (foodInfoDataList != null && foodInfoDataList.size() > 0) {
                    for(FoodInfoData data:foodInfoDataList){
//                        if(leftMenum.get(data.getMealTime()))
//                        leftMenum.put()
                    }
                    PagerAdapter pa= (PagerAdapter) viewpager.getAdapter();
                    BreakFastActivity foodFleatemnt= (BreakFastActivity) pa.getItem(viewpager.getCurrentItem());
//                    foodFleatemnt.setFoodInfoDataList(foodInfoDataList);
                }
            }
        }
    }

    MyFoodTypeRecyleViewAdapter foodTypeAdapter = null;


    /**
     * 设置餐品类型
     * @param foodType
     */
    public void setLeftSeletFoodType(String foodType) {
        if (foodTypeList.size() == 0) {
            Toast.makeText(this, "食物类型为空！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            curFoodType = foodType;
        }
//        initFoodInfoListData(curMealTime);
    }

//    /**
//     * 获取想对时间段相对餐品类型下的餐品信息
//     * @param mealTime
//     */
//    protected void initFoodInfoListData(String mealTime) {
//        curMealTime = "" + this.mealTime;
//        foodInfoDatas = sortFood(curFoodType, mealTime);
////        for (int i = 0; i < foodInfoDatas.size(); i++) {
////            Log.i(TAG, "foodInfoDatas.get(i).getProName()===" + foodInfoDatas.get(i).getProName());
////        }
//    }
    /**
     * 获取想对时间段相对餐品类型下的餐品信息
     * @param mealTime
     */
    protected void initFoodInfoListData(int postion,String mealTime) {
        curMealTime = "" + this.mealTime;
        List<FoodInfoData> foodInfoDatas = sortFood(curFoodType, mealTime);
        PagerAdapter pa= (PagerAdapter) viewpager.getAdapter();
        BreakFastActivity foodFleatemnt= (BreakFastActivity) pa.getItem(postion);
        foodFleatemnt.setFoodInfoDataList(foodInfoDatas);
//        for (int i = 0; i < foodInfoDatas.size(); i++) {
//            Log.i(TAG, "foodInfoDatas.get(i).getProName()===" + foodInfoDatas.get(i).getProName());
//        }
    }

    public Cursor sortFoodCursor(String cateName, String mealTime){
        Cursor cursorFoodInfo=footterControler.getFoodInfoCursor(mealTime,cateName,isScope);
        return cursorFoodInfo;
    }

    /**
     * 通过食物的类别和用餐时间进行获取食物信息
     *
     * @param cateName
     * @param mealTime
     * @return
     */
    public List<FoodInfoData> sortFood(String cateName, String mealTime) {
        curFoodList.clear();
        Cursor queryFoodInfo=footterControler.getFoodInfoCursor(mealTime,cateName,isScope);


//        for (int i = 0; i < foodInfoDataList.size(); i++) {
//            FoodInfoData foodInfoData = foodInfoDataList.get(i);
//            if (foodInfoData.getCateName().equals(cateName) && foodInfoData.getMealTime().equals(mealTime)) {
//                curFoodList.add(foodInfoData);
//            }
//        }
        return curFoodList;
    }

    /**
     * 获取食物类型list
     *
     * @return
     */
    public ArrayList<String> getFoodType() {
        foodTypeList.clear();
//        if (foodInfoDataList != null && foodInfoDataList.size() > 0) {
//            for (int i = 0; i < foodInfoDataList.size(); i++) {
//                FoodInfoData foodInfoData = foodInfoDataList.get(i);
//                if (!foodTypeList.contains(foodInfoData.getCateName())) {
//                    foodTypeList.add(foodInfoData.getCateName());
//                }
//            }
//        }
//        if (foodTypeList.size() > 0) {
//            curFoodType = foodTypeList.get(0);
//        }
        return foodTypeList;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

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
//        Date date = new Date();// 取时间
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(date);
//        calendar.add(calendar.DATE, 0);//
//        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = formatter.format(date);
        dataStringArray[0] = "今日";

//        date = new Date();// 取时间
//        calendar = new GregorianCalendar();
//        calendar.setTime(date);
//        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
//        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//        formatter = new SimpleDateFormat("yyyy-MM-dd");
//        dateString = formatter.format(date);
        dataStringArray[1] = "明日";

//        date = new Date();// 取时间
//        calendar = new GregorianCalendar();
//        calendar.setTime(date);
//        calendar.add(calendar.DATE, 2);// 把日期往后增加2天.整数往后推,负数往前移动
//        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//        formatter = new SimpleDateFormat("yyyy-MM-dd");
//        dateString = formatter.format(date);
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
        private SoftReference<FoodInfoDataDao> softReference = null;
        private LayoutInflater layoutInflater=null;
        private String TAG="MyFoodTypeRecyleViewAdapter";
        @Override
        public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
            if (softReference != null) {
                FoodInfoDataDao dao = softReference.get();
                if (dao != null) {
                    FoodInfoData pc = dao.readEntity(cursor, 0);
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

        public void setDao(FoodInfoDataDao dao) {
            softReference = new SoftReference<FoodInfoDataDao>(dao);
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

            public void setData(FoodInfoData pc) {
                this.food_type.setText(pc.getCateName());
            }
        }
    }
}

