package dq.lelaohui.com.lelaohuipad.dao;
import android.database.Cursor;
import android.util.Log;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;
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

        insert(FoodInfoData.class,t);
        if(t!=null&&!t.isEmpty()){
            List<FootCateBean> footCateBeanList=null;
            for(int i=0;i<t.size();i++){
                FoodInfoData data= (FoodInfoData) t.get(i);
                footCateBeanList.add(new FootCateBean(data));
            }
            if(footCateBeanList!=null&&!footCateBeanList.isEmpty()){

                insert(FootCateBean.class, footCateBeanList);
            }
        }


    }

    @Override
    public void delete(BaseBean t) {

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
        return getDao(FoodInfoData.class);
    }

    /**
     * 查询获取相对应的餐品信息
     * @param mealTime   早中晚
     * @param cateName    菜单名
     * @param mealType   今明后
     * @return
     */
    public Cursor queryFoodInfo(String mealTime,String cateName,String mealType){
        WhereCondition mealTimeConin= FoodInfoDataDao.Properties.MealTime.eq(mealTime);
        WhereCondition cateNameContin=FoodInfoDataDao.Properties.CateName.eq(cateName);
        WhereCondition mealTypeContin= FoodInfoDataDao.Properties.MealType.eq(mealType);
        return super.query(FoodInfoData.class,mealTimeConin,cateNameContin,mealTypeContin);
    }

    /**
     * 查询餐品类型
     * @param mealTime
     * @param mealType
     * @return
     */
    public Cursor queryFoodType(String mealTime, String mealType){
        WhereCondition mealTimeConin= FootCateBeanDao.Properties.MealTime.eq(mealTime);
        WhereCondition mealTypeContin= FootCateBeanDao.Properties.MealType.eq(mealType);
        return super.query(FootCateBean.class,mealTimeConin,mealTypeContin);
    }
    /**
     * 查询餐品类型
     * @param mealType
     * @return
     */
    public Cursor queryFoodType(String mealType){
        WhereCondition mealTypeContin= FootCateBeanDao.Properties.MealType.eq(mealType);
        Log.i("ProFoodInfoDaoOperator","mealTypeContin=="+mealTypeContin.toString());
        return super.query(FootCateBean.class,mealTypeContin);
    }

}
