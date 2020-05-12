package com.example.learning;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class relativeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将布局xml文件引入到activity当中
        setContentView(R.layout.layout_relative);

    }

}
