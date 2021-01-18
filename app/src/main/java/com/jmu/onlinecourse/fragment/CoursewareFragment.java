package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.adapter.CoursewareAdapter;
import com.jmu.onlinecourse.utils.DataProviderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursewareFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author czc
 */
public class CoursewareFragment extends Fragment {
    private static final int MAX_NUMBER = 15;
    private RecyclerView courseware;
    private List<String> coursewareName;
    private List<String> coursewareUrl;
    public CoursewareFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShowCoursewareFragment.
     */
    public static CoursewareFragment newInstance() {
        CoursewareFragment fragment = new CoursewareFragment();
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
        View view =  inflater.inflate(R.layout.fragment_courseware, container, false);
        initView(view);
        return view;
    }
    private void initView(View view){
        courseware = view.findViewById(R.id.list_courseware);
        coursewareName = new ArrayList<>();
        coursewareUrl = new ArrayList<>();

        initData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        courseware.setLayoutManager(linearLayoutManager);
        courseware.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        courseware.setAdapter(new CoursewareAdapter(getActivity().getSupportFragmentManager(), coursewareName, coursewareUrl));


    }
    private void initData(){
        coursewareName.add("第一章 毛泽东思想及其历史地位");
        coursewareName.add("第二章 新民主主义革命理论");
        coursewareName.add("第三章 社会主义改造理论");
        coursewareName.add("第四章 社会主义建设道路初步探索的理论成果");
        coursewareName.add("第五章 邓小平理论");
        coursewareName.add("第六章 三个代表”重要思想");
        coursewareName.add("第七章 科学发展观");
        coursewareName.add("第八章 习近平新时代中国特色社会主义思想及其历史地位");
        coursewareName.add("第九章 中国特色社会主义总任务 ");
        coursewareName.add("第十章 全面深化改革");
        coursewareName.add("第十一章 “五位一体”总体布局");
        coursewareName.add("第十二章 全面推进国防和军队现代化");
        coursewareName.add("第十三章“一国两制”与祖国统一");
        coursewareName.add("第十四章 中国特色大国外交");
        coursewareName.add("第十五章 全面从严治党");

        //1
        coursewareUrl.add("https://www.doc88.com/p-0083843848981.html");
        //2
        coursewareUrl.add("https://www.doc88.com/p-9758137017527.html");
        //3
        coursewareUrl.add("https://www.doc88.com/p-1324889511896.html");
        //4
        coursewareUrl.add("https://www.doc88.com/p-6009778666935.html");
        //5
        coursewareUrl.add("https://www.doc88.com/p-90099062365401.html");
        //6
        coursewareUrl.add("https://www.doc88.com/p-5435037021253.html");
        //7
        coursewareUrl.add("https://www.doc88.com/p-3989101965174.html");
        //8
        coursewareUrl.add("https://www.doc88.com/p-5146469854885.html");
        //9
        coursewareUrl.add("https://www.doc88.com/p-29016981004391.html");
        //10
        coursewareUrl.add("https://www.doc88.com/p-1753963384763.html");
        //11
        coursewareUrl.add("https://www.doc88.com/p-2176485359750.html");
        //12
        coursewareUrl.add("https://www.doc88.com/p-5199102744712.html");
        //13
        coursewareUrl.add("https://www.doc88.com/p-1167881489882.html");
        //14
        coursewareUrl.add("https://www.doc88.com/p-67747315485977.html");
        //15
        coursewareUrl.add("https://www.docin.com/p-2387409234.html");
    }
}