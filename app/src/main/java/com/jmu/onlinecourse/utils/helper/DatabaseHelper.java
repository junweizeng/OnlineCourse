package com.jmu.onlinecourse.utils.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jmu.onlinecourse.entity.VideoInfo;
import com.jmu.onlinecourse.utils.DataProviderUtils;

/**
 * @author zjw
 * @date 2021/1/17 0:14
 * @ClassName DatabaseHelper
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * user表创建SQL语句
     */
    private static final String CREATE_VIDEO_TABLE =
            "CREATE TABLE video (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "video_name TEXT NOT NULL, " +
                    "summary TEXT, " +
                    "image_url TEXT, " +
                    "likes INTEGER DEFAULT 0, " +
                    "collection INTEGER DEFAULT 0, " +
                    "play_volume INTEGER DEFAULT 0, " +
                    "detail_url TEXT)";

    /**
     * user表创建SQL语句
     */
    private static final String CREATE_USER_TABLE = "CREATE TABLE user (" +
            "acount text PRIMARY KEY, " +
            "password text NOT NULL, " +
            "username text NOT NULL)";

    /**
     * Collection表创建SQL语句
     */
    private static final String CREATE_COLLECTION_TABLE = "CREATE TABLE collection " +
            "(ID INTEGER, " +
            "name text NOT NULL, " +
            "type text NOT NULL)";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COLLECTION_TABLE);
        db.execSQL(CREATE_VIDEO_TABLE);
        for(VideoInfo videoInfo: DataProviderUtils.getAllVideoInfo()) {
            ContentValues initialValues = new ContentValues();
            initialValues.put("video_name", videoInfo.getVideoName());
            initialValues.put("summary", videoInfo.getSummary());
            initialValues.put("image_url", videoInfo.getImageUrl());
            initialValues.put("detail_url", videoInfo.getDetailUrl());
            db.insert("video", null, initialValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
