package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port;

import android.database.Cursor;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public interface DBOperatorImp {
    BaseBean queryData(BaseBean t);
    List<? extends BaseBean> queryDataList(BaseBean t);
     void updateData(BaseBean t);
     void intsert(BaseBean t);
     void intsert(List<? extends BaseBean> t);
     void delete(BaseBean t);
     Cursor query(BaseBean t);
    void updateData(List<? extends BaseBean>  t);
    AbstractDao get();
    void delete(String ...condition);
}
