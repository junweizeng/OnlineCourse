package com.jmu.onlinecourse;

import android.app.Application;

import com.xuexiang.xui.XUI;

/**
 * @author zjw
 * @date 2021-1-11 9:11
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化UI框架
        XUI.init(this);
        // 开启UI框架测试日志
        XUI.debug(true);
    }
}
