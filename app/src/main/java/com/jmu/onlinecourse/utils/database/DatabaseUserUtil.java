package com.jmu.onlinecourse.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author zjw
 * @date 2021/1/16 13:31
 * @ClassName DatabaseUserUtil
 */
public class DatabaseUserUtil {
    private static final String TAG = "DatabaseUserUtil";
    private static final String TRUE = "1";
    private static final String FALSE = "0";

    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "online_course";

    /**
     * 数据库版本
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * 表名
     */
    private static final String DATABASE_TABLE = "user";

    /**
     * 表中各个字段
     */
    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERNAME = "username";

    /**
     * user表创建SQL语句
     */
    private static final String CREATE_TABLE =
            "CREATE TABLE " + DATABASE_TABLE + " ("
                    + KEY_ACCOUNT + " text PRIMARY KEY, "
                    + KEY_PASSWORD + " text NOT NULL, "
                    + KEY_USERNAME + " text NOT NULL)";

    /**
     * 上下文环境
     */
    private final Context mContext;
    private final int newVersion;

    /**
     * 有参构造函数 - 获取到上下文环境，以获取操作数据库的权限
     *
     * @param context 上下文环境
     */
    public DatabaseUserUtil(Context context, int newVersion) {
        this.mContext = context;
        this.newVersion = newVersion;
    }

    private DatabaseUserHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * 内部类，用于创建和更新数据库的类
     */
    private static class DatabaseUserHelper extends SQLiteOpenHelper {
        DatabaseUserHelper(Context context, int newVersion) {
            super(context, DATABASE_NAME, null, newVersion);
        }

        /**
         * 当数据库不存在时，将会调用onCreate方法。
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "Creating DataBase: " + CREATE_TABLE);
            db.execSQL(CREATE_TABLE);
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_ACCOUNT, "1");
            initialValues.put(KEY_PASSWORD, "1");
            initialValues.put(KEY_USERNAME, "1");
            db.insert(DATABASE_TABLE, null, initialValues);
        }

        /**
         * 数据库版本更改时，将调用onUpgrade方法。
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        }
    }

    /**
     * 此方法用于创建/打开connection
     *
     * @return DatabaseUserUtil的实例
     * @throws SQLException 错误信息
     */
    public DatabaseUserUtil open() throws SQLException {
        mDbHelper = new DatabaseUserHelper(mContext, newVersion);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    /**
     * 此方法用于关闭connection
     */
    public void close() {
        mDbHelper.close();
    }


    /**
     * 插入数据到数据库中，如果发生错误返回-1
     * @param account 账号
     * @param password 密码
     * @param username 用户名
     * @return 返回新插入行的行ID；如果发生错误，返回-1
     */
    public long insert(String account, String password, String username) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ACCOUNT, account);
        initialValues.put(KEY_PASSWORD, password);
        initialValues.put(KEY_USERNAME, username);
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    public Boolean isLoginSuccess(String account, String password) {
        Cursor mCursor =
                mDb.query(true,
                        DATABASE_TABLE,
                        new String[]{"count(*)"},
                        KEY_ACCOUNT + "=" + account + " AND "
                                + KEY_PASSWORD + "=" + password,
                        null,
                        null,
                        null,
                        null,
                        null);
        String count = "0";
        if (mCursor != null) {
            mCursor.moveToFirst();
            count = mCursor.getString(mCursor.getColumnIndex("count(*)"));
        }
        if(count.equals(FALSE)) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean isAccountExists(String account) {
        Cursor mCursor = mDb.query(true,
                DATABASE_TABLE,
                new String[]{"count(*)"},
                KEY_ACCOUNT + "=" + account,
                null,
                null,
                null,
                null,
                null);
        String count = "0";
        if (mCursor != null) {
            mCursor.moveToFirst();
            count = mCursor.getString(mCursor.getColumnIndex("count(*)"));
        }
        if(count.equals(FALSE)) {
            return false;
        } else {
            return true;
        }
    }
}
