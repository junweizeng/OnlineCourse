package com.jmu.onlinecourse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.entity.TextInfo;

import java.util.List;

public class TextAdapter extends BaseAdapter {
    private Context context;
    private int resourceId;
    private List<TextInfo>data;

    public TextAdapter(Context context, int resourceId, List<TextInfo> data){
        super();
        this.context=context;
        this.resourceId=resourceId;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextInfo textInfo=(TextInfo) getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null)
        {
            view= LayoutInflater.from(context).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView=view.findViewById(R.id.textImage);
            viewHolder.textName=view.findViewById(R.id.textName);
            viewHolder.textId=view.findViewById(R.id.textId);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.imageView.setImageResource(textInfo.getImageView());
        viewHolder.textName.setText(textInfo.getTextName());
        /*
        往textView里面传入int类型会出错
         */
        viewHolder.textId.setText(textInfo.getTextId()+"");
        return view;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textName;
        TextView textId;
    }
}
