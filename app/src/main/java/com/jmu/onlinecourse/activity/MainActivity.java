package com.jmu.onlinecourse.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jmu.onlinecourse.R;
import com.jmu.onlinecourse.fragment.CollectionFragment;

/**
 * @author zjw
 */
public class MainActivity extends AppCompatActivity {
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(i == 0) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new CollectionFragment(), "cf").commit();
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new TeachingVideoFragment(), "tvf").commit();
            i = 1;
        }


    }
}