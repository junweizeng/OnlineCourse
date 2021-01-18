package com.jmu.onlinecourse.utils.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jmu.onlinecourse.activity.MainActivity;
import com.jmu.onlinecourse.entity.Problem;
import com.jmu.onlinecourse.entity.TeachPlan;
import com.jmu.onlinecourse.utils.PlansDataProviderUtil;
import com.jmu.onlinecourse.utils.database.ProblemInsertUtil;

import java.util.List;

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
    private Context context;
    public static final String PROBLEM = "CREATE TABLE problem(id integer primary key autoincrement," +
            "  title text," +
            "  option_a text," +
            "  option_b text," +
            "  option_c text," +
            "  option_d text," +
            "  examination integer," +
            "  answer text)";
    public static final String SCORE = "CREATE TABLE score(id integer primary key autoincrement," +
            "  score integer)";
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
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
        //创建problem表sql语句
        db.execSQL(PROBLEM);
        //从txt文件获取数据
        List<Problem> problems = ProblemInsertUtil.getDataFromTxt(context);
        //将数据插入到数据库
        ProblemInsertUtil.insertDataIntoProblem(db, problems);
        db.execSQL(SCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
