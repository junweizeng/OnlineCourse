package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.entity.TeachPlan;
import com.jmu.onlinecourse.utils.database.DatabasePlansUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ywww
 * @date 2021-01-17 2:08
 */
public class TeachPlansFragment extends Fragment {
    private View view = null;
    private GridLayout gridLayout;
    private DatabasePlansUtil plansUtil;
    private List<TeachPlan> teachPlans;
    private Spinner termSelectSpinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_teach_plans,null);
            gridLayout = view.findViewById(R.id.plans_gridLayout);
            termSelectSpinner = view.findViewById(R.id.term_select_banner);
            plansUtil = new DatabasePlansUtil(getContext());
            plansUtil.open();
            teachPlans = plansUtil.fetchPlansByYearAndTerm(2020,1);
            plansUtil.close();
            initPlansView();
            initListener();
        }
        // 解决使用replace产生重叠问题
        container.removeView(view);
        return view;
    }

    /**
     * 初始化网格布局
     */
    private void initPlansView(){
        gridLayout.removeAllViews();
        for(int i = 0;i<teachPlans.size();i++){
            List<String> viewTests = new ArrayList<>();
            viewTests.add(teachPlans.get(i).getDate());
            viewTests.add(teachPlans.get(i).getTeaching_content());
            viewTests.add(String.valueOf(teachPlans.get(i).getClass_hour()));
            for(int j=0;j<viewTests.size();j++){
                TextView textView = new TextView(getContext());
                textView.setText(viewTests.get(j));
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                // 设置宽度为屏幕的1/3
                if(j != 2){
                    textView.setWidth(getResources().getDisplayMetrics().widthPixels*2/5);
                } else {
                    textView.setWidth(getResources().getDisplayMetrics().widthPixels/5);
                }
                textView.setHeight(getResources().getDimensionPixelSize(R.dimen.plan_item_height));
                // 参数1为起始位置，参数2为占位数
                GridLayout.Spec columnSpec = GridLayout.spec(j,1);
                GridLayout.Spec rowSpec = GridLayout.spec(i,1);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
                gridLayout.addView(textView,params);
            }

        }
    }

    private void initListener(){
        // 下拉框监听器设置
        termSelectSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    plansUtil.open();
                    teachPlans = plansUtil.fetchPlansByYearAndTerm(2020,1);
                    plansUtil.close();
                    initPlansView();
                } else if(position == 1){
                    plansUtil.open();
                    teachPlans = plansUtil.fetchPlansByYearAndTerm(2020,2);
                    plansUtil.close();
                    initPlansView();
                } else if(position == 2){
                    plansUtil.open();
                    teachPlans = plansUtil.fetchPlansByYearAndTerm(2021,1);
                    plansUtil.close();
                    initPlansView();
                } else if(position == 3){
                    plansUtil.open();
                    teachPlans = plansUtil.fetchPlansByYearAndTerm(2021,2);
                    plansUtil.close();
                    initPlansView();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
