package com.jmu.onlinecourse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.fragment.CoursewareFragment;
import com.jmu.onlinecourse.fragment.ShowCoursewareFragment;
import com.jmu.onlinecourse.fragment.CoursewareFragment;
import com.jmu.onlinecourse.utils.database.DatabaseCollectionUtil;
import com.xuexiang.xui.widget.alpha.XUIAlphaTextView;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 课件名适配器
 * @author czc
 */
public class CoursewareAdapter extends RecyclerView.Adapter<CoursewareAdapter.CoursewareHolder> {
    private FragmentManager fragmentManager;
    /**
     * 课件名称
     */
    List<String> coursewareName = new ArrayList<>();
    /**
     * 课件URL
     */
    List<String> coursewareUrl = new ArrayList<>();
    public CoursewareAdapter(FragmentManager fragmentManager, List<String> coursewareName, List<String> coursewareUrl) {
        this.fragmentManager = fragmentManager;
        this.coursewareName = coursewareName;
        this.coursewareUrl = coursewareUrl;
    }

    @NonNull
    @Override
    public CoursewareHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_courseware, parent, false);
        CoursewareHolder holder = new CoursewareHolder(view);
        return holder;
    }

    /**
     * 将数据绑定到控件
     * @param holder 绑定控件的holder，用户获取绑定数据的控件
     * @param position 绑定控件所在的位置
     */
    @Override
    public void onBindViewHolder(@NonNull CoursewareHolder holder, final int position) {
        holder.tv.setText(coursewareName.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过webview查看对应课件
                fragmentManager.beginTransaction().add(R.id.fragment_courseware, ShowCoursewareFragment.newInstance(coursewareUrl.get(position))).commit();
            }
        });
        holder.collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                DatabaseCollectionUtil util = new DatabaseCollectionUtil(context);
                if(util.isInCollection(position, "ppt")){
                    Toast.makeText(context, "您已收藏过此课件", Toast.LENGTH_SHORT).show();
                }else{
                    util.insert(position, coursewareName.get(position), "ppt");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return coursewareName.size();
    }


    class CoursewareHolder extends RecyclerView.ViewHolder{
        XUIAlphaTextView tv;
        ShadowButton collect;
        public CoursewareHolder(@NonNull View itemView) {
            super(itemView);
            collect = itemView.findViewById(R.id.collect);
            tv = itemView.findViewById(R.id.courseware_name);
        }
    }
}
