package dq.lelaohui.com.lelaohuipad.dao;

import android.database.Cursor;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.SerInitProPackDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/11/2.
 */

public class ProServerContentDaoOperator  extends BaseDaoOperator {

    private static ProServerContentDaoOperator ourInstance = null;

    public static ProServerContentDaoOperator getInstance() {
        if(ourInstance==null){
            synchronized (ProServerContentDaoOperator.class){
                ourInstance=new ProServerContentDaoOperator();
            }
        }
        return ourInstance;
    }

    @Override
    public BaseBean queryData(BaseBean t) {
        return null;
    }

    @Override
    public List<? extends BaseBean> queryDataList(BaseBean t) {
        return queryDataList(SerInitProPack.class,t);

    }

    @Override
    public void updateData(BaseBean t) {
        update(SerInitProPack.class,t);
    }

    @Override
    public void intsert(BaseBean t) {
        insert(SerInitProPack.class,t);
    }

    @Override
    public void intsert(List<? extends BaseBean> t) {
        insert(SerInitProPack.class,t);
    }

    @Override
    public void delete(BaseBean t) {
            deleteAll(SerInitProPack.class);
    }

    @Override
    public Cursor query(BaseBean t) {
        return null;
    }

    @Override
    public void updateData(List<? extends BaseBean> t) {

    }

    @Override
    public AbstractDao get() {
        return getDao(SerInitProPack.class);
    }
    /**查询二级分类下的服务项
     * @param orgId
     * @param orgTypeId
     * @return
     */
    public Cursor querySerInitProData(long orgId ,int orgTypeId,int cateId ){
        WhereCondition condition= SerInitProPackDao.Properties.OrgId.eq(orgId);
        WhereCondition orgTypeIdContin= SerInitProPackDao.Properties.OrgTypeId.eq(orgTypeId);
        WhereCondition cateidContin= SerInitProPackDao.Properties.ServiceCateId.eq(cateId);
        return super.query(SerInitProPack.class,condition,orgTypeIdContin,cateidContin);
    }
    /**
     * 查询所有
     * @return
     */
    public Cursor querySerInitProData( ){
        return super.query(SerInitProPack.class);
    }

}
