package com.jmu.onlinecourse.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceControl;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.fragment.CollectionFragment;
import com.jmu.onlinecourse.fragment.FeedbackFragment;
import com.jmu.onlinecourse.fragment.IndexFragment;
import com.jmu.onlinecourse.fragment.VersionFragment;
import com.jmu.onlinecourse.utils.helper.DatabaseHelper;
import com.thekhaeng.slidingmenu.lib.SlidingMenu;

import java.util.Objects;

/**
 * @author zjw
 */
public class MainActivity extends AppCompatActivity {
    private static int isFirst = 0;

    private IndexFragment indexFragment;
    private FragmentManager fManager;
    private FragmentTransaction fTransaction;
    private FeedbackFragment feedbackFragment;
    /**
     * 抽屉菜单
     */
    private NavigationView navigationView;
    private SlidingMenu menu;
    private CollectionFragment collectionFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 数据库载入
        DatabaseHelper dbHelper = new DatabaseHelper(this,"online_course",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.close();
        fManager= getSupportFragmentManager();

        if(isFirst == 0) {
            indexFragment = new IndexFragment();
            fTransaction = fManager.beginTransaction();
            fTransaction.add(R.id.page_content, indexFragment, "index");
            fTransaction.commit();
            isFirst++;
        } else {
            indexFragment = (IndexFragment) fManager.findFragmentByTag("index");
        }
        init();
    }

    private void init(){
        // 抽屉菜单操作
        menu = new SlidingMenu(this);
        // 设置为左边划出
        menu.setMode(SlidingMenu.LEFT);
        // 全屏滑动
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 菜单偏移
        menu.setBehindOffsetRes(R.dimen.drawer_menu_offset);
        // 渐变比率
        menu.setFadeDegree(0.5f);
        // 将菜单依附在MainActivity上
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置布局
        menu.setMenu(R.layout.sliding_menu_layout);
        navigationView = menu.findViewById(R.id.left_menu);
        // 显示icon,否则icon是灰色的
        navigationView.setItemIconTintList(null);
        initListener();
    }

    private void initListener(){
        // 设置抽屉菜单里的监听器
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    // 我的收藏
                    case R.id.my_collection:
                        menu.toggle();
                        collectionFragment = new CollectionFragment();
                        // 获取FragmentManager，开启一个事务，隐藏当前Fragment，向容器中添加Fragment，提交事务
                        Fragment fragment = fManager.findFragmentByTag("index");
                        fTransaction = fManager.beginTransaction();
                        fTransaction.hide(Objects.requireNonNull(fragment));
                        fTransaction.add(R.id.page_content,collectionFragment , "collection").addToBackStack(null);
                        fTransaction.commit();
                        Toast.makeText(MainActivity.this,"我的收藏",Toast.LENGTH_SHORT).show();
                        break;
                    // 浏览记录
                    case R.id.view_log:

                        break;
                    // 版本信息
                    case R.id.my_version:
                        menu.toggle();
                        Fragment fragment1 = fManager.findFragmentByTag("index");
                        fTransaction = fManager.beginTransaction();
                        fTransaction.hide(Objects.requireNonNull(fragment1));
                        fTransaction.add(R.id.page_content, new VersionFragment(), "version").addToBackStack(null);
                        fTransaction.commit();
                        break;
                    // 意见反馈
                    case R.id.suggestion_write:
                        fTransaction = fManager.beginTransaction();
                        fTransaction.hide(indexFragment);
                        if(feedbackFragment == null || fManager.findFragmentByTag("feedback") == null) {
                            feedbackFragment = new FeedbackFragment();
                            fTransaction.add(R.id.page_content,feedbackFragment,"feedback").addToBackStack(null).commit();
                        } else {
                            fTransaction.show(feedbackFragment).addToBackStack(null).commit();
                        }
                        menu.toggle();
                        lockDrawerMenu();
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void openDrawerMenu(){
        menu.showMenu();
    }

    public void lockDrawerMenu(){
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    }

    public void unlockDrawerMenu(){
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    }

    public FragmentManager getFManager(){
        return fManager;
    }

    public CollectionFragment getCollectionFragment(){
        return collectionFragment;
    }

}
