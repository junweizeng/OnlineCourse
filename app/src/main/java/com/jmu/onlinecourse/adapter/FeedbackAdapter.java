package com.jmu.onlinecourse.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.entity.FeedbackLog;

import java.util.List;

/**
 * @author ywww
 * @date 2021-01-18 22:35
 */
public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyHolder> {
    private List<FeedbackLog> feedbackLogs;
    public FeedbackAdapter(List<FeedbackLog> feedbackLogs){
        this.feedbackLogs = feedbackLogs;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item_layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        // 逆序使得时间近的在上面
        holder.dataText.setText(feedbackLogs.get(getItemCount()-position-1).getDate());
        holder.contentText.setText(feedbackLogs.get(getItemCount()-position-1).getContent());
    }

    @Override
    public int getItemCount() {
        if(feedbackLogs != null){
            return feedbackLogs.size();
        }
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView dataText;
        private TextView contentText;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            dataText = itemView.findViewById(R.id.feedback_date);
            contentText = itemView.findViewById(R.id.feedback_content);
        }
    }
}
