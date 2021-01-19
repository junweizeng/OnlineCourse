package com.jmu.onlinecourse.utils;

import android.util.Log;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.entity.TextInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static com.xuexiang.xui.utils.ResUtils.getResources;

public class TextProviderUtil {
    public List<TextInfo> provide() throws IOException{
        List<TextInfo>data=new ArrayList<TextInfo>();
        //获取文本text内容
        String string1=readText(R.raw.text1);
        String string4=readText(R.raw.text4);
        String string2=readText(R.raw.text2);
        String string3=readText(R.raw.text3);
        data.add(new TextInfo(1,"《共产党宣言》", R.drawable.text1,string1));
        data.add(new TextInfo(2,"《邓小平文选 部分文章》", R.drawable.text2,string2));
        data.add(new TextInfo(3,"《在延安座谈会上的讲话》", R.drawable.text3,string3));
        data.add(new TextInfo(4,"《论共产党员的修养》", R.drawable.text4,string4));
        //TextInfo textInfo=new TextInfo("","", R.drawable.timg);
        return data;
    }
    public String readText(int resource){
        InputStream input=getResources().openRawResource(resource);
        Reader reader=new InputStreamReader(input);
        StringBuffer stringBuffer=new StringBuffer();
        char b[]=new char[1024];
        int len=-1;
        try {
            while ((len = reader.read(b))!= -1){
                stringBuffer.append(b);
            }
        }catch (IOException e){
            Log.e("ReadingFile","IOException");
        }
        String string=stringBuffer.toString();
        return string;
    }
}
