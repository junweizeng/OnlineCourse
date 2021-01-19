package com.jmu.onlinecourse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.adapter.TextAdapter;
import com.jmu.onlinecourse.entity.TextInfo;
import com.jmu.onlinecourse.utils.TextProviderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextFragment extends Fragment {
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         final TextFragment textFragment=new TextFragment();
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        listView = view.findViewById(R.id.text_listview);
        List<TextInfo>data=new ArrayList<TextInfo>();

        try {
            // 数据提供初始化
            data=new TextProviderUtil().provide();
        } catch (IOException e) {
            e.printStackTrace();
        }


        TextAdapter adapter=new TextAdapter(getActivity(), R.layout.fragment_text_item,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "666", Toast.LENGTH_SHORT).show();
                List<TextInfo>data=new ArrayList<TextInfo>();
                try {
                    data=new TextProviderUtil().provide();//数据提供初始化
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bundle bundle=new Bundle();
                bundle.putInt("para",position);
                bundle.putString("name", data.get(position).getTextName());
                bundle.putString("content", data.get(position).getTextContent());
                bundle.putString("from", "index");

                ReadFragment readFragment = new ReadFragment();
                readFragment.setArguments(bundle);
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Fragment fragment = manager.findFragmentByTag("index");
                transaction.hide(Objects.requireNonNull(fragment));
                transaction.add( R.id.page_content, readFragment, "rf");
                transaction.commit();
            }
        });
        return view;
    }
}

