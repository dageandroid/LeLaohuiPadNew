package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.FoodTimeSpinnerAdapter;
import dq.lelaohui.com.lelaohuipad.adapter.FoodTypeAdapter;
import dq.lelaohui.com.lelaohuipad.adapter.FoodTypesAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.FoodInfoData;
import dq.lelaohui.com.lelaohuipad.controler.FootterControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/21.
 */

public class FoodInfosActivity extends LeLaoHuiBaseActivity implements View.OnClickListener {
    public AppCompatTextView title_tv;
    public AppCompatImageButton left_btn;
    public AppCompatTextView reight_tv;
    public Spinner spnner;
    public Button break_btn;
    public Button lunch_btn;
    public Button dinner_btn;
    public LinearLayout food_top;
    public View view_line;
    public ListView left_listview;
    public TextView rightlist_tips;
    public ListView right_listview;
    public ImageView show_pp;
    public AppCompatTextView shopping_num_txt;
    public FrameLayout shopping_num;
    public AppCompatTextView shopping_product_price;
    public AppCompatButton upload_shopping_car;
    public RelativeLayout main_layout;
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

    private String cacheKey = Protocol_KEY.CACHE_KEY.FOOT_TODAY_KEY;
    private SysVar var = null;
    private int mealTime = 1;//早中晚时间标示
    private String curFoodType;//当前食物类型
    private ArrayList<String> foodTypeList = new ArrayList<String>();//食物类型集合
    private List<FoodInfoData> curFoodList=new ArrayList<>();//当前食物列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footterControler = (FootterControler) getControler();
        var = SysVar.getInstance();
        initView();
        setTimeData();
        spinnerAdapter = new FoodTimeSpinnerAdapter(this, dataStringArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnner.setAdapter(spinnerAdapter);
        spnner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        cacheKey = Protocol_KEY.CACHE_KEY.FOOT_TODAY_KEY;
                        isScope = TODAY_FOOD;
                        footterControler.doQueryFoodInfo(isScope, var.getUserId());
                        break;
                    case 1:
                        cacheKey = Protocol_KEY.CACHE_KEY.FOOT_TOMORROW_KEY;
                        isScope = TOMORROW_FOOD;
                        footterControler.doQueryFoodInfo(isScope, var.getUserId());
                        break;
                    case 2:
                        cacheKey = Protocol_KEY.CACHE_KEY.FOOT_AFTERTOMORROW_KEY;
                        isScope = POSTNATAL_FOOD;
                        footterControler.doQueryFoodInfo(isScope, var.getUserId());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public IControler getControler() {
        return FootterControler.getControler();
    }


    private FoodTypesAdapter foodTypeAdapter = null;
    private   FoodInfosAdapter foodInfosAdapter=null;
    /**
     * 初始化食物数据
     *
     * @param mealTime
     */
    private  List<FoodInfoData> listData=new ArrayList<>();
    protected void initFoodTypeListData(String mealTime) {
//        getFoodType();
//        foodTypeAdapter = new FoodTypesAdapter(FoodInfosActivity.this, getFoodType());
//        left_listview.setAdapter(foodTypeAdapter);
        foodTypeAdapter.setData(getFoodType());
        foodTypeAdapter.notifyDataSetChanged();
        listData=new ArrayList<>();
        if (listData==null&&listData.size()==0){
            return;
        }
        listData=sortFood(foodTypeList.get(0),mealTime);
        foodInfosAdapter.setData(listData,isScope);
        foodInfosAdapter.notifyDataSetChanged();
    }

    /**
     * 根据食物类型和吃饭时间划分
     * @param cateName
     * @param mealTime
     * @return
     */
     public List<FoodInfoData> sortFood(String cateName,String mealTime){
         curFoodList.clear();
         for (int i=0;i<foodInfoDataList.size();i++){
             FoodInfoData foodInfoData=foodInfoDataList.get(i);
             if (foodInfoData.getCateName().equals(mealTime)){
                 curFoodList.add(foodInfoData);
             }
         }
         return curFoodList;
     }
    private List<FoodInfoData> foodInfoDataList = new ArrayList<FoodInfoData>();

    /**
     * 获取食物类型list
     *
     * @return
     */
    public ArrayList<String> getFoodType() {
        foodTypeList.clear();
        if (foodInfoDataList != null && foodInfoDataList.size() > 0) {
            for (int i = 0; i < foodInfoDataList.size(); i++) {
                FoodInfoData foodInfoData = foodInfoDataList.get(i);
                if (!foodTypeList.contains(foodInfoData.getCateName())) {
                    foodTypeList.add(foodInfoData.getCateName());
                }
            }
        }
        if (foodTypeList.size() > 0) {
            curFoodType = foodTypeList.get(0);
        }
        return foodTypeList;
    }

    @Override
    public void result(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.QUERY_FOOD_INFO_RESPONSE)) {
                foodInfoDataList = bundle.getParcelableArrayList("foodInfo");
                if (foodInfoDataList != null && foodInfoDataList.size() > 0) {
                    int index = Integer.parseInt(isScope);
                    switch (this.mealTime) {
                        case 1:
                            rightlist_tips.setText(dataStringArray[index] + "早餐");
                            break;
                        case 2:
                            rightlist_tips.setText(dataStringArray[index] + "午餐");
                            break;
                        case 3:
                            rightlist_tips.setText(dataStringArray[index] + "晚餐");
                            break;
                    }
                    initFoodTypeListData(curFoodType);
                }
            }
        }
    }

    public void initView() {
        title_tv = (AppCompatTextView) findViewById(R.id.title_tv);
        title_tv.setText("美食");
        left_btn = (AppCompatImageButton) findViewById(R.id.left_btn);
        left_btn.setOnClickListener(this);
        reight_tv = (AppCompatTextView) findViewById(R.id.reight_tv);
        spnner = (Spinner) findViewById(R.id.spnner);
        break_btn = (Button) findViewById(R.id.break_btn);
        break_btn.setOnClickListener(this);
        lunch_btn = (Button) findViewById(R.id.lunch_btn);
        lunch_btn.setOnClickListener(this);
        dinner_btn = (Button) findViewById(R.id.dinner_btn);
        dinner_btn.setOnClickListener(this);
        food_top = (LinearLayout) findViewById(R.id.food_top);
        view_line = (View) findViewById(R.id.view_line);
        left_listview = (ListView) findViewById(R.id.left_listview);
        rightlist_tips = (TextView) findViewById(R.id.rightlist_tips);
        right_listview = (ListView) findViewById(R.id.right_listview);
        show_pp = (ImageView) findViewById(R.id.show_pp);
        shopping_num_txt = (AppCompatTextView) findViewById(R.id.shopping_num_txt);
        shopping_num = (FrameLayout) findViewById(R.id.shopping_num);
        shopping_product_price = (AppCompatTextView) findViewById(R.id.shopping_product_price);
        upload_shopping_car = (AppCompatButton) findViewById(R.id.upload_shopping_car);
        main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        foodTypeAdapter=new FoodTypesAdapter(FoodInfosActivity.this);
        left_listview.setAdapter(foodTypeAdapter);
        left_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                isScroll=false;
                setSelectFoodType(i);
            }
        });
        foodInfosAdapter =new FoodInfosAdapter(FoodInfosActivity.this);
        right_listview.setAdapter(foodInfosAdapter);
    }

    /**
     * 菜品类型选择
     * @param position
     */
    public void setSelectFoodType(int position){
    if(foodTypeList.size()==0){
       getFoodType();
        return;
        }else {
        curFoodType = foodTypeList.get(position);
    }
        initFoodInfoListData(curMealTime);
    }
    public void initFoodInfoListData(String  curMealTime){
        curMealTime=""+this.mealTime;
        List<FoodInfoData> data=sortFood(curFoodType,curMealTime);
        foodInfosAdapter.setData(data,isScope);
        foodInfosAdapter.notifyDataSetChanged();
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activity_food_info;
    }

    @Override
    public void usable() {

    }

    /**
     * 设置餐品时间 今天，明天，后天
     */
    public void setTimeData() {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 0);//
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        dataStringArray[0] = "今日";

        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        dateString = formatter.format(date);
        dataStringArray[1] = "明日";

        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 2);// 把日期往后增加2天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        dateString = formatter.format(date);
        dataStringArray[2] = "后日";
    }
int index;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.break_btn:
                if (foodInfoDataList.size() == 0) {
                    break_btn.setTextColor(getResources().getColor(
                            R.color.color_white));
                    lunch_btn.setTextColor(getResources().getColor(
                            R.color.color_black));
                    dinner_btn.setTextColor(getResources().getColor(
                            R.color.color_black));
                    break_btn.setSelected(true);
                    break_btn.setClickable(false);
                    lunch_btn.setSelected(false);
                    lunch_btn.setClickable(true);
                    dinner_btn.setSelected(false);
                    dinner_btn.setClickable(true);
                    this.mealTime = 1;
                    initFoodInfoListData("" + mealTime);
                    index = Integer.parseInt(isScope);
                    rightlist_tips.setText(dataStringArray[index] + " 早餐");
                } else {
//                    clearDayFood();
                    Toast.makeText(this, "是否要取消您的选择？", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lunch_btn:
                if (foodInfoDataList.size() == 0) {
                    lunch_btn.setTextColor(getResources().getColor(
                            R.color.color_white));
                    break_btn.setTextColor(getResources().getColor(
                            R.color.color_black));
                    dinner_btn.setTextColor(getResources().getColor(
                            R.color.color_black));
                    break_btn.setSelected(false);
                    break_btn.setClickable(true);
                    lunch_btn.setSelected(true);
                    lunch_btn.setClickable(false);
                    dinner_btn.setSelected(false);
                    dinner_btn.setClickable(true);
                    this.mealTime = 2;
                    initFoodInfoListData("" + mealTime);
                    index = Integer.parseInt(isScope);
                    rightlist_tips.setText(dataStringArray[index] + " 午餐");
                } else {
//                    clearDayFood();
                    Toast.makeText(this, "是否要取消您的选择？", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.dinner_btn:
                if (foodInfoDataList.size() == 0) {

                dinner_btn.setTextColor(getResources().getColor(
                        R.color.color_white));
                lunch_btn.setTextColor(getResources().getColor(
                        R.color.color_black));
                break_btn.setTextColor(getResources().getColor(
                        R.color.color_black));
                break_btn.setSelected(false);
                break_btn.setClickable(true);
                lunch_btn.setSelected(false);
                lunch_btn.setClickable(true);
                dinner_btn.setSelected(true);
                dinner_btn.setClickable(false);
                this.mealTime = 3;
                    initFoodInfoListData("" + mealTime);
                index = Integer.parseInt(isScope);
                rightlist_tips.setText(dataStringArray[index] + " 晚餐");
            } else {
//                clearDayFood();
                    Toast.makeText(this, "是否要取消您的选择？", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public class FoodInfosAdapter extends BaseAdapter {
        private Context context;
        private List<FoodInfoData> foodInfoDataList=new ArrayList<>();
        private LayoutInflater inflater;
        private final static String TODAY_FOOD = "0";
        private String isScope = TODAY_FOOD;

        public FoodInfosAdapter(Context context, List<FoodInfoData> foodInfoDataList) {
            this.foodInfoDataList = foodInfoDataList;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }
        public FoodInfosAdapter(Context context){
            this.context=context;
        }
        public void setData(List<FoodInfoData> foodInfoData,String  isScope){
            this.isScope=isScope;
            foodInfoDataList.clear();
            foodInfoDataList.addAll(foodInfoData);
            this.notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            if (foodInfoDataList == null && foodInfoDataList.size() == 0) {
                return 0;
            }
            return foodInfoDataList.size();
        }

        @Override
        public Object getItem(int i) {
            return foodInfoDataList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder=null;
                view = inflater.inflate(R.layout.llh_food_cv_item, null);
                viewHolder=new ViewHolder(view);
            return view;
        }

        public  class ViewHolder {
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
          public void  setData(FoodInfoData foodInfoData){
              this.food_name.setText(foodInfoData.getProName());
              this.food_price.setText("￥"+foodInfoData.getProPrice()+"元");
            }

        }
    }


}
