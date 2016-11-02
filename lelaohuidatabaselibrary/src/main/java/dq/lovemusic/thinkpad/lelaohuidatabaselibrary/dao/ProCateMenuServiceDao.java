package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateMenuService;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PRO_CATE_MENU_SERVICE".
*/
public class ProCateMenuServiceDao extends AbstractDao<ProCateMenuService, Long> {

    public static final String TABLENAME = "PRO_CATE_MENU_SERVICE";

    /**
     * Properties of entity ProCateMenuService.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CateId = new Property(1, long.class, "cateId", false, "CATE_ID");
        public final static Property CateName = new Property(2, String.class, "cateName", false, "CATE_NAME");
        public final static Property CateLevel = new Property(3, int.class, "cateLevel", false, "CATE_LEVEL");
        public final static Property ParentId = new Property(4, long.class, "parentId", false, "PARENT_ID");
        public final static Property IsLeaf = new Property(5, int.class, "isLeaf", false, "IS_LEAF");
        public final static Property IsDelete = new Property(6, int.class, "isDelete", false, "IS_DELETE");
        public final static Property ManagerId = new Property(7, String.class, "managerId", false, "MANAGER_ID");
        public final static Property ManagerName = new Property(8, String.class, "managerName", false, "MANAGER_NAME");
        public final static Property OrgId = new Property(9, long.class, "orgId", false, "ORG_ID");
        public final static Property OrgName = new Property(10, String.class, "orgName", false, "ORG_NAME");
        public final static Property OrgTypeId = new Property(11, int.class, "orgTypeId", false, "ORG_TYPE_ID");
        public final static Property AddTime = new Property(12, java.util.Date.class, "addTime", false, "ADD_TIME");
        public final static Property UpdTime = new Property(13, java.util.Date.class, "updTime", false, "UPD_TIME");
        public final static Property Remark = new Property(14, String.class, "remark", false, "REMARK");
        public final static Property Status = new Property(15, int.class, "status", false, "STATUS");
        public final static Property PinYin = new Property(16, String.class, "pinYin", false, "PIN_YIN");
        public final static Property PY = new Property(17, String.class, "pY", false, "P_Y");
        public final static Property IsPack = new Property(18, int.class, "isPack", false, "IS_PACK");
        public final static Property PackorgId = new Property(19, Long.class, "packorgId", false, "PACKORG_ID");
    }


    public ProCateMenuServiceDao(DaoConfig config) {
        super(config);
    }
    
    public ProCateMenuServiceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PRO_CATE_MENU_SERVICE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CATE_ID\" INTEGER NOT NULL ," + // 1: cateId
                "\"CATE_NAME\" TEXT," + // 2: cateName
                "\"CATE_LEVEL\" INTEGER NOT NULL ," + // 3: cateLevel
                "\"PARENT_ID\" INTEGER NOT NULL ," + // 4: parentId
                "\"IS_LEAF\" INTEGER NOT NULL ," + // 5: isLeaf
                "\"IS_DELETE\" INTEGER NOT NULL ," + // 6: isDelete
                "\"MANAGER_ID\" TEXT," + // 7: managerId
                "\"MANAGER_NAME\" TEXT," + // 8: managerName
                "\"ORG_ID\" INTEGER NOT NULL ," + // 9: orgId
                "\"ORG_NAME\" TEXT," + // 10: orgName
                "\"ORG_TYPE_ID\" INTEGER NOT NULL ," + // 11: orgTypeId
                "\"ADD_TIME\" INTEGER," + // 12: addTime
                "\"UPD_TIME\" INTEGER," + // 13: updTime
                "\"REMARK\" TEXT," + // 14: remark
                "\"STATUS\" INTEGER NOT NULL ," + // 15: status
                "\"PIN_YIN\" TEXT," + // 16: pinYin
                "\"P_Y\" TEXT," + // 17: pY
                "\"IS_PACK\" INTEGER NOT NULL ," + // 18: isPack
                "\"PACKORG_ID\" INTEGER);"); // 19: packorgId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PRO_CATE_MENU_SERVICE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ProCateMenuService entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCateId());
 
        String cateName = entity.getCateName();
        if (cateName != null) {
            stmt.bindString(3, cateName);
        }
        stmt.bindLong(4, entity.getCateLevel());
        stmt.bindLong(5, entity.getParentId());
        stmt.bindLong(6, entity.getIsLeaf());
        stmt.bindLong(7, entity.getIsDelete());
 
        String managerId = entity.getManagerId();
        if (managerId != null) {
            stmt.bindString(8, managerId);
        }
 
        String managerName = entity.getManagerName();
        if (managerName != null) {
            stmt.bindString(9, managerName);
        }
        stmt.bindLong(10, entity.getOrgId());
 
        String orgName = entity.getOrgName();
        if (orgName != null) {
            stmt.bindString(11, orgName);
        }
        stmt.bindLong(12, entity.getOrgTypeId());
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(13, addTime.getTime());
        }
 
        java.util.Date updTime = entity.getUpdTime();
        if (updTime != null) {
            stmt.bindLong(14, updTime.getTime());
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(15, remark);
        }
        stmt.bindLong(16, entity.getStatus());
 
        String pinYin = entity.getPinYin();
        if (pinYin != null) {
            stmt.bindString(17, pinYin);
        }
 
        String pY = entity.getPY();
        if (pY != null) {
            stmt.bindString(18, pY);
        }
        stmt.bindLong(19, entity.getIsPack());
 
        Long packorgId = entity.getPackorgId();
        if (packorgId != null) {
            stmt.bindLong(20, packorgId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ProCateMenuService entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCateId());
 
        String cateName = entity.getCateName();
        if (cateName != null) {
            stmt.bindString(3, cateName);
        }
        stmt.bindLong(4, entity.getCateLevel());
        stmt.bindLong(5, entity.getParentId());
        stmt.bindLong(6, entity.getIsLeaf());
        stmt.bindLong(7, entity.getIsDelete());
 
        String managerId = entity.getManagerId();
        if (managerId != null) {
            stmt.bindString(8, managerId);
        }
 
        String managerName = entity.getManagerName();
        if (managerName != null) {
            stmt.bindString(9, managerName);
        }
        stmt.bindLong(10, entity.getOrgId());
 
        String orgName = entity.getOrgName();
        if (orgName != null) {
            stmt.bindString(11, orgName);
        }
        stmt.bindLong(12, entity.getOrgTypeId());
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(13, addTime.getTime());
        }
 
        java.util.Date updTime = entity.getUpdTime();
        if (updTime != null) {
            stmt.bindLong(14, updTime.getTime());
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(15, remark);
        }
        stmt.bindLong(16, entity.getStatus());
 
        String pinYin = entity.getPinYin();
        if (pinYin != null) {
            stmt.bindString(17, pinYin);
        }
 
        String pY = entity.getPY();
        if (pY != null) {
            stmt.bindString(18, pY);
        }
        stmt.bindLong(19, entity.getIsPack());
 
        Long packorgId = entity.getPackorgId();
        if (packorgId != null) {
            stmt.bindLong(20, packorgId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ProCateMenuService readEntity(Cursor cursor, int offset) {
        ProCateMenuService entity = new ProCateMenuService( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // cateId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cateName
            cursor.getInt(offset + 3), // cateLevel
            cursor.getLong(offset + 4), // parentId
            cursor.getInt(offset + 5), // isLeaf
            cursor.getInt(offset + 6), // isDelete
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // managerId
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // managerName
            cursor.getLong(offset + 9), // orgId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // orgName
            cursor.getInt(offset + 11), // orgTypeId
            cursor.isNull(offset + 12) ? null : new java.util.Date(cursor.getLong(offset + 12)), // addTime
            cursor.isNull(offset + 13) ? null : new java.util.Date(cursor.getLong(offset + 13)), // updTime
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // remark
            cursor.getInt(offset + 15), // status
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // pinYin
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // pY
            cursor.getInt(offset + 18), // isPack
            cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19) // packorgId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ProCateMenuService entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCateId(cursor.getLong(offset + 1));
        entity.setCateName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCateLevel(cursor.getInt(offset + 3));
        entity.setParentId(cursor.getLong(offset + 4));
        entity.setIsLeaf(cursor.getInt(offset + 5));
        entity.setIsDelete(cursor.getInt(offset + 6));
        entity.setManagerId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setManagerName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setOrgId(cursor.getLong(offset + 9));
        entity.setOrgName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setOrgTypeId(cursor.getInt(offset + 11));
        entity.setAddTime(cursor.isNull(offset + 12) ? null : new java.util.Date(cursor.getLong(offset + 12)));
        entity.setUpdTime(cursor.isNull(offset + 13) ? null : new java.util.Date(cursor.getLong(offset + 13)));
        entity.setRemark(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setStatus(cursor.getInt(offset + 15));
        entity.setPinYin(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setPY(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setIsPack(cursor.getInt(offset + 18));
        entity.setPackorgId(cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ProCateMenuService entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ProCateMenuService entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ProCateMenuService entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}