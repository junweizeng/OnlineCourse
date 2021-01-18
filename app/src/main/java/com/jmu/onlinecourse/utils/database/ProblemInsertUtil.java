package com.jmu.onlinecourse.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import com.jmu.onlinecourse.entity.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 插入online_course.problem表数据
 * @author czc
 */
public class ProblemInsertUtil {
    private final static String COLUMN_TITLE = "title";
    private final static String COLUMN_OPTION_A = "option_a";
    private final static String COLUMN_OPTION_B = "option_b";
    private final static String COLUMN_OPTION_C = "option_c";
    private final static String COLUMN_OPTION_D = "option_d";
    private final static String COLUMN_EXAMINATION = "examination";
    private final static String COLUMN_ANSWER = "answer";
    private final static String TABLE_NAME = "problem";

    /**
     * 从txt文件中获取数据,已List的形式保存
     * @param context 上下文，用于连接SQLite数据库
     * @return 返回存储txt中数据的List<Problem>
     */
    public static List<Problem> getDataFromTxt(Context context){
        AssetManager assetManager = context.getAssets();
        List<Problem> problems = new ArrayList<>();
        InputStream is = null;
        BufferedReader br = null;
        try {
            //读取assets文件夹下的problems.txt文件
            is = assetManager.open("problems.txt");
            br = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = br.readLine()) != null){
                String title = line;
                String optionA = br.readLine();
                String optionB = br.readLine();
                String optionC = br.readLine();
                String optionD = br.readLine();
                int examination = Integer.parseInt(br.readLine());
                String answer = br.readLine();
                problems.add(new Problem(title, optionA, optionB, optionC, optionD, examination, answer));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                if(br != null) {
                    br.close();
                }
                if(is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return problems;
    }

    /**
     * 将数据插入到SQLite数据库中
     * @param db SQLiteDatabase，用于插入数据
     * @param problems 保存了需要插入的数据
     */
    public static void insertDataIntoProblem(SQLiteDatabase db, List<Problem> problems){
        //手动开启事务，能够减少插入时间
        db.beginTransaction();
        for(Problem p : problems){
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, p.getTitle());
            values.put(COLUMN_OPTION_A, p.getOptionA());
            values.put(COLUMN_OPTION_B, p.getOptionB());
            values.put(COLUMN_OPTION_C, p.getOptionC());
            values.put(COLUMN_OPTION_D, p.getOptionD());
            values.put(COLUMN_EXAMINATION, p.getExamination());
            values.put(COLUMN_ANSWER, p.getAnswer());
            db.insert(TABLE_NAME, null, values);
        }
        //设置事务处理成功
        db.setTransactionSuccessful();
        //处理完成
        db.endTransaction();
    }
}
