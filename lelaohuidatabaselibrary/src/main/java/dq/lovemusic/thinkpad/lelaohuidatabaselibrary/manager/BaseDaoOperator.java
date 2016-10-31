package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.query.CursorQuery;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.DaoMaster;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.DaoSession;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.DBOperatorImp;


/**
 * Created by ThinkPad on 2016/10/23.
 */

public abstract class BaseDaoOperator implements DBOperatorImp {
    private String Tag=getClass().getSimpleName();
    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext;
    private AbstractDaoSession getWritableDao() {
        if(mContext==null){
            throw new RuntimeException( getClass().getSimpleName()+" is error mContext is null");
        }

        DaoMaster daoMaster = getDaoMaster(DBManager.getInstance(mContext).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }
    protected void insert(Class<? extends Object> entityClass,Object value){
       try{
           DaoSession daoSession = (DaoSession) getWritableDao();
           AbstractDao dao=  daoSession.getDao(entityClass);
           if(value instanceof ArrayList){
              ArrayList<?>  obj= (ArrayList<?>) value;
               obj.toArray();
               Log.i(Tag, "insert: "+obj.toString());
               dao.insertInTx(obj.toArray());
           }else{
               dao.insert(value);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
    protected void update(Class<? extends Object> entityClass,Object value){
        DaoSession daoSession = (DaoSession) getWritableDao();
        AbstractDao dao=  daoSession.getDao(entityClass);
        dao.update(value);
    }
    protected Cursor query(Class<? extends Object> entityClass,WhereCondition condition,WhereCondition ...conditions){
        DaoSession daoSession = (DaoSession) getReadDao();
        AbstractDao dao=  daoSession.getDao(entityClass);

        CursorQuery cursorQuery= dao.queryBuilder().where(condition,conditions).distinct().buildCursor();
        Cursor cursor=cursorQuery.query();
        Log.i(Tag, "query: "+cursor.getCount());
        return cursor;
    }


    /**条件查询得到对应集合
     * @param entityClass
     * @param condition
     * @param conditions
     * @return
     */
    protected  List<?extends BaseBean> queryDataList(Class<? extends Object> entityClass,WhereCondition condition,WhereCondition ...conditions){
        DaoSession daoSession = (DaoSession) getReadDao();
        AbstractDao dao=  daoSession.getDao(entityClass);
        return dao.queryBuilder().where(condition,conditions).distinct().build().list();
    }
    protected  List<?extends BaseBean> queryDataList(Class<? extends Object> entityClass,BaseBean baseBean){
           DaoSession daoSession = (DaoSession) getReadDao();
        AbstractDao dao=  daoSession.getDao(entityClass);
        return dao.queryBuilder().build().list();
    }
    private AbstractDaoSession getReadDao() {
        if(mContext==null){
            throw new RuntimeException( getClass().getSimpleName()+" is error mContext is null");
        }

        DaoMaster daoMaster = getDaoMaster(DBManager.getInstance(mContext).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();

        return daoSession;
    }
    protected AbstractDao getDao(Class<? extends Object> entityClass){
        AbstractDaoSession daoSession=getReadDao();
        if(daoSession!=null){
            return daoSession.getDao(entityClass);
        }
        return null;
    }

    private DaoMaster getDaoMaster(SQLiteDatabase database){
        if(database==null){
            return null;
        }
        return new DaoMaster(database);
    }
    public static class DBManager {
        private final static String dbName = "llelaohui_pad_db.db";
        private static DBManager mInstance;
        private DaoMaster.DevOpenHelper openHelper;
        private Context context;

        private DBManager(Context context) {
            this.context = context;
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }

        /**
         * 获取单例引用
         *
         * @param context
         * @return
         */
        public static DBManager getInstance(Context context) {
            if (mInstance == null) {
                synchronized (DBManager.class) {
                    if (mInstance == null) {
                        mInstance = new DBManager(context);
                    }
                }
            }
            return mInstance;
        }
        /**
         * 获取可读数据库
         */
        private SQLiteDatabase getReadableDatabase() {
            if (openHelper == null) {
                openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
            }
            SQLiteDatabase db = openHelper.getReadableDatabase();
            return db;
        }
        /**
         * 获取可写数据库
         */
        private SQLiteDatabase getWritableDatabase() {
            if (openHelper == null) {
                openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
            }
            SQLiteDatabase db = openHelper.getWritableDatabase();
            return db;
        }
    }
}
