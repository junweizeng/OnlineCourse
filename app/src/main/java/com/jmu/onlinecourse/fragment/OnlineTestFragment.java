package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.entity.Problem;
import com.jmu.onlinecourse.utils.database.ProblemUtil;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnlineTestFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author czc
 */
public class OnlineTestFragment extends Fragment {

    private static final int MAX = 9;
    private static final int REMIND_TIME_MINUTE = 2;
    private static final int REMIND_TIME_SECOND = 0;

    private TextView countdown;
    private RadioGroup radioGroup;
    private TextView title;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC;
    private RadioButton optionD;

    private ShadowButton last;
    private ShadowButton next;
    private ShadowButton finishTest;

    private TitleBar titleBar;

    /**
     * 保存题目数据
     */
    private ArrayList<Problem> problems;
    /**
     * 保存用户每题的答案
     */
    private ArrayList<String> answers = new ArrayList<>(10);
    private int pageIndex = 0;
    /**
     * 设置计时器，每个1000毫秒调用onTick方法，总计时时长为300000（5分钟）
     */
    private CountDownTimer timer = new CountDownTimer(300000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int minute = (int) ((millisUntilFinished/1000) / 60);
            int second = (int) ((millisUntilFinished/1000) % 60);
            countdown.setText(String.format(getResources().getString(R.string.countdown), minute, second));
            if(minute == REMIND_TIME_MINUTE && second == REMIND_TIME_SECOND){
                Toast.makeText(getActivity(), "剩余时间两分钟!", Toast.LENGTH_LONG).show();
            }
        }
        //计时结束后调用
        @Override
        public void onFinish() {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            OnlineTestFragment fragment = (OnlineTestFragment) fragmentManager.findFragmentByTag("otf");
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.page_content, OnlineTestResultFragment.newInstance(answers, problems), "otrf").commit();
        }
    };

    public OnlineTestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OnlineTestFragment.
     */
    public static OnlineTestFragment newInstance() {
        OnlineTestFragment fragment = new OnlineTestFragment();
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
        View view = inflater.inflate(R.layout.fragment_online_test, container, false);
        initView(view);
        return view;
    }
    private void initView(View view)
    {
        //对answers列表进行预填充，防止之后发生越界
        for(int i = 0; i <= MAX; i++){
            answers.add(null);
        }
        //从数据库获取题目数据
        getDataFromDataBase();

        title = view.findViewById(R.id.title);
        radioGroup = view.findViewById(R.id.radio_group);
        optionA = view.findViewById(R.id.radio_buttonA);
        optionB = view.findViewById(R.id.radio_buttonB);
        optionC = view.findViewById(R.id.radio_buttonC);
        optionD = view.findViewById(R.id.radio_buttonD);
        last = view.findViewById(R.id.btn_last);
        next = view.findViewById(R.id.btn_next);
        countdown = view.findViewById(R.id.countdown);
        finishTest = view.findViewById(R.id.finish_test);
        titleBar = view.findViewById(R.id.onlineTest_titlebar);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                OnlineTestFragment fragment = (OnlineTestFragment) fragmentManager.findFragmentByTag("otf");
                IndexFragment indexFragment = (IndexFragment) fragmentManager.findFragmentByTag("index");
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                getActivity().getSupportFragmentManager().beginTransaction().show(indexFragment).commit();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_buttonA:
                        answers.set(pageIndex, "A");
                        break;
                    case R.id.radio_buttonB:
                        answers.set(pageIndex, "B");
                        break;
                    case R.id.radio_buttonC:
                        answers.set(pageIndex, "C");
                        break;
                    case R.id.radio_buttonD:
                        answers.set(pageIndex, "D");
                        break;
                    default:
                        break;
                }
            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageIndex == 0){
                    Toast.makeText(getActivity(), "已经是第一题了!", Toast.LENGTH_SHORT).show();
                }else{
                    pageIndex -= 1;
                    setTitleAndOption();
                    getAnswerAndDisableOthers();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pageIndex == MAX){
                    Toast.makeText(getActivity(), "已经是最后一题了!", Toast.LENGTH_SHORT).show();
                }else {
                    pageIndex += 1;
                    setTitleAndOption();
                    getAnswerAndDisableOthers();
                }

            }
        });
        finishTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消倒计时否则会出现IllegalStateException
                timer.cancel();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                OnlineTestFragment fragment = (OnlineTestFragment) fragmentManager.findFragmentByTag("otf");
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.page_content, OnlineTestResultFragment.newInstance(answers, problems), "otrf").commit();
            }
        });
        //设置每题的题目和选项
        setTitleAndOption();
        //开始计时
        timer.start();
    }

    /**
     * 从数据库中获取每题的题目和选项
     */
    private void getDataFromDataBase(){
        //通过随机数，随机获取一套测试
        Random random = new Random();
        int examination = random.nextInt(10) + 1;
        Log.i("OnlineTestFragment","第"+examination+"套测试");
        problems = ProblemUtil.findAllByExamination(getContext(), examination);
    }

    /**
     * 设置每题的题目和选项
     */
    private void setTitleAndOption(){
        //num为题号
        int num = problems.get(pageIndex).getId() % 10;
        if(num == 0) {
            num = 10;
        }
        title.setText(String.format(getResources().getString(R.string.onlineTestTitle), num, problems.get(pageIndex).getTitle()));
        optionA.setText(String.format(getResources().getString(R.string.onlineTestOption), problems.get(pageIndex).getOptionA()));
        optionB.setText(String.format(getResources().getString(R.string.onlineTestOption), problems.get(pageIndex).getOptionB()));
        optionC.setText(String.format(getResources().getString(R.string.onlineTestOption), problems.get(pageIndex).getOptionC()));
        optionD.setText(String.format(getResources().getString(R.string.onlineTestOption), problems.get(pageIndex).getOptionD()));
    }

    /**
     * 通过用户的选项选中对应的单选按钮
     */
    private void getAnswerAndDisableOthers() {
        String answer = answers.get(pageIndex);
        if(answer == null){
            optionA.setChecked(false);
            optionB.setChecked(false);
            optionC.setChecked(false);
            optionD.setChecked(false);
        }else {
            switch (answer) {
                case "A":
                    System.out.println("A");
                    optionA.setChecked(true);
                    break;
                case "B":
                    System.out.println("B");
                    optionB.setChecked(true);
                    break;
                case "C":
                    System.out.println("C");
                    optionC.setChecked(true);
                    break;
                case "D":
                    System.out.println("D");
                    optionD.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        System.out.println("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        System.out.println("onPause");
        super.onPause();
    }
}