package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port;

import java.util.List;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public interface DBOperatorImp {
    Object queryData(Object t);
    List<Object> queryDataList(Object t);
     void updateData(Object t);
     void updateData(List<?> t);
     void intsert(Object t);
     void insert(List<?> t);
     void delete(Object t);
     void delete(List<?> t);
      DaoImp getWritableDao();
      DaoImp getReadDao();
}
