package com.jmu.onlinecourse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.fragment.ShowCoursewareFragment;
import com.jmu.onlinecourse.utils.database.DatabaseCollectionUtil;
import com.xuexiang.xui.widget.alpha.XUIAlphaTextView;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author czc
 */
public class ErrorAdapter extends RecyclerView.Adapter<ErrorAdapter.ErrorHolder> {
    List<String> errorTitles = new ArrayList<>();
    List<String> corretcOption = new ArrayList<>();
    public ErrorAdapter(List<String> errorTitles, List<String> corretcOption) {
        this.errorTitles = errorTitles;
        this.corretcOption = corretcOption;
    }

    @NonNull
    @Override
    public ErrorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error, parent, false);
        ErrorHolder holder = new ErrorHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ErrorHolder holder, int position) {
        holder.errorTitle.setText(errorTitles.get(position));
        holder.option.setText(corretcOption.get(position));
    }


    @Override
    public int getItemCount() {
        return errorTitles.size();
    }


    class ErrorHolder extends RecyclerView.ViewHolder{
        TextView errorTitle;
        TextView option;
        public ErrorHolder(@NonNull View itemView) {
            super(itemView);
            option  = itemView.findViewById(R.id.tv_error_option);
            errorTitle = itemView.findViewById(R.id.tv_error_title);
        }
    }
}
