package com.jmu.onlinecourse.utils.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jmu.onlinecourse.entity.VideoInfo;
import com.jmu.onlinecourse.utils.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjw
 * @date 2021/1/16 14:52
 * @ClassName DatabaseVideoUtil
 */
public class DatabaseVideoUtil {
    private static final String TAG = "DatabaseVideoUtil";

    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "online_course";

    /**
     * 表名
     */
    private static final String DATABASE_TABLE = "video";

    /**
     * 表中各个字段
     */
    public static final String KEY_ID = "ID";
    public static final String KEY_VIDEO_NAME = "video_name";
    public static final String KEY_SUMMARY = "summary";
    public static final String KEY_IMAGE_URL = "image_url";
    public static final String KEY_LIKES = "likes";
    public static final String KEY_COLLECTION = "collection";
    public static final String KEY_PLAY_VOLUME = "play_volume";
    public static final String KEY_DETAIL_URL = "detail_url";

    /**
     * user表创建SQL语句
     */
    private static final String CREATE_TABLE =
            "CREATE TABLE " + DATABASE_TABLE + " ("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_VIDEO_NAME + " TEXT NOT NULL, "
                    + KEY_SUMMARY + " TEXT, "
                    + KEY_IMAGE_URL + " TEXT, "
                    + KEY_LIKES + " INTEGER DEFAULT 0, "
                    + KEY_COLLECTION + " INTEGER DEFAULT 0, "
                    + KEY_PLAY_VOLUME + " INTEGER DEFAULT 0, "
                    + KEY_DETAIL_URL + " TEXT)";

    /**
     * 上下文环境
     */
    private final Context mContext;

    /**
     * 有参构造函数 - 获取到上下文环境，以获取操作数据库的权限
     *
     * @param context 上下文环境
     */
    public DatabaseVideoUtil(Context context) {
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

    public VideoInfo fetchVideoInfoById(long id) {
        open();
        VideoInfo videoInfo = new VideoInfo();
        Cursor mCursor = mDb.rawQuery("SELECT * FROM video WHERE ID=" + String.valueOf(id), null);
        mCursor.moveToNext();
        if(!mCursor.isAfterLast()) {
            videoInfo.setID(mCursor.getLong(mCursor.getColumnIndex("ID")));
            videoInfo.setVideoName(mCursor.getString(mCursor.getColumnIndex("video_name")));
            videoInfo.setSummary(mCursor.getString(mCursor.getColumnIndex("summary")));
            videoInfo.setImageUrl(mCursor.getString(mCursor.getColumnIndex("image_url")));
            videoInfo.setLikes(mCursor.getInt(mCursor.getColumnIndex("likes")));
            videoInfo.setCollection(mCursor.getInt(mCursor.getColumnIndex("collection")));
            videoInfo.setPlayVolume(mCursor.getInt(mCursor.getColumnIndex("play_volume")));
            videoInfo.setDetailUrl(mCursor.getString(mCursor.getColumnIndex("detail_url")));
        }
        return videoInfo;
    }

    public List<VideoInfo> fetchAllVideoInfo() {
        open();
        List<VideoInfo> videoInfoList = new ArrayList<>();
        Cursor mCursor = mDb.rawQuery("SELECT * FROM video", null);
        mCursor.moveToNext();
        while(!mCursor.isAfterLast()) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setID(mCursor.getLong(mCursor.getColumnIndex("ID")));
            videoInfo.setVideoName(mCursor.getString(mCursor.getColumnIndex("video_name")));
            videoInfo.setSummary(mCursor.getString(mCursor.getColumnIndex("summary")));
            videoInfo.setImageUrl(mCursor.getString(mCursor.getColumnIndex("image_url")));
            videoInfo.setLikes(mCursor.getInt(mCursor.getColumnIndex("likes")));
            videoInfo.setCollection(mCursor.getInt(mCursor.getColumnIndex("collection")));
            videoInfo.setPlayVolume(mCursor.getInt(mCursor.getColumnIndex("play_volume")));
            videoInfo.setDetailUrl(mCursor.getString(mCursor.getColumnIndex("detail_url")));
            videoInfoList.add(videoInfo);
            mCursor.moveToNext();
        }
        mCursor.close();
        close();
        return videoInfoList;
    }

    /**
     * 通过给定页数获取指定五条记录
     * @param page 页数
     * @return 返回五条视频信息记录
     */
    public List<VideoInfo> fetchFiveVideoInfo(int page) {
        List<VideoInfo> list = fetchAllVideoInfo();
        List<VideoInfo> result = new ArrayList<>();
        int begin = page * 5;
        int end;
        if(begin + 5 > list.size()) {
            end = list.size();
        } else {
            end = begin + 5;
        }
        for(int i = begin; i < end; ++i) {
            result.add(list.get(i));
        }
        return result;
    }



    /**
     * 让某条记录的某个字段 增加1 或 减少1
     * @param ID 表的主键，唯一标识
     * @param field 定位到某个字段
     * @param isIncrease 是否增加1
     */
    public void inOrDecreaseSomething(long ID, String field, boolean isIncrease) {
        open();
        String ch;
        if(!isIncrease) {
            ch = "-";
        } else {
            ch = "+";
        }
        String updateSql = "UPDATE video SET " + field + "=" + field + ch + "1 WHERE ID=" + String.valueOf(ID);
        mDb.execSQL(updateSql);
        close();
    }

    public void increaseLikes(long ID) {
        inOrDecreaseSomething(ID, "likes", true);
    }

    public void decreaseLikes(long ID) {
        inOrDecreaseSomething(ID, "likes", false);
    }

    public void increaseCollection(long ID) {
        inOrDecreaseSomething(ID, "collection", true);
    }

    public void decreaseCollection(long ID) {
        inOrDecreaseSomething(ID, "collection", false);
    }

    public void increasePlayVolume(long ID) {
        inOrDecreaseSomething(ID, "play_volume", true);
    }

    /**
     * 获取到某条记录的某个字段值
     * @param ID 表的主键，唯一标识
     * @param field 某个字段
     * @return 字段值
     */
    public int fetchSomething(long ID, String field) {
        open();
        String querySql = "SELECT " + field + " FROM video WHERE ID=" + String.valueOf(ID);
        Cursor cursor = mDb.rawQuery(querySql, null);
        int count = 0;
        cursor.moveToNext();
        if(!cursor.isAfterLast()) {
            count = cursor.getInt(cursor.getColumnIndex(field));
        }
        cursor.close();
        close();
        return count;
    }

    public int fetchLikes(long ID) {
        return fetchSomething(ID, "likes");
    }

    public int fetchCollection(long ID) {
        return fetchSomething(ID, "collection");
    }

    public int fetchPlayVolume(long ID) {
        return fetchSomething(ID, "play_volume");
    }


}
