package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDaoSession;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.DaoMaster;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.DaoSession;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.DBOperatorImp;

/**
 * Created by ThinkPad on 2016/10/23.
 */

public abstract class BaseDaoOperator implements DBOperatorImp {
    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext;
    @Override
    public AbstractDaoSession getWritableDao() {
        return null;
    }

    @Override
    public AbstractDaoSession getReadDao() {
        if(mContext==null){
            throw new RuntimeException( getClass().getSimpleName()+" is error mContext is null");
        }

        DaoMaster daoMaster = getDaoMaster(DBManager.getInstance(mContext).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();

        return daoSession;
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
