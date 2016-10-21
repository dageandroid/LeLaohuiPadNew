package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FootInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FOOT_INFO".
*/
public class FootInfoDao extends AbstractDao<FootInfo, Long> {

    public static final String TABLENAME = "FOOT_INFO";

    /**
     * Properties of entity FootInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CateId = new Property(1, int.class, "cateId", false, "CATE_ID");
        public final static Property CateName = new Property(2, String.class, "cateName", false, "CATE_NAME");
        public final static Property ProId = new Property(3, String.class, "proId", false, "PRO_ID");
        public final static Property ProName = new Property(4, String.class, "proName", false, "PRO_NAME");
        public final static Property SupplierId = new Property(5, String.class, "supplierId", false, "SUPPLIER_ID");
        public final static Property ProPrice = new Property(6, int.class, "proPrice", false, "PRO_PRICE");
        public final static Property ProPic = new Property(7, String.class, "proPic", false, "PRO_PIC");
        public final static Property MealTime = new Property(8, int.class, "mealTime", false, "MEAL_TIME");
        public final static Property MealType = new Property(9, int.class, "mealType", false, "MEAL_TYPE");
        public final static Property Remark = new Property(10, String.class, "remark", false, "REMARK");
        public final static Property SupplierName = new Property(11, String.class, "supplierName", false, "SUPPLIER_NAME");
        public final static Property SupplierType = new Property(12, String.class, "supplierType", false, "SUPPLIER_TYPE");
        public final static Property ProNum = new Property(13, String.class, "proNum", false, "PRO_NUM");
    }


    public FootInfoDao(DaoConfig config) {
        super(config);
    }
    
    public FootInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FOOT_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CATE_ID\" INTEGER NOT NULL ," + // 1: cateId
                "\"CATE_NAME\" TEXT," + // 2: cateName
                "\"PRO_ID\" TEXT," + // 3: proId
                "\"PRO_NAME\" TEXT," + // 4: proName
                "\"SUPPLIER_ID\" TEXT," + // 5: supplierId
                "\"PRO_PRICE\" INTEGER NOT NULL ," + // 6: proPrice
                "\"PRO_PIC\" TEXT," + // 7: proPic
                "\"MEAL_TIME\" INTEGER NOT NULL ," + // 8: mealTime
                "\"MEAL_TYPE\" INTEGER NOT NULL ," + // 9: mealType
                "\"REMARK\" TEXT," + // 10: remark
                "\"SUPPLIER_NAME\" TEXT," + // 11: supplierName
                "\"SUPPLIER_TYPE\" TEXT," + // 12: supplierType
                "\"PRO_NUM\" TEXT);"); // 13: proNum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FOOT_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FootInfo entity) {
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
 
        String proId = entity.getProId();
        if (proId != null) {
            stmt.bindString(4, proId);
        }
 
        String proName = entity.getProName();
        if (proName != null) {
            stmt.bindString(5, proName);
        }
 
        String supplierId = entity.getSupplierId();
        if (supplierId != null) {
            stmt.bindString(6, supplierId);
        }
        stmt.bindLong(7, entity.getProPrice());
 
        String proPic = entity.getProPic();
        if (proPic != null) {
            stmt.bindString(8, proPic);
        }
        stmt.bindLong(9, entity.getMealTime());
        stmt.bindLong(10, entity.getMealType());
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(11, remark);
        }
 
        String supplierName = entity.getSupplierName();
        if (supplierName != null) {
            stmt.bindString(12, supplierName);
        }
 
        String supplierType = entity.getSupplierType();
        if (supplierType != null) {
            stmt.bindString(13, supplierType);
        }
 
        String proNum = entity.getProNum();
        if (proNum != null) {
            stmt.bindString(14, proNum);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FootInfo entity) {
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
 
        String proId = entity.getProId();
        if (proId != null) {
            stmt.bindString(4, proId);
        }
 
        String proName = entity.getProName();
        if (proName != null) {
            stmt.bindString(5, proName);
        }
 
        String supplierId = entity.getSupplierId();
        if (supplierId != null) {
            stmt.bindString(6, supplierId);
        }
        stmt.bindLong(7, entity.getProPrice());
 
        String proPic = entity.getProPic();
        if (proPic != null) {
            stmt.bindString(8, proPic);
        }
        stmt.bindLong(9, entity.getMealTime());
        stmt.bindLong(10, entity.getMealType());
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(11, remark);
        }
 
        String supplierName = entity.getSupplierName();
        if (supplierName != null) {
            stmt.bindString(12, supplierName);
        }
 
        String supplierType = entity.getSupplierType();
        if (supplierType != null) {
            stmt.bindString(13, supplierType);
        }
 
        String proNum = entity.getProNum();
        if (proNum != null) {
            stmt.bindString(14, proNum);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FootInfo readEntity(Cursor cursor, int offset) {
        FootInfo entity = new FootInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // cateId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cateName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // proId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // proName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // supplierId
            cursor.getInt(offset + 6), // proPrice
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // proPic
            cursor.getInt(offset + 8), // mealTime
            cursor.getInt(offset + 9), // mealType
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // remark
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // supplierName
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // supplierType
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // proNum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FootInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCateId(cursor.getInt(offset + 1));
        entity.setCateName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSupplierId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setProPrice(cursor.getInt(offset + 6));
        entity.setProPic(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMealTime(cursor.getInt(offset + 8));
        entity.setMealType(cursor.getInt(offset + 9));
        entity.setRemark(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSupplierName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setSupplierType(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setProNum(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FootInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FootInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FootInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
