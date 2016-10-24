package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager;

import org.greenrobot.greendao.AbstractDaoSession;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.DBOperatorImp;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.DaoImp;

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

    @Override
    public Object queryData(Object t) {
        return null;
    }

    @Override
    public List<Object> queryDataList(Object t) {
        return null;
    }

    @Override
    public void updateData(Object t) {

    }



    @Override
    public void intsert(Object t) {
        insert(ProCateService.class,t);
    }


    @Override
    public void delete(Object t) {

    }
}
