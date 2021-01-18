package com.jmu.onlinecourse.utils.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jmu.onlinecourse.activity.MainActivity;
import com.jmu.onlinecourse.entity.TeachPlan;
import com.jmu.onlinecourse.utils.PlansDataProviderUtil;

/**
 * @author ywww
 * @date 2021-01-17 15:43
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * 教学计划表创建语句
     */
    private static final String CREATE_TEACH_PLANS_TABLE =
            "CREATE TABLE teach_plans (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "begin_year INTEGER," +
                    "term INTEGER," +
                    "date TEXT,"+
                    "teaching_content TEXT," +
                    "class_hour DOUBLE)";

    private static final String CREATE_FEEDBACK_LOGS_TABLE =
            "CREATE TABLE feedback_logs (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "date TEXT," +
                    "content TEXT)";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建教学计划数据
        db.execSQL(CREATE_TEACH_PLANS_TABLE);
        for(TeachPlan tp: PlansDataProviderUtil.teachPlans_data){
            ContentValues contentValues = new ContentValues();
            contentValues.put("begin_year",tp.getBegin_year());
            contentValues.put("term",tp.getTerm());
            contentValues.put("date",tp.getDate());
            contentValues.put("teaching_content",tp.getTeaching_content());
            contentValues.put("class_hour",tp.getClass_hour());
            db.insert("teach_plans",null,contentValues);
        }
        db.execSQL(CREATE_FEEDBACK_LOGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
