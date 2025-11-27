package com.example.myapplication01.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication01.R;
import com.example.myapplication01.logic.GameLogic;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private GameLogic gameLogic;
    private TextView timerTextView, scoreTextView;
    private ArrayList<ImageView> moleViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initializeViews();
        gameLogic = new GameLogic(this, moleViews, scoreTextView, timerTextView);
        gameLogic.startGame();
    }

    private void initializeViews() {
        timerTextView = findViewById(R.id.tv_timer_text);
        scoreTextView = findViewById(R.id.tv_score_text);

        moleViews = new ArrayList<>();
        // 添加所有地鼠视图
        moleViews.add(findViewById(R.id.mole_1));
        moleViews.add(findViewById(R.id.mole_2));
        moleViews.add(findViewById(R.id.mole_3));
        moleViews.add(findViewById(R.id.mole_4));
        moleViews.add(findViewById(R.id.mole_5));
        moleViews.add(findViewById(R.id.mole_6));
        moleViews.add(findViewById(R.id.mole_7));
        moleViews.add(findViewById(R.id.mole_8));
        moleViews.add(findViewById(R.id.mole_9));

        // 为每个地鼠设置点击监听器
        for (int i = 0; i < moleViews.size(); i++) {
            final int index = i;
            moleViews.get(i).setOnClickListener(v -> gameLogic.hideMole(index));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gameLogic != null) {
            gameLogic.stopGame();
        }
    }
}