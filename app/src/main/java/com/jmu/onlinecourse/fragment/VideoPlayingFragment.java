package com.jmu.onlinecourse.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.entity.VideoInfo;
import com.jmu.onlinecourse.utils.database.DatabaseCollectionUtil;
import com.jmu.onlinecourse.utils.database.DatabaseVideoUtil;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.shinebutton.ShineButton;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author zjw
 */
public class VideoPlayingFragment extends Fragment implements View.OnClickListener {
    private static final String FROM_COLLECTION = "collection";

    private View view;
    private Activity mContext;

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private VideoInfo currentVideo;
    private ImageButton ibFullScreen;

    private TitleBar tbTitle;
    private TextView tvPlayVolume;
    private ShineButton sbLikes;
    private TextView tvLikes;
    private ShineButton sbCollection;
    private TextView tvCollection;
    private TextView tvVideoName;
    private TextView tvSummary;

    private DatabaseVideoUtil db;
    private DatabaseCollectionUtil db2;

    private String from;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("testing", "onCreateView...");
        db = new DatabaseVideoUtil(getActivity());
        db2 = new DatabaseCollectionUtil(getActivity());
        mContext = getActivity();

        // 获取传递过来的Bundle信息
        Bundle bundle = getArguments();
        if(bundle != null) {
            long id = bundle.getLong("currentVideo");
            currentVideo = db.fetchVideoInfoById(id);
            from = bundle.getString("from");
        }

        if(view == null) {
            view = inflater.inflate(R.layout.fragment_video_playing, container, false);
            initViews();
            initListens();
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        playerView = view.findViewById(R.id.player_view);
        ibFullScreen = view.findViewById(R.id.exo_fullscreen_button);

        tbTitle = view.findViewById(R.id.tb_title);
        tvPlayVolume = view.findViewById(R.id.tv_play_volume);
        sbLikes = view.findViewById(R.id.sb_likes);
        tvLikes = view.findViewById(R.id.tv_likes);
        sbCollection = view.findViewById(R.id.sb_collection);
        tvCollection = view.findViewById(R.id.tv_collection);
        tvVideoName = view.findViewById(R.id.tv_video_name);
        tvSummary = view.findViewById(R.id.tv_summary);


        tvPlayVolume.setText("播放量 " + db.fetchPlayVolume(currentVideo.getID()));
        tvLikes.setText(String.valueOf(db.fetchLikes(currentVideo.getID())));
        boolean isInCollection = db2.isInCollection(currentVideo.getID(), "video");
        Log.i("helloChecked", String.valueOf(isInCollection));
        sbCollection.setChecked(isInCollection);
        tvCollection.setText(isInCollection ? "已收藏" : "未收藏");
        if(FROM_COLLECTION.equals(from)) {
            sbCollection.setVisibility(View.GONE);
            tvCollection.setVisibility(View.GONE);
        }
        tvVideoName.setText(currentVideo.getVideoName());
        tvSummary.setText(currentVideo.getSummary());
        tbTitle.setTitle(currentVideo.getVideoName());

        player = new SimpleExoPlayer.Builder(Objects.requireNonNull(getActivity())).build();
        playerView.setPlayer(player);
        // 准备视频地址；创建一个媒体项；将媒体项加入到播放器中；准备播放器；并播放
        String videoUri = currentVideo.getDetailUrl();
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        player.addMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    private void initListens() {
        tbTitle.setOnClickListener(this);
        sbLikes.setOnClickListener(this);
        sbCollection.setOnClickListener(this);
        ibFullScreen.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i("testing", "onSaveInstanceState...");
        super.onSaveInstanceState(outState);
        outState.putLong("position", player.getCurrentPosition());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("testing", "onViewStateRestored...");
        if (savedInstanceState != null) {
            long position = savedInstanceState.getLong("position");
            player.seekTo(position);
            Configuration cfg = getResources().getConfiguration();

            if(cfg.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // 当为横屏状态时，将标题栏和通知栏设置为不可见，并让视频充满整个屏幕，改变放大图标为缩小
                tbTitle.setVisibility(View.GONE);
                mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
                ibFullScreen.setImageResource(R.drawable.exo_controls_fullscreen_exit);
            } else if (cfg.orientation == Configuration.ORIENTATION_PORTRAIT){
                // 当为竖屏时，则相反
                tbTitle.setVisibility(View.VISIBLE);
                mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
                ibFullScreen.setImageResource(R.drawable.exo_controls_fullscreen_enter);
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Configuration cfg = getResources().getConfiguration();
        if ( cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ){
            mContext.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        }else {
            mContext.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tb_title:
                // 获取FragmentManager，开启一个事务，隐藏当前Fragment，向容器中添加Fragment，提交事务
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Fragment fragment = manager.findFragmentByTag("videoPlaying");
                transaction.remove(Objects.requireNonNull(fragment));
                fragment = manager.findFragmentByTag(from);
                transaction.show(Objects.requireNonNull(fragment));
                transaction.commit();
                break;
            case R.id.sb_likes:
                db.increaseLikes(currentVideo.getID());
                int likes = db.fetchLikes(currentVideo.getID());
                tvLikes.setText(likes == 0 ? "点赞量" : String.valueOf(likes));
                break;
            case R.id.sb_collection:
                if(!sbCollection.isChecked()) {
                    db.decreaseCollection(currentVideo.getID());
                    db2.delete(currentVideo.getID(), "video");
                } else {
                    db.increaseCollection(currentVideo.getID());
                    db2.insert(currentVideo.getID(), currentVideo.getVideoName(), "video");
                }
                boolean isInCollection = db2.isInCollection(currentVideo.getID(), "video");
                tvCollection.setText(isInCollection ? "已收藏" : "未收藏");
                break;
            case R.id.exo_fullscreen_button:
                Configuration cfg = getResources().getConfiguration();
                if (cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ){
                    // 如果是横向的，则改成竖向
                    mContext.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
                }else {
                    // 如果是竖向的，则改成横向
                    mContext.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
                }
                break;
            default:
                break;
        }
    }
}