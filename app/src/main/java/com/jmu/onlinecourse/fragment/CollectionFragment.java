package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.adapter.CollectionCardViewListAdapter;
import com.jmu.onlinecourse.adapter.helper.CollectionTouchHelperCallBack;
import com.jmu.onlinecourse.entity.CollectionInfo;
import com.jmu.onlinecourse.utils.database.DatabaseCollectionUtil;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.Objects;


/**
 * @author zjw
 */
public class CollectionFragment extends Fragment {
    private View view;
    private TitleBar tbReturn;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private CollectionCardViewListAdapter mAdapter;
    private DatabaseCollectionUtil db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DatabaseCollectionUtil(getActivity());
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_collection, container, false);
        }

        initViews();
        initListens();
        return view;
    }

    private void initViews() {
        tbReturn = view.findViewById(R.id.tb_return);
        smartRefreshLayout = view.findViewById(R.id.smart_refresh_layout);
        // 设置顶部刷新样式
        smartRefreshLayout.setRefreshHeader(new BezierCircleHeader(getContext()));
        smartRefreshLayout.setEnableLoadMore(false);

        recyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new CollectionCardViewListAdapter(getActivity(), db.fetchAllCollectionInfo());
        // 设置适配器，以按需提供子视图
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new CollectionTouchHelperCallBack(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        // 设置布局管理器，以适应线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        // 设置item的动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.refresh(db.fetchAllCollectionInfo());
    }

    private void initListens() {
        tbReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Fragment fragment = manager.findFragmentByTag("collection");
                transaction.remove(Objects.requireNonNull(fragment));
                transaction.show(Objects.requireNonNull(manager.findFragmentByTag("index")));
                transaction.commit();
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(() -> {
                    mAdapter.refresh(db.fetchAllCollectionInfo());
                    refreshLayout.finishRefresh();
                }, 1000);
            }
        });

        mAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<CollectionInfo>() {
            @Override
            public void onItemClick(View itemView, CollectionInfo item, int position) {
                if(item.getType().equals("video")) {
                    Log.i("helloOnItemClick", "hello");
                    VideoPlayingFragment videoPlayingFragment = new VideoPlayingFragment();
                    Bundle bundle = new Bundle();
                    bundle.putLong("currentVideo", item.getID());
                    bundle.putString("from", "collection");
                    videoPlayingFragment.setArguments(bundle);

                    // 获取FragmentManager，开启一个事务，隐藏当前Fragment，向容器中添加Fragment，提交事务
                    FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();

                    Fragment fragment = manager.findFragmentByTag("collection");
                    transaction.hide(Objects.requireNonNull(fragment));
                    transaction.add(R.id.page_content, videoPlayingFragment, "videoPlaying");
                    transaction.commit();
                }
            }
        });
    }

}