package com.jmu.onlinecourse.adapter.helper;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.adapter.ipml.ItemTouchHelperListener;

/**
 * @author zjw
 * @date 2021/1/18 8:54
 * @ClassName CollectionTouchHelperCallBack
 */
public class CollectionTouchHelperCallBack extends ItemTouchHelper.Callback {
    private ItemTouchHelperListener itemTouchHelperListener;

    public CollectionTouchHelperCallBack(ItemTouchHelperListener itemTouchHelperListener) {
        this.itemTouchHelperListener = itemTouchHelperListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 允许上下拖动
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // 允许从右向左滑动
        int swipeFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // onItemMove接口里的方法
        itemTouchHelperListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // onItemDelete接口里的方法
        itemTouchHelperListener.onItemDelete(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        // 该方法返回值为true时，表示支持长按ItemView拖动
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        // 该方法返回true时，表示如果用户触摸并且左滑了view，那么可以执行滑动删除操作，就是可以调用onSwiped()方法
        return true;
    }
}
