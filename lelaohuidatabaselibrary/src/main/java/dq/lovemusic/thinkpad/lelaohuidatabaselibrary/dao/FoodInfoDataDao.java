package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FoodInfoData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FOOD_INFO_DATA".
*/
public class FoodInfoDataDao extends AbstractDao<FoodInfoData, Long> {

    public static final String TABLENAME = "FOOD_INFO_DATA";

    /**
     * Properties of entity FoodInfoData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CateId = new Property(1, Long.class, "cateId", false, "CATE_ID");
        public final static Property CateName = new Property(2, String.class, "cateName", false, "CATE_NAME");
        public final static Property ProId = new Property(3, String.class, "proId", false, "PRO_ID");
        public final static Property ProName = new Property(4, String.class, "proName", false, "PRO_NAME");
        public final static Property SupplierId = new Property(5, String.class, "supplierId", false, "SUPPLIER_ID");
        public final static Property ProPrice = new Property(6, int.class, "proPrice", false, "PRO_PRICE");
        public final static Property ProPic = new Property(7, String.class, "proPic", false, "PRO_PIC");
        public final static Property MealTime = new Property(8, String.class, "mealTime", false, "MEAL_TIME");
        public final static Property MealType = new Property(9, String.class, "mealType", false, "MEAL_TYPE");
        public final static Property Remark = new Property(10, String.class, "remark", false, "REMARK");
        public final static Property SupplierName = new Property(11, String.class, "supplierName", false, "SUPPLIER_NAME");
        public final static Property SupplierType = new Property(12, String.class, "supplierType", false, "SUPPLIER_TYPE");
        public final static Property BuyNum = new Property(13, int.class, "buyNum", false, "BUY_NUM");
        public final static Property IsScope = new Property(14, String.class, "isScope", false, "IS_SCOPE");
        public final static Property UnineqKey = new Property(15, String.class, "unineqKey", false, "UNINEQ_KEY");
    }


    public FoodInfoDataDao(DaoConfig config) {
        super(config);
    }
    
    public FoodInfoDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FOOD_INFO_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CATE_ID\" INTEGER," + // 1: cateId
                "\"CATE_NAME\" TEXT," + // 2: cateName
                "\"PRO_ID\" TEXT UNIQUE ," + // 3: proId
                "\"PRO_NAME\" TEXT," + // 4: proName
                "\"SUPPLIER_ID\" TEXT," + // 5: supplierId
                "\"PRO_PRICE\" INTEGER NOT NULL ," + // 6: proPrice
                "\"PRO_PIC\" TEXT," + // 7: proPic
                "\"MEAL_TIME\" TEXT," + // 8: mealTime
                "\"MEAL_TYPE\" TEXT," + // 9: mealType
                "\"REMARK\" TEXT," + // 10: remark
                "\"SUPPLIER_NAME\" TEXT," + // 11: supplierName
                "\"SUPPLIER_TYPE\" TEXT," + // 12: supplierType
                "\"BUY_NUM\" INTEGER NOT NULL ," + // 13: buyNum
                "\"IS_SCOPE\" TEXT," + // 14: isScope
                "\"UNINEQ_KEY\" TEXT UNIQUE );"); // 15: unineqKey
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FOOD_INFO_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FoodInfoData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long cateId = entity.getCateId();
        if (cateId != null) {
            stmt.bindLong(2, cateId);
        }
 
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
 
        String mealTime = entity.getMealTime();
        if (mealTime != null) {
            stmt.bindString(9, mealTime);
        }
 
        String mealType = entity.getMealType();
        if (mealType != null) {
            stmt.bindString(10, mealType);
        }
 
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
        stmt.bindLong(14, entity.getBuyNum());
 
        String isScope = entity.getIsScope();
        if (isScope != null) {
            stmt.bindString(15, isScope);
        }
 
        String unineqKey = entity.getUnineqKey();
        if (unineqKey != null) {
            stmt.bindString(16, unineqKey);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FoodInfoData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long cateId = entity.getCateId();
        if (cateId != null) {
            stmt.bindLong(2, cateId);
        }
 
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
 
        String mealTime = entity.getMealTime();
        if (mealTime != null) {
            stmt.bindString(9, mealTime);
        }
 
        String mealType = entity.getMealType();
        if (mealType != null) {
            stmt.bindString(10, mealType);
        }
 
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
        stmt.bindLong(14, entity.getBuyNum());
 
        String isScope = entity.getIsScope();
        if (isScope != null) {
            stmt.bindString(15, isScope);
        }
 
        String unineqKey = entity.getUnineqKey();
        if (unineqKey != null) {
            stmt.bindString(16, unineqKey);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FoodInfoData readEntity(Cursor cursor, int offset) {
        FoodInfoData entity = new FoodInfoData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // cateId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cateName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // proId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // proName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // supplierId
            cursor.getInt(offset + 6), // proPrice
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // proPic
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // mealTime
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // mealType
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // remark
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // supplierName
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // supplierType
            cursor.getInt(offset + 13), // buyNum
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // isScope
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // unineqKey
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FoodInfoData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCateId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setCateName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSupplierId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setProPrice(cursor.getInt(offset + 6));
        entity.setProPic(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMealTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMealType(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setRemark(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSupplierName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setSupplierType(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setBuyNum(cursor.getInt(offset + 13));
        entity.setIsScope(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setUnineqKey(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FoodInfoData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FoodInfoData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FoodInfoData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
