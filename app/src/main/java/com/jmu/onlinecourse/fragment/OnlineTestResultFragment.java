package com.jmu.onlinecourse.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.adapter.CoursewareAdapter;
import com.jmu.onlinecourse.adapter.ErrorAdapter;
import com.jmu.onlinecourse.entity.Problem;
import com.jmu.onlinecourse.utils.helper.DatabaseHelper;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnlineTestResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 展示测试结果
 * @author czc
 */
public class OnlineTestResultFragment extends Fragment {

    private static final String USERANSWERS = "userAnswers";
    private static final String CORRECTANSWERS = "correctAnswers";
    private static final int MAX = 10;
    private List<String> userAnswers;
    private List<Problem> correctAnswers;
    private List<String> errors;
    private TitleBar titleBar;
    private RecyclerView recyclerView;
    public OnlineTestResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param answers Parameter 1.
     * @return A new instance of fragment OnlineTestResultFragment.
     */
    public static OnlineTestResultFragment newInstance(ArrayList<String> answers, ArrayList<Problem> correctAnswer) {
        OnlineTestResultFragment fragment = new OnlineTestResultFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(USERANSWERS, answers);
        ArrayList list = new ArrayList();
        list.add(correctAnswer);
        args.putParcelableArrayList(CORRECTANSWERS, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userAnswers = getArguments().getStringArrayList(USERANSWERS);
            ArrayList list = getArguments().getParcelableArrayList(CORRECTANSWERS);
            correctAnswers = (List<Problem>) list.get(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_online_test_result, container, false);
        initView(view);
        return view;
    }
    private void initView(View view){
        titleBar = view.findViewById(R.id.onlineTest_retuslt_titlebar);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                OnlineTestResultFragment fragment = (OnlineTestResultFragment) fragmentManager.findFragmentByTag("otrf");
                IndexFragment indexFragment = (IndexFragment) fragmentManager.findFragmentByTag("index");
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                getActivity().getSupportFragmentManager().beginTransaction().show(indexFragment).commit();
            }
        });
        recyclerView = view.findViewById(R.id.errors);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        int score = processOption();
        TextView tvScore = view.findViewById(R.id.tv_score);
        tvScore.setText(String.valueOf(score));
        writeScoreToDatabase(score);
    }

    /**
     * 将正确答案与用户选择的答案比较，获取用户的成绩
     * @return 用户成绩
     */
    private int processOption(){
        int score = 0;
        errors = new ArrayList<>();
        for(int i = 0; i < MAX; i++){
            if(correctAnswers.get(i).getAnswer().equals(userAnswers.get(i))){
                score += 10;
            }else{
                errors.add(correctAnswers.get(i).getTitle());
            }
        }
        recyclerView.setAdapter(new ErrorAdapter(errors));
        return score;
    }

    /**
     * 将成绩写回数据库中，用于查看成绩部分，绘图
     * @param score 此次测试的成绩
     */
    private void writeScoreToDatabase(int score){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(), "online_course", null, 1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("score", score);
        db.insert("score", null, values);
    }


}