package dq.lelaohui.com.lelaohuipad.dao;

import android.database.Cursor;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateMenuService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateMenuServiceDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/10/27.
 */

public class ProMenumServiceDaoOperator extends BaseDaoOperator {
    private static ProMenumServiceDaoOperator ourInstance = null;

    public static ProMenumServiceDaoOperator getInstance() {
        if(ourInstance==null){
            synchronized (ProMenumServiceDaoOperator.class){
                ourInstance=new ProMenumServiceDaoOperator();
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
        return queryDataList(ProCateMenuService.class,t);
    }

    @Override
    public void updateData(BaseBean t) {
    update(ProCateMenuService.class,t);
    }

    @Override
    public void intsert(BaseBean t) {
            insert(ProCateMenuService.class,t);
    }

    @Override
    public void intsert(List<? extends BaseBean> t) {
        insert(ProCateMenuService.class,t);
    }
    @Override
    public void delete(BaseBean t) {

    }

    /**查询所有
     * @param t
     * @return
     */
    @Override
    public Cursor query(BaseBean t){
        ProCateMenuService proCateService= (ProCateMenuService) t;
        return query(ProCateMenuService.class);
    }


    @Override
    public void updateData(List<? extends BaseBean> t) {

    }

    public AbstractDao get() {
        return getDao(ProCateMenuService.class);
    }

    /**查询二级数据库
     * @param orgId
     * @param orgTypeId
     * @param cateId
     * @return
     */
    public Cursor queryTwo(long orgId ,int orgTypeId,long cateId ){
        WhereCondition condition= ProCateMenuServiceDao.Properties.OrgId.eq(orgId);
        WhereCondition orgTypeIdContin=ProCateMenuServiceDao.Properties.OrgTypeId.eq(orgTypeId);
        WhereCondition cateidContin= ProCateMenuServiceDao.Properties.ParentId.eq(cateId);
        return super.query(ProCateMenuService.class,condition,orgTypeIdContin,cateidContin);
    }

    /**查询二级数据库
     * @param cateId
     * @return
     */
    public Cursor queryTwo(long cateId ){

        WhereCondition cateidContin=ProCateMenuServiceDao.Properties.ParentId.eq(cateId);
        return super.query(ProCateMenuService.class,cateidContin);
    }
    /**查询二级数据库
     * @param orgId
     * @param orgTypeId
     * @return
     */
    public Cursor queryDataTwo(long orgId ,int orgTypeId){
        WhereCondition condition= ProCateMenuServiceDao.Properties.OrgId.eq(orgId);
        WhereCondition orgTypeIdContin=ProCateMenuServiceDao.Properties.OrgTypeId.eq(orgTypeId);
        return super.query(ProCateMenuService.class,condition,orgTypeIdContin);
    }

    /**查询二级数据库
     * @return
     */
    public Cursor queryDataTwo( ){
        return super.query(ProCateMenuService.class);
    }
}
