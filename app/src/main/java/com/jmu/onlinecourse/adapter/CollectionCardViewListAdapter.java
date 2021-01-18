package com.jmu.onlinecourse.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.adapter.ipml.ItemTouchHelperListener;
import com.jmu.onlinecourse.entity.CollectionInfo;
import com.jmu.onlinecourse.utils.database.DatabaseCollectionUtil;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import java.util.List;

/**
 * @author zjw
 * @date 2021/1/17 20:37
 * @ClassName CollectionCardViewListAdapter
 */
public class CollectionCardViewListAdapter  extends BaseRecyclerAdapter<CollectionInfo>
        implements ItemTouchHelperListener {

    private Context context;
    private List<CollectionInfo> collectionInfoList;

    private static final String KEY_VIDEO = "video";
    private static final String KEY_PPT = "ppt";

    public CollectionCardViewListAdapter(Context context, List<CollectionInfo> collectionInfoList) {
        this.context = context;
        this.collectionInfoList = collectionInfoList;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_collection_card_view_list_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, CollectionInfo item) {
        if(position < collectionInfoList.size()) {
            CollectionInfo collectionInfo = collectionInfoList.get(position);
            holder.text(R.id.tv_name, collectionInfo.getName());
            holder.text(R.id.iv_type, collectionInfo.getType());
            CardView cardView = (CardView) holder.getView(R.id.cv_background);
            ImageView ivType = (ImageView) holder.getView(R.id.iv_type);
            if(KEY_VIDEO.equals(collectionInfo.getType())) {
                ivType.setImageResource(R.drawable.adapter_collection_card_view_list_item_video);
                cardView.setCardBackgroundColor(holder.getContext().getResources().getColor(R.color.xui_btn_green_normal_color, null));
            } else if(KEY_PPT.equals(collectionInfo.getType())) {
                ivType.setImageResource(R.drawable.adapter_collection_card_view_list_item_ppt);
                cardView.setCardBackgroundColor(holder.getContext().getResources().getColor(R.color.xui_config_color_gray_9, null));
            } else {
                ivType.setImageResource(R.drawable.adapter_collection_card_view_list_item_reading);
                cardView.setCardBackgroundColor(holder.getContext().getResources().getColor(R.color.xui_config_color_light_yellow, null));
            }
        }
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDelete(int position) {
        Log.i("helloPosition", String.valueOf(position));
        DatabaseCollectionUtil db = new DatabaseCollectionUtil(context);
        db.delete(collectionInfoList.get(position).getID(), KEY_VIDEO);
        collectionInfoList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     * 当adapter更新时，getItemCount的数量也会发生改变
     * @return 最新item数量
     */
    @Override
    public int getItemCount() {
        return collectionInfoList.size();
    }
}
