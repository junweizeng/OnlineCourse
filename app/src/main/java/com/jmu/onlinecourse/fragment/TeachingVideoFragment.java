package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.adapter.VideoCardViewListAdapter;
import com.jmu.onlinecourse.entity.VideoInfo;
import com.jmu.onlinecourse.utils.database.DatabaseVideoUtil;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;
import java.util.Objects;

/**
 * @author zjw
 */
public class TeachingVideoFragment extends Fragment {
    private View view;
    private SmartRefreshLayout smartRefreshLayout;
    private VideoCardViewListAdapter mAdapter;
    private int page = 0;
    private DatabaseVideoUtil db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DatabaseVideoUtil(getActivity());
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_teaching_video, container, false);
            initViews();
            initListeners();
        }
        return view;
    }

    void initViews() {
        smartRefreshLayout = view.findViewById(R.id.smart_refresh_layout);
        // 设置顶部刷新样式
        smartRefreshLayout.setRefreshHeader(new BezierCircleHeader(getContext()));
        // 设置底部刷新样式，SpinnerStyle.scale为随着拉动幅度增大放大缩小样式
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(Objects.requireNonNull(getContext())).setSpinnerStyle(SpinnerStyle.Scale));


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        // 设置适配器，以按需提供子视图
        recyclerView.setAdapter(mAdapter = new VideoCardViewListAdapter());
        // 设置布局管理器，以适应线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        // 设置item的动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    void initListeners() {
        mAdapter.setOnItemClickListener((itemView, item, position) -> {
            Toast.makeText(getContext(), "点击了" + item.getVideoName(), Toast.LENGTH_SHORT).show();
            // 增加播放量.............
            db.increasePlayVolume(item.getID());

            VideoPlayingFragment videoPlayingFragment = new VideoPlayingFragment();
            Bundle bundle = new Bundle();
            bundle.putLong("currentVideo", item.getID());
            bundle.putString("from", "tvf");
            videoPlayingFragment.setArguments(bundle);

            // 获取FragmentManager，开启一个事务，隐藏当前Fragment，向容器中添加Fragment，提交事务
            FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            Fragment fragment = manager.findFragmentByTag("tvf");
            transaction.hide(Objects.requireNonNull(fragment));
            transaction.add(R.id.fragment_container, videoPlayingFragment, "vpf");
            transaction.commit();
        });

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            /**
             * 加载更多数据项
             * @param refreshLayout 待加载的布局
             */
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(() -> {
                    page++;
                    List<VideoInfo> videoInfoList = db.fetchFiveVideoInfo(page);
                    Log.i("hello", String.valueOf(videoInfoList.size()));
                    if(videoInfoList.size() > 0) {
                        mAdapter.loadMore(videoInfoList);
                        refreshLayout.finishLoadMore();
                    } else {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }, 1000);
            }

            /**
             * 重新加载布局
             * @param refreshLayout 待加载的布局
             */
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(() -> {
                    page = 0;
                    mAdapter.refresh(db.fetchFiveVideoInfo(page));
                    refreshLayout.finishRefresh();
                }, 1000);
            }
        });
        // 刚进入该界面，进行刷新获取数据项
        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}