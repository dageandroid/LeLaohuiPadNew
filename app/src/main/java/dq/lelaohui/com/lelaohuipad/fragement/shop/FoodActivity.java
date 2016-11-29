package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.FoodTimeSpinnerAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.ShoppingCarListBean;
import dq.lelaohui.com.lelaohuipad.controler.FootterControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.adapter.MyFoodTypeRecyleViewAdapter;
import dq.lelaohui.com.lelaohuipad.fragement.shop.adapter.PagerAdapter;
import dq.lelaohui.com.lelaohuipad.fragement.shop.car.BaseShopCart;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FootCateBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FootCateBeanDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;


/**
 * Created by ZTF on 2016/11/18.
 */

public class FoodActivity extends LeLaoHuiBaseActivity implements   BaseShopCart.UiOperator, LoaderManager.LoaderCallbacks<Cursor>,BreakFastActivity.FootInfoCursor {
    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private AppCompatSpinner select_time;
    private ListView food_type;
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
    private View llh_shopping_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footterControler = (FootterControler) getControler();
        footterControler.setContext(this);
        var = SysVar.getInstance();
        initView();
        setSelectTime();
        initFootType();
        initInfoDetailView();


    }

    MyFoodTypeRecyleViewAdapter footCateAdapter = null;
    private int cateSelectId=-1;
    /**
     * 初始化左侧菜单
     */
    private void initFootType() {
        if (food_type == null) {
            food_type = (ListView) findViewById(R.id.food_type);
        }

        final Cursor cursor = footterControler.getFoodTypeCursor(""+select_time.getSelectedItemPosition());
        footCateAdapter = new MyFoodTypeRecyleViewAdapter(this, cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        Log.i(TAG, "initFootType: "+cursor.getCount());
        if (cursor.getCount() == 0) {
            footterControler.doQueryFoodInfo(String.valueOf(0), var.getUserId());
        }else{
            initPageData();
        }
        footCateAdapter.setDao((FootCateBeanDao) footterControler.getBaseDaoOperator().get(FootCateBean.class));
        food_type.setAdapter(footCateAdapter);
        food_type.getSelectedItemId();
        getSupportLoaderManager().initLoader(0, null, this);
        food_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cateSelectId=(int)l;
                if(viewpager==null){
                    return ;
                }
                initPageItem(viewpager.getCurrentItem());
            }
        });

    }
    private String getMealTime(){
        if( sliding_tabs==null){
            return "1";
        }
        return ""+(  select_time.getSelectedItemPosition()+1);

    }
    private String getMealType(){
        if(select_time==null){
            return "0";
        }
        return  ""+(viewpager.getCurrentItem());
    }
    private void initPageData() {
        if( food_type!=null){
            food_type.postDelayed(new Runnable() {
                @Override
                public void run() {
                    food_type.performItemClick(food_type.getChildAt(0),0,1);
                }
            },500);
        }
        if( sliding_tabs!=null){
            int [] dy=new int[2];
            sliding_tabs.getChildAt(0).getLocationOnScreen(dy);
            sliding_tabs.getChildAt(0).postDelayed(new Runnable() {
                @Override
                public void run() {
                    sliding_tabs.getChildAt(0).performClick();
                }
            },500);
        }
    }


    /**
     * 设置食品详细页面
     */
    private void initInfoDetailView() {
        if (viewpager == null) {
            viewpager = (ViewPager) findViewById(R.id.viewpager);
            initTitleData();
        }
        sliding_tabs.setupWithViewPager(viewpager,true);
        sliding_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.i(TAG, "TAB  POSITION==" + position);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int id = getSelectCateId();
                initPageItem(position);
                Log.i(TAG, "onPageSelected: ");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int getSelectCateId() {
        return  cateSelectId;
    }

    private void initPageItem(int position) {
        PagerAdapter pagerAdapter = (PagerAdapter) viewpager.getAdapter();
        if (pagerAdapter != null) {
            BreakFastActivity fragment = (BreakFastActivity) pagerAdapter.getItem(position);
            fragment.notifyDataChanger();
        }
    }

    private void setViewPager(List<String> list_title, List<Fragment> fragments) {
        viewpager.setAdapter(new PagerAdapter(getSupportFragmentManager(), list_title, fragments));
    }

    /**
     * 设置日期选择spinner控件
     */
    private void setSelectTime() {
        if (select_time == null) {
            select_time = (AppCompatSpinner) findViewById(R.id.select_time);
        }
        setTimeData();
        spinnerAdapter = new FoodTimeSpinnerAdapter(this, dataStringArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_time.setAdapter(spinnerAdapter);
        select_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
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
        llh_shopping_bottom = findViewById(R.id.llh_shopping_bottom);
    }


    /**
     * 初始化TabLayout
     */
    public void initTitleData() {
        List<String> list_title = new ArrayList<>();
        list_title.add("早餐");
        list_title.add("午餐");
        list_title.add("晚餐");
        List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<list_title.size();i++){
            BreakFastActivity ba=new BreakFastActivity();
            ba.setFootInfoCursor(this);
            fragments.add(ba);
        }
        setViewPager(list_title, fragments);
    }


    @Override
    public Cursor getCuror() {
        String mealTime=getMealTime();
        int selectCateId=getSelectCateId();
        String mealType=getMealType();
        Log.i(TAG, "getCuror: mealTime="+mealTime+",selectCateId="+selectCateId+",mealType="+mealType);
        Cursor cur=getFootControler().getFoodInfoCursor(mealTime,selectCateId,mealType);
        return cur;
    }

    @Override
    public BaseDaoOperator getDao() {
        return getControler().getBaseDaoOperator();
    }

    @Override
    public FootterControler getFootControler() {
        return (FootterControler)getControler();
    }

    @Override
    public BaseShopCart getShopCart() {
        BaseShopCart shopCartBase = new BaseShopCart(this);
        shopCartBase.setUiOperator(this);
//        shopCartBase.init();
        return shopCartBase;
    }

    @Override
    public IControler getControler() {
        FootterControler.getControler().setContext(this);
        return FootterControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        String action = bundle.getString("action");
        if (ServiceNetContant.ServiceResponseAction.QUERY_FOOD_INFO_RESPONSE.equals(action)) {
            footCateAdapter.swapCursor(footterControler.getFoodTypeCursor(""+select_time.getSelectedItemPosition()));
            initPageData();
        }
    }



    private int changeId = -1;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        changeId = id;
        FootLoader cursor = null;
        cursor = new FootLoader(this, new FootLoader.CursorCallBack() {
            @Override
            public Cursor getFoodTypeCursor() {
                return footterControler.getFoodTypeCursor(""+select_time.getSelectedItemPosition());
            }
        });

        return cursor;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        footCateAdapter.swapCursor(footterControler.getFoodTypeCursor(""+select_time.getSelectedItemPosition()));
   }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        footCateAdapter.swapCursor(null);
    }

    @Override
    public void setPromot(String promot) {
        shopping_product_price.setText("总价：" + promot);
    }

    @Override
    public View getParnetView() {
        return llh_shopping_bottom;
    }

    @Override
    public View getCardView() {
        return show_pp;
    }

    @Override
    public View getOrderView() {
        return upload_shopping_car;
    }

    @Override
    public View getPormotView() {
        return shopping_num_txt;
    }

    @Override
    public RequestParam getOrderParam(Vector<ShoppingCarListBean> data) {
        return null;
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

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

