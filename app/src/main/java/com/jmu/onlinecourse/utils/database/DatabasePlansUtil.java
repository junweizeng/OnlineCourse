package com.jmu.onlinecourse.utils.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jmu.onlinecourse.entity.TeachPlan;
import com.jmu.onlinecourse.utils.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ywww
 * @date 2021-01-17 17:03
 */
public class DatabasePlansUtil {
    private final Context context;
    private final static int COLUMN_NUM = 4;

    public DatabasePlansUtil(Context context) {
        this.context = context;
    }

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public void open() throws SQLException {
        dbHelper = new DatabaseHelper(context,"online_course",null,1);
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    /**
     * 根据学年和学期获得改学期的教学计划
     * @param year 学年
     * @param term 学期
     * @return 教学计划
     */
    public List<TeachPlan> fetchPlansByYearAndTerm(int year,int term){
        List<TeachPlan> teachPlans = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM teach_plans WHERE " +
                "begin_year="+year+" AND term="+term, null);
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            Log.d("TAG","1");
            TeachPlan teachPlan = new TeachPlan();
            teachPlan.setBegin_year(cursor.getInt(cursor.getColumnIndex("begin_year")));
            teachPlan.setTerm(cursor.getInt(cursor.getColumnIndex("term")));
            teachPlan.setDate(cursor.getString(cursor.getColumnIndex("date")));
            teachPlan.setTeaching_content(cursor.getString(cursor.getColumnIndex("teaching_content")));
            teachPlan.setClass_hour(cursor.getDouble(cursor.getColumnIndex("class_hour")));
            teachPlans.add(teachPlan);
            cursor.moveToNext();
        }
        return teachPlans;
    }
}
