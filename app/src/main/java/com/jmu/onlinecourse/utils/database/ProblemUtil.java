package com.jmu.onlinecourse.utils.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jmu.onlinecourse.entity.Problem;
import com.jmu.onlinecourse.utils.helper.DatabaseHelper;

import java.util.ArrayList;

/**
 * 数据库查询工具类，用户查询problem表
 * @author czc
 */
public class ProblemUtil {
    public static ArrayList<Problem> findAllByExamination(Context context, int examination){
        ArrayList<Problem> problems = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(context, "online_course", null ,1);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        //查询一套测试的所有题目,通过一个List保存并返回
        Cursor cursor = db.rawQuery("select * from problem where examination = ? order by id", new String[]{String.valueOf(examination)});
        if(cursor.getCount() > 0){
            //通过游标将查询到的值存进List中
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String optionA = cursor.getString(cursor.getColumnIndex("option_a"));
                String optionB = cursor.getString(cursor.getColumnIndex("option_b"));
                String optionC = cursor.getString(cursor.getColumnIndex("option_c"));
                String optionD = cursor.getString(cursor.getColumnIndex("option_d"));
                examination = cursor.getInt(cursor.getColumnIndex("examination"));
                String answer = cursor.getString(cursor.getColumnIndex("answer"));
                problems.add(new Problem(id, title, optionA, optionB, optionC, optionD, examination, answer));
            }
        }
        cursor.close();
        db.close();
        return problems;
    }
}
