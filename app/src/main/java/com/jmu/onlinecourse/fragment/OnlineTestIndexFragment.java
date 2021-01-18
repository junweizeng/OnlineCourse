package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jmu.onlinecourse.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnlineTestIndexFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author czc
 */
public class OnlineTestIndexFragment extends Fragment {

    private Button begin_test;
    private Button show_score;
    private TitleBar titleBar;

    public OnlineTestIndexFragment() {
        // Required empty public constructor
    }


    public static OnlineTestIndexFragment newInstance() {
        OnlineTestIndexFragment fragment = new OnlineTestIndexFragment();
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
        View view = inflater.inflate(R.layout.fragment_online_test_index, container, false);

        initView(view);
        return view;
    }
    private void initView(View view)
    {
        titleBar = view.findViewById(R.id.onlineTest_index_titlebar);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击返回", Toast.LENGTH_SHORT).show();
            }
        });
        //开始测试按钮
        begin_test = view.findViewById(R.id.begin_test);
        //展示成绩按钮
        show_score = view.findViewById(R.id.show_score);
        begin_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IndexFragment indexFragment = (IndexFragment) getActivity().getSupportFragmentManager().findFragmentByTag("index");
                getActivity().getSupportFragmentManager().beginTransaction().hide(indexFragment).commit();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.page_content, OnlineTestFragment.newInstance(), "otf").commit();
            }
        });
        show_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IndexFragment indexFragment = (IndexFragment) getActivity().getSupportFragmentManager().findFragmentByTag("index");
                getActivity().getSupportFragmentManager().beginTransaction().hide(indexFragment).commit();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.page_content, ShowScoreFragment.newInstance(), "ssf").commit();
            }
        });
    }
}