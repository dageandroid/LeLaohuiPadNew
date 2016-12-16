package dq.lelaohui.com.lelaohuipad.dao;

import android.database.Cursor;
import android.text.TextUtils;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.CursorQuery;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FoodInfoData;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FootCateBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FoodInfoDataDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FootCateBeanDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/11/22.
 *  保存餐品信息数据库
 */

public class ProFoodInfoDaoOperator extends BaseDaoOperator {

    private static ProFoodInfoDaoOperator ourInstance = null;

    public static ProFoodInfoDaoOperator getInstance() {
        if(ourInstance==null){
            synchronized (ProServerContentDaoOperator.class){
                ourInstance=new ProFoodInfoDaoOperator();
            }
        }
        return ourInstance;
    }
    private  ProFoodInfoDaoOperator(){

    }

    @Override
    public BaseBean queryData(BaseBean t) {
        return null;
    }

    @Override
    public List<? extends BaseBean> queryDataList(BaseBean t) {
        return queryDataList(FoodInfoData.class,t);
    }

    @Override
    public void updateData(BaseBean t) {
        update(FoodInfoData.class,t);
    }

    @Override
    public void intsert(BaseBean t) {
        insert(FoodInfoData.class,t);
    }

    @Override
    public void intsert(List<? extends BaseBean> t) {


        if(t!=null&&!t.isEmpty()){
            for(int i=0;i<t.size();i++){
                FoodInfoData data= (FoodInfoData) t.get(i);
               super. insert(FootCateBean.class, new FootCateBean(data));
            }
            super.insert(FoodInfoData.class,t);
        }


    }
    public void delete(String t) {
        super.deleteAll(FoodInfoData.class);
        super.deleteAll(FootCateBean.class);
    }
    @Override
    public void delete(BaseBean t) {
       super.deleteAll(FoodInfoData.class);
        super.deleteAll(FootCateBean.class);
    }
    public long count(){
        return 0;
    }
    @Override
    public Cursor query(BaseBean t) {
        FoodInfoData footInfo= (FoodInfoData) t;
        return query(FoodInfoData.class);
    }

    @Override
    public void updateData(List<? extends BaseBean> t) {

    }

    @Override
    public AbstractDao get() {
        return  get(FoodInfoData.class);
    }

    @Override
    public void delete(String... condition) {

    }

    private ConcurrentHashMap<String,CursorQuery> conditonQueryCache=new ConcurrentHashMap<>();
    private  CursorQuery footInfoQuery=null;
    /**
     * 查询获取相对应的餐品信息
     * @param mealTime   早中晚
     * @param isscope)   今明后
     * @return
     */
    public Cursor queryFoodInfo(String mealTime,String cateId,String isscope){
        QueryBuilder.   LOG_SQL       =       true   ;
        QueryBuilder.   LOG_VALUES       =       true   ;
        if (footInfoQuery == null) {
            WhereCondition mealTimeConin= FoodInfoDataDao.Properties.MealTime.eq(TextUtils.isEmpty(mealTime)?"0":mealTime);
            WhereCondition cateNameContin=FoodInfoDataDao.Properties.CateName.eq(TextUtils.isEmpty(cateId)?"":cateId);
            WhereCondition isscoreCondition=FoodInfoDataDao.Properties.IsScore.eq(isscope);
            footInfoQuery= getCursorQuery(FoodInfoData.class,mealTimeConin,cateNameContin,isscoreCondition);
            return footInfoQuery.query();
        }
        footInfoQuery.setParameter(0,TextUtils.isEmpty(mealTime)?"0":mealTime);
        footInfoQuery.setParameter(1,TextUtils.isEmpty(cateId)?"":cateId);
        footInfoQuery.setParameter(2, TextUtils.isEmpty(isscope)?"0":isscope);
        return footInfoQuery==null?null:footInfoQuery.query();
    }
    private CursorQuery cateCursorQuery=null;
    /**
     * 查询餐品类型
     * @param isscope  0:今日，1：明日 ，2:后天
     * @return
     */
    public Cursor queryFoodType(String isscope){
        QueryBuilder.   LOG_SQL       =       true   ;
        QueryBuilder.   LOG_VALUES       =       true   ;
        if (cateCursorQuery == null) {
            WhereCondition mealTypeContin= FootCateBeanDao.Properties.MealTime.eq(isscope);
            cateCursorQuery=getCursorQuery(FootCateBean.class,mealTypeContin);
        }else{
            cateCursorQuery.setParameter(0,isscope);
        }
        return  cateCursorQuery.query();
    }

}
