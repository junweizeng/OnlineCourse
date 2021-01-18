package com.jmu.onlinecourse.utils.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jmu.onlinecourse.entity.CollectionInfo;
import com.jmu.onlinecourse.utils.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author zjw
 * @date 2021/1/17 22:11
 * @ClassName DatabaseCollectionUtil
 */
public class DatabaseCollectionUtil {

    private static final String TAG = "DatabaseUserUtil";
    private static final String TRUE = "1";
    private static final String FALSE = "0";

    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "online_course";

    /**
     * 表名
     */
    private static final String DATABASE_TABLE = "collection";

    /**
     * 表中各个字段
     */
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE= "type";

    /**
     * 上下文环境
     */
    private final Context mContext;

    /**
     * 有参构造函数 - 获取到上下文环境，以获取操作数据库的权限
     *
     * @param context 上下文环境
     */
    public DatabaseCollectionUtil(Context context) {
        this.mContext = context;
    }

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * 此方法用于创建/打开connection
     *
     * @throws SQLException 错误信息
     */
    private void open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, 1);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * 此方法用于关闭connection
     */
    private void close() {
        mDbHelper.close();
    }

    /**
     * 将收藏信息插入到表汇总
     * @param ID id值
     * @param name 名称
     * @param type 类型（video、ppt、article）三种类型
     * @throws SQLException SQL执行错误异常
     */
    public void insert(long ID, String name, String type) throws SQLException {
        open();
        String insertSql = "INSERT INTO collection VALUES("
                + String.valueOf(ID) + ", '"
                + name + "', '"
                + type + "')";
        mDb.execSQL(insertSql);
        close();
    }

    /**
     * 将收藏记录从表中抹除
     * @param ID id值
     * @param type 类型（video、ppt、article）三种类型
     */
    public void delete(long ID, String type) {
        open();
        String deleteSql = "DELETE FROM collection WHERE ID=" + String.valueOf(ID) + " AND type='" + type + "'";
        mDb.execSQL(deleteSql);
        close();
    }

    /**
     * 获取到所有的收藏信息
     * @return 返回所有的收藏信息
     */
    public List<CollectionInfo> fetchAllCollectionInfo() {
        open();
        List<CollectionInfo> result = new ArrayList<>();
        Cursor mCursor = mDb.rawQuery("SELECT * FROM collection", null);
        mCursor.moveToNext();
        while(!mCursor.isAfterLast()) {
            CollectionInfo collectionInfo = new CollectionInfo();
            collectionInfo.setID(mCursor.getLong(mCursor.getColumnIndex("ID")));
            collectionInfo.setName(mCursor.getString(mCursor.getColumnIndex("name")));
            collectionInfo.setType(mCursor.getString(mCursor.getColumnIndex("type")));
            result.add(collectionInfo);
            mCursor.moveToNext();
        }
        mCursor.close();
        close();
        Collections.reverse(result);
        return result;
    }

    /**
     * 判断某种类型的某条记录是否已经存在于表中
     * @param ID 记录的唯一标识
     * @param type 收藏类型
     * @return true/false
     */
    public boolean isInCollection(long ID, String type) {
        open();
        Cursor mCursor = mDb.query(DATABASE_TABLE,
                new String[]{"count(*)"},
                "ID=" + String.valueOf(ID) + " AND type='" + type + "'",
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
        Objects.requireNonNull(mCursor).close();
        close();
        return !count.equals(FALSE);
    }
}
