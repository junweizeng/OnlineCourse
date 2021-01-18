package com.jmu.onlinecourse.adapter.ipml;

/**
 * @author zjw
 * @date 2021/1/18 9:04
 * @ClassName ItemTouchHelperListener
 */
public interface ItemTouchHelperListener {
    /**
     * 移动item
     * @param fromPosition 原来的位置
     * @param toPosition 移动到的位置
     */
    public void onItemMove(int fromPosition,int toPosition);

    /**
     * 删除item
     * @param position 待删除的item位置
     */
    public void onItemDelete(int position);
}
