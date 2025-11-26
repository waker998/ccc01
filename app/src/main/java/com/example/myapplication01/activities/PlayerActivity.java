package com.example.myapplication01.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication01.R;  // 添加导入

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player); // 稍后创建这个布局
    }
}