package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.activity.MainActivity;
import com.jmu.onlinecourse.entity.FeedbackLog;
import com.jmu.onlinecourse.utils.database.DatabaseFeedbackUtil;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ywww
 * @date 2021-01-18 16:10
 */
public class FeedbackFragment extends Fragment {
    private View view;
    private final String total_num = "/600";
    private ImageView backButton;
    private RoundButton submitButton;
    private TextView showLogButton;
    private EditText feedbackEdit;
    private TextView fontNumText;
    private MainActivity mainActivity;
    private FeedbackLogsFragment feedbackLogsFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feedback,null);
        mainActivity = (MainActivity) getActivity();
        init();
        return view;
    }
    private void init(){
        backButton = view.findViewById(R.id.feedback_back_button);
        submitButton = view.findViewById(R.id.submit_feedback_button);
        showLogButton = view.findViewById(R.id.to_feedback_log_view);
        feedbackEdit = view.findViewById(R.id.feedback_edit);
        fontNumText = view.findViewById(R.id.fond_num_view);
        initListener();
    }

    private void initListener(){
        // 返回按钮
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getFManager().popBackStack();
            }
        });

        // 提交按钮
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedbackEdit.getText().toString().length() > 0){
                    // 获取时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd日\nHH:mm:ss");
                    Date currentData = new Date(System.currentTimeMillis());
                    String date = simpleDateFormat.format(currentData);
                    FeedbackLog feedbackLog = new FeedbackLog(date,feedbackEdit.getText().toString());
                    // 插入数据
                    DatabaseFeedbackUtil databaseFeedbackUtil = new DatabaseFeedbackUtil(mainActivity);
                    databaseFeedbackUtil.open();
                    databaseFeedbackUtil.insertFeedBackInfo(feedbackLog);
                    databaseFeedbackUtil.close();
                }
                Toast.makeText(mainActivity,"提交成功",Toast.LENGTH_SHORT).show();
                feedbackEdit.setText("");
            }
        });

        // 查看历史反馈
        showLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackEdit.setText("");
                FragmentTransaction fTransaction = mainActivity.getFManager().beginTransaction();
                fTransaction.hide(FeedbackFragment.this);
                if(feedbackLogsFragment == null || mainActivity.getFManager().findFragmentByTag("feedback_logs")==null){
                    feedbackLogsFragment = new FeedbackLogsFragment();
                    fTransaction.add(R.id.page_content,feedbackLogsFragment).addToBackStack(null).commit();
                } else {
                    fTransaction.show(feedbackLogsFragment).addToBackStack(null).commit();
                }
            }
        });

        // editText输入时监听
        feedbackEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 改变字数提示
                int num = feedbackEdit.getText().toString().length();
                fontNumText.setText(num+total_num);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
