package com.jmu.onlinecourse.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jmu.onlinecourse.entity.FeedbackLog;
import com.jmu.onlinecourse.utils.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ywww
 * @date 2021-01-18 21:37
 */
public class DatabaseFeedbackUtil {
    private Context context;

    public DatabaseFeedbackUtil(Context context) {
        this.context = context;
    }

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public void open(){
        dbHelper = new DatabaseHelper(context,"online_course",null,1);
        db = dbHelper.getWritableDatabase();
    }

    public List<FeedbackLog> fetchFeedBackInfo(){
        List<FeedbackLog> feedbackLogs = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM feedback_logs",null);
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            FeedbackLog feedbackLog = new FeedbackLog();
            feedbackLog.setDate(cursor.getString(cursor.getColumnIndex("date")));
            feedbackLog.setContent(cursor.getString(cursor.getColumnIndex("content")));
            feedbackLogs.add(feedbackLog);
            cursor.moveToNext();
        }
        return feedbackLogs;
    }

    public void insertFeedBackInfo(FeedbackLog feedbackLog){
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",feedbackLog.getDate());
        contentValues.put("content",feedbackLog.getContent());
        db.insert("feedback_logs",null,contentValues);
    }

    public void close(){
        db.close();
    }
}
