package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.entity.TextInfo;
import com.jmu.onlinecourse.utils.TextProviderUtil;
import com.jmu.onlinecourse.utils.database.DatabaseCollectionUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFragment extends Fragment {
    TextView textView;
    Button button1;
    Button button2;
    String from;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        final ReadFragment readFragment=new ReadFragment();
        textView=view.findViewById(R.id.fg_read);
        button1=view.findViewById(R.id.button_1);
        button2=view.findViewById(R.id.button_2);
        /**
         * 获取文本内容
         */
        int pama = getArguments().getInt("para");
        String name = getArguments().getString("name");
        from = getArguments().getString("from");
        Log.i("helloPama", String.valueOf(pama) + "    " + name);

        List<TextInfo> data = new ArrayList<TextInfo>();

        try {
            //数据提供初始化
            data = new TextProviderUtil().provide();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content=data.get(pama).getTextContent();
        int num = content.length() / 15;

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "收藏", Toast.LENGTH_SHORT).show();
                DatabaseCollectionUtil databaseCollectionUtil=new DatabaseCollectionUtil(getActivity());
                if(!databaseCollectionUtil.isInCollection(pama,"reading")){
                    databaseCollectionUtil.insert(pama,name,"reading");
                    Toast.makeText(getActivity(), "收藏成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "您收藏过该文章！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextProviderUtil dataProviderUtil=new TextProviderUtil();
        String s;
        if(pama==0) {
           s=dataProviderUtil.readText(R.raw.text1);
        } else if(pama==1){
           s=dataProviderUtil.readText(R.raw.text2);
        } else if (pama==2) {
           s=dataProviderUtil.readText(R.raw.text3);
        }else {
           s=dataProviderUtil.readText(R.raw.text4);
        }
        textView.setText(s);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                Fragment fragment = fragmentManager.findFragmentByTag("rf");
                Fragment fragment1 = fragmentManager.findFragmentByTag(from);
                fragmentManager.beginTransaction().remove(fragment).show(fragment1).commit();;
            }
        });

        return view;
    }

}
