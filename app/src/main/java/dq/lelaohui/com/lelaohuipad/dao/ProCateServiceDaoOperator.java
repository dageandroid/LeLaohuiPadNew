package dq.lelaohui.com.lelaohuipad.dao;

import android.database.Cursor;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateServiceDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ThinkPad on 2016/10/23.
 */
public class ProCateServiceDaoOperator extends  BaseDaoOperator{
    private static ProCateServiceDaoOperator ourInstance = null;

    public static ProCateServiceDaoOperator getInstance() {
        if(ourInstance==null){
            synchronized (ProCateServiceDaoOperator.class){
                ourInstance=new ProCateServiceDaoOperator();
            }
        }
        return ourInstance;
    }

    private ProCateServiceDaoOperator() {

     }
    public long count(){
        return 0;
    }

    @Override
    public BaseBean queryData(BaseBean t) {
        return null;
    }

    @Override
    public List<?extends  BaseBean> queryDataList(BaseBean t) {
        return queryDataList(ProCateService.class,t);
    }

    @Override
    public void updateData(BaseBean t) {

    }



    @Override
    public void intsert(BaseBean t) {
        insert(ProCateService.class,t);
    }

    @Override
    public void intsert(List<? extends BaseBean> t) {
        insert(ProCateService.class,t);
    }


    @Override
    public void delete(BaseBean t) {

    }

    @Override
    public Cursor query(BaseBean t)
    {
        return query(ProCateService.class,t);
    }

    @Override
    public void updateData(List<? extends BaseBean> t) {

    }

    @Override
    public AbstractDao get() {
        return getDao(ProCateService.class);
    }
}
