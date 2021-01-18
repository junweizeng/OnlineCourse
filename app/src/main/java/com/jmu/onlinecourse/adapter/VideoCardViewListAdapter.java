package com.jmu.onlinecourse.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.entity.VideoInfo;
import com.jmu.onlinecourse.utils.database.DatabaseCollectionUtil;
import com.jmu.onlinecourse.utils.database.DatabaseVideoUtil;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.button.shinebutton.ShineButton;

/**
 * @author zjw
 * @date 2021/1/12 14:35
 * @ClassName VideoCardViewListAdapter
 */
public class VideoCardViewListAdapter extends BaseRecyclerAdapter<VideoInfo> {

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_video_card_view_list_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, VideoInfo item) {
        DatabaseVideoUtil db = new DatabaseVideoUtil(holder.getContext());
        DatabaseCollectionUtil db2 = new DatabaseCollectionUtil(holder.getContext());
        long ID = -1;
        int likes = 0;
        int collection = 0;
        int playVolume = 0;
        if(item != null) {
            ID = item.getID();
            boolean isInCollection = db2.isInCollection(ID, "video");
            holder.text(R.id.tv_video_name, item.getVideoName());
            holder.text(R.id.tv_summary, item.getSummary());
            likes = db.fetchLikes(ID);
            holder.text(R.id.tv_likes, likes == 0 ? "点赞量" : String.valueOf(likes));
            collection = db.fetchCollection(ID);
            holder.text(R.id.tv_collection, isInCollection ? "已收藏" : "未收藏");
            playVolume = db.fetchPlayVolume(ID);
            holder.text(R.id.tv_play_volume, "播放量 " + playVolume);
            holder.image(R.id.riv_image, item.getImageUrl());

            ShineButton sbLikes = (ShineButton) holder.getView(R.id.sb_likes);
            ShineButton sbCollection = (ShineButton) holder.getView(R.id.sb_collection);
            sbCollection.setChecked(isInCollection);

            sbLikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("AdapterTest", item.getVideoName());
                    db.increaseLikes(item.getID());
                    int likes = db.fetchLikes(item.getID());
                    holder.text(R.id.tv_likes, likes == 0 ? "点赞量" : String.valueOf(likes));
                }
            });

            sbCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("AdapterTest", item.getVideoName());
                    if(!sbCollection.isChecked()) {
                        db.decreaseCollection(item.getID());
                        db2.delete(item.getID(), "video");
                    } else {
                        db.increaseCollection(item.getID());
                        db2.insert(item.getID(), item.getVideoName(), "video");
                    }
                    boolean isInCollection = db2.isInCollection(item.getID(), "video");
                    holder.text(R.id.tv_collection, isInCollection ?
                            "已收藏" : "未收藏");
                }
            });
        }
    }

}
