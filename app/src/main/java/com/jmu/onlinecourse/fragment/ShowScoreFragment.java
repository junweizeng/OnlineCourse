package com.jmu.onlinecourse.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.utils.helper.DatabaseHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author 14548
 */
public class ShowScoreFragment extends Fragment {

    private LineChart scoreLineChart;
    private TitleBar titleBar;
    public ShowScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShowScoreFragment.
     */
    public static ShowScoreFragment newInstance() {
        ShowScoreFragment fragment = new ShowScoreFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_show_score, container, false);
        initView(view);
        return view;
    }
    private void initView(View view){
        scoreLineChart = view.findViewById(R.id.score_line_chart);
        titleBar = view.findViewById(R.id.show_score_titlebar);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                ShowScoreFragment fragment = (ShowScoreFragment) fragmentManager.findFragmentByTag("ssf");
                IndexFragment indexFragment = (IndexFragment) fragmentManager.findFragmentByTag("index");
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                getActivity().getSupportFragmentManager().beginTransaction().show(indexFragment).commit();
            }
        });
        drawLineChart();
    }
    private void drawLineChart(){
        XAxis xAxis = scoreLineChart.getXAxis();
        //设置x轴位于图像底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置x轴最大值
        xAxis.setAxisMinimum(1f);
        //设置y轴最小值
        xAxis.setAxisMaximum(5f);
        //设置x轴的间隔（加f因为参数类型是float）
        xAxis.setGranularity(1f);

        YAxis yAxis = scoreLineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        //设置y轴文字颜色
        yAxis.setTextColor(Color.BLUE);

        //设置及格线
        LimitLine ll1 = new LimitLine(60f, "及格");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll1.setTextSize(10f);
        yAxis.addLimitLine(ll1);
        //设置右侧坐标轴不可用
        scoreLineChart.getAxisRight().setEnabled(false);
        //设置描述内容
        Description description = new Description();
        description.setText("近五次测试成绩");
        description.setTextColor(Color.BLUE);
        scoreLineChart.setDescription(description);
        //获取近五次的成绩
        List<Entry> entries = getScore();
        LineDataSet data = new LineDataSet(entries, "折线图");
        LineData lineData = new LineData(data);

        scoreLineChart.setData(lineData);
    }

    /**
     * 从数据库中获取进五次的成绩
     * @return 返回包含成绩的List
     */
    private List<Entry> getScore(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(), "online_course", null, 1);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select  * from score order by id desc limit 0,5",null);
        List<Entry> entries = new ArrayList<>();
        int x = 1;
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                int score = cursor.getInt(cursor.getColumnIndex("score"));
                entries.add(new Entry(x++, score));
            }
        }
        cursor.close();
        db.close();
        return entries;
    }
}