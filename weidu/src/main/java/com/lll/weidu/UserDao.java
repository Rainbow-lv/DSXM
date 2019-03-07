package com.lll.weidu;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lll.weidu.bean.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UserId = new Property(0, long.class, "userId", true, "_id");
        public final static Property HeadPic = new Property(1, String.class, "headPic", false, "HEAD_PIC");
        public final static Property NickName = new Property(2, String.class, "nickName", false, "NICK_NAME");
        public final static Property Phone = new Property(3, String.class, "phone", false, "PHONE");
        public final static Property SessionId = new Property(4, String.class, "sessionId", false, "SESSION_ID");
        public final static Property Sex = new Property(5, int.class, "sex", false, "SEX");
        public final static Property Status = new Property(6, int.class, "status", false, "STATUS");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: userId
                "\"HEAD_PIC\" TEXT," + // 1: headPic
                "\"NICK_NAME\" TEXT," + // 2: nickName
                "\"PHONE\" TEXT," + // 3: phone
                "\"SESSION_ID\" TEXT," + // 4: sessionId
                "\"SEX\" INTEGER NOT NULL ," + // 5: sex
                "\"STATUS\" INTEGER NOT NULL );"); // 6: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUserId());
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(2, headPic);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(3, nickName);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(5, sessionId);
        }
        stmt.bindLong(6, entity.getSex());
        stmt.bindLong(7, entity.getStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUserId());
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(2, headPic);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(3, nickName);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(5, sessionId);
        }
        stmt.bindLong(6, entity.getSex());
        stmt.bindLong(7, entity.getStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.getLong(offset + 0), // userId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // headPic
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // nickName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // phone
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sessionId
            cursor.getInt(offset + 5), // sex
            cursor.getInt(offset + 6) // status
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setUserId(cursor.getLong(offset + 0));
        entity.setHeadPic(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNickName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSessionId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSex(cursor.getInt(offset + 5));
        entity.setStatus(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setUserId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}