package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.activity.MainActivity;
import com.jmu.onlinecourse.adapter.MyPagerAdapter;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.ArrayList;

/**
 * @author ywww
 * @date 2021-01-18 13:33
 */
public class IndexFragment extends Fragment {
    private View view = null;
    private MainActivity mainActivity;
    private BottomMainFragment bottomMainFragment;
    private TextView pageTitle;
    /**
     * fragment管理
     */
    private FragmentManager BottomManager;
    private FragmentTransaction fTransaction;

    /**
     * 打开抽屉菜单按钮
     */
    private RadiusImageView showDrawerButton;
    /**
     * 实现页面滑动切换
     */
    private ViewPager myViewPager;
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private FragmentManager myViewManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_index,null);
            mainActivity = (MainActivity) getActivity();
            init();
        }
        return view;
    }
    private void init(){
        pageTitle = view.findViewById(R.id.index_title);
        // 抽屉菜单打开按钮
        showDrawerButton = view.findViewById(R.id.show_drawer_button);
        // viewPager操作
        myViewPager = view.findViewById(R.id.index_view_pager);
        fragments.add(new TeachPlansFragment());
        fragments.add(CoursewareFragment.newInstance());
        fragments.add(new TeachingVideoFragment());
        fragments.add(OnlineTestIndexFragment.newInstance());
        fragments.add(new TextFragment());

        /**
         * 待添加其它页面
         */


        myViewManager = getChildFragmentManager();
        // 底部导航栏操作
        bottomMainFragment = new BottomMainFragment();
        BottomManager = getChildFragmentManager();
        initView();
        initListener();
    }
    private void initView(){
        fTransaction = BottomManager.beginTransaction();
        fTransaction.add(R.id.bottom_main_layout, bottomMainFragment);
        fTransaction.commit();
        myViewPager.setAdapter(new MyPagerAdapter(myViewManager, fragments));
        // 设置当前页面
        myViewPager.setCurrentItem(0);
        // 设置最多缓存页面
        myViewPager.setOffscreenPageLimit(3);
    }
    private void initListener(){
        // 打开抽屉菜单的点击事件
        showDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.openDrawerMenu();
            }
        });

        // 设置viewpager页面切换监听
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                bottomMainFragment.replayButtons();
                switch (position){
                    // 课程计划
                    case 0:
                        // 解锁抽屉桌面滑动
                        mainActivity.unlockDrawerMenu();
                        bottomMainFragment.setOutstandingButton(0);
                        pageTitle.setText(R.string.teach_plans);
                        break;
                    // 专题课件
                    case 1:
                        // 取消抽屉界面滑动
                        mainActivity.lockDrawerMenu();
                        bottomMainFragment.setOutstandingButton(1);
                        pageTitle.setText(R.string.course_ware);
                        break;
                    // 教学视频
                    case 2:
                        // 取消抽屉界面滑动
                        mainActivity.lockDrawerMenu();
                        bottomMainFragment.setOutstandingButton(2);
                        pageTitle.setText(R.string.teach_videos);
                        break;
                    // 在线测试
                    case 3:
                        // 取消抽屉界面滑动
                        mainActivity.lockDrawerMenu();
                        bottomMainFragment.setOutstandingButton(3);
                        pageTitle.setText(R.string.online_testing);
                        break;
                    // 经典阅读
                    case 4:
                        // 取消抽屉界面滑动
                        mainActivity.lockDrawerMenu();
                        bottomMainFragment.setOutstandingButton(4);
                        pageTitle.setText(R.string.classic_reading);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public ViewPager getMyViewPager(){
        return myViewPager;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("TAG","this");
        if(hidden == false) {
            Log.d("TAG","this");
            TeachingVideoFragment teachingVideoFragment1 = (TeachingVideoFragment) fragments.get(2);
            VideoPlayingFragment videoPlayingFragment1 = (VideoPlayingFragment) teachingVideoFragment1.getVideoPlayingFragment();
            if (videoPlayingFragment1 != null) {
                Log.d("TAG","this");
                videoPlayingFragment1.onDestroy();
            }
            if(mainActivity.getCollectionFragment() != null){
                VideoPlayingFragment videoPlayingFragment2 = mainActivity.getCollectionFragment().getVideoPlayingFragment();
                if(videoPlayingFragment2 != null){
                    videoPlayingFragment2.onDestroy();
                }
            }
        }
    }
}
