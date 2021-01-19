package com.jmu.onlinecourse.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.activity.MainActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.Objects;

/**
 * @author zjw
 */
public class VersionFragment extends Fragment {
    private View view;
    private TitleBar tbReturn;
    private TextView tvVersion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_version, container, false);
        }
        initViews();
        return view;
    }

    private void initViews() {
        tbReturn = view.findViewById(R.id.tb_return);
        tvVersion = view.findViewById(R.id.tv_version);

        tbReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取FragmentManager，开启一个事务，隐藏当前Fragment，向容器中添加Fragment，提交事务
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Fragment fragment = manager.findFragmentByTag("version");
                transaction.remove(Objects.requireNonNull(fragment));
                fragment = manager.findFragmentByTag("index");
                transaction.show(Objects.requireNonNull(fragment)).addToBackStack(null);
                transaction.commit();
            }
        });

        tvVersion.setText("当前版本：v" + getVersionName());
    }

    /**
     * 获取当前程序的版本名
     * @return 当前程序的版本名
     */
    private String getVersionName() {
        PackageManager packageManager = getActivity().getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo( getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }
}