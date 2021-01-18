package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.activity.MainActivity;
import com.jmu.onlinecourse.adapter.FeedbackAdapter;
import com.jmu.onlinecourse.entity.FeedbackLog;
import com.jmu.onlinecourse.utils.database.DatabaseFeedbackUtil;

import java.util.List;

/**
 * @author ywww
 * @date 2021-01-18 21:56
 */
public class FeedbackLogsFragment extends Fragment {
    private View view;
    private ImageView backButton;
    private RecyclerView feedbackRecycler;
    private MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feedback_logs,null);
        mainActivity = (MainActivity) getActivity();
       init();

        return view;
    }

    private void init(){
        backButton = view.findViewById(R.id.feedback_logs_back_button);
        feedbackRecycler = view.findViewById(R.id.feedback_recyclerview);
        // 拿到需要的数据
        DatabaseFeedbackUtil databaseFeedbackUtil = new DatabaseFeedbackUtil(mainActivity);
        databaseFeedbackUtil.open();
        List<FeedbackLog> feedbackLogs = databaseFeedbackUtil.fetchFeedBackInfo();
        databaseFeedbackUtil.close();
        // 设置recyclerView
        FeedbackAdapter feedbackAdapter = new FeedbackAdapter(feedbackLogs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        feedbackRecycler.setLayoutManager(layoutManager);
        feedbackRecycler.setAdapter(feedbackAdapter);
        initListener();
    }

    private void initListener(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getFManager().popBackStack();
            }
        });
    }
}
