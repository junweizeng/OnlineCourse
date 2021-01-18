package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.activity.MainActivity;

/**
 * @author ywww
 * @date 2021-01-11 17:00
 */
public class BottomMainFragment extends Fragment {
    private View view;
    /**
     * 底部菜单
     */
    private ImageView planButton;
    private ImageView courseButton;
    private ImageView videoButton;
    private ImageView testingButton;
    private ImageView readingButton;
    private LinearLayout planLayout;
    private LinearLayout courseLayout;
    private LinearLayout videoLayout;
    private LinearLayout testingLayout;
    private LinearLayout readingLayout;
    private MainActivity mainActivity;
    private ViewPager viewPager;
    private IndexFragment indexFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom_main,null);
        mainActivity = (MainActivity) getActivity();
        mainActivity.getParent();
        indexFragment = (IndexFragment) getParentFragment();
        Log.d("TAG",indexFragment.toString());
        viewPager = indexFragment.getMyViewPager();
        init();
        return view;
    }

    private void init(){
        // 设置监听器
        planButton = view.findViewById(R.id.plan_button);
        planButton.setOnClickListener(new BottomItemListener());
        courseButton = view.findViewById(R.id.course_button);
        courseButton.setOnClickListener(new BottomItemListener());
        videoButton = view.findViewById(R.id.video_button);
        videoButton.setOnClickListener(new BottomItemListener());
        testingButton = view.findViewById(R.id.testing_button);
        testingButton.setOnClickListener(new BottomItemListener());
        readingButton = view.findViewById(R.id.reading_button);
        readingButton.setOnClickListener(new BottomItemListener());
        planLayout = view.findViewById(R.id.plan_layout);
        courseLayout = view.findViewById(R.id.course_layout);
        videoLayout = view.findViewById(R.id.video_layout);
        testingLayout = view.findViewById(R.id.testing_layout);
        readingLayout = view.findViewById(R.id.reading_layout);
    }


    /**
     * 重置底部导航栏的颜色
     */
    public void replayButtons(){
        planLayout.setBackgroundColor(getResources().getColor(R.color.xui_config_color_background_phone,null));
        courseLayout.setBackgroundColor(getResources().getColor(R.color.xui_config_color_background_phone,null));
        videoLayout.setBackgroundColor(getResources().getColor(R.color.xui_config_color_background_phone,null));
        testingLayout.setBackgroundColor(getResources().getColor(R.color.xui_config_color_background_phone,null));
        readingLayout.setBackgroundColor(getResources().getColor(R.color.xui_config_color_background_phone,null));
    }

    /**
     * 设置底部导航栏的颜色
     * @param position 位置
     */
    public void setOutstandingButton(int position){
        switch (position){
            case 0:
                planLayout.setBackgroundColor(getResources().getColor(R.color.littleGray,null));
                break;
            case 1:
                courseLayout.setBackgroundColor(getResources().getColor(R.color.littleGray,null));
                break;
            case 2:
                videoLayout.setBackgroundColor(getResources().getColor(R.color.littleGray,null));
                break;
            case 3:
                testingLayout.setBackgroundColor(getResources().getColor(R.color.littleGray,null));
                break;
            case 4:
                readingLayout.setBackgroundColor(getResources().getColor(R.color.littleGray,null));
                break;
            default:
                break;
        }
    }


    /**
     * 导航栏点击事件
     */
    public class BottomItemListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.plan_button:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.course_button:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.video_button:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.testing_button:
                    viewPager.setCurrentItem(3);
                    break;
                case R.id.reading_button:
                    viewPager.setCurrentItem(4);
                    break;
                default:
                    break;
            }
        }
    }
}

