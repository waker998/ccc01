package com.example.myapplication01.logic;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication01.activities.PlayerActivity;
import com.example.myapplication01.models.Mole;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private static final long MOLE_DISPLAY_TIME = 1000; // 地鼠显示时间
    private static final long GAME_DURATION = 30000;    // 游戏总时长30秒

    private int currentScore = 0;
    private int timeRemaining = 30;

    private Handler moleHandler = new Handler(Looper.getMainLooper()); // 修复：使用主线程Looper
    private Runnable moleRunnable;
    private CountDownTimer gameTimer;
    private Random random = new Random();

    private ArrayList<Mole> moles;
    private TextView scoreTextView, timerTextView;
    private boolean isGameRunning = false;
    private Context context;
    private int currentMoleIndex = -1;

    public GameLogic(Context context, ArrayList<ImageView> moleViews,
                     TextView scoreText, TextView timerText) {
        this.context = context;
        this.scoreTextView = scoreText;
        this.timerTextView = timerText;

        // 初始化地鼠
        moles = new ArrayList<>();
        for (int i = 0; i < moleViews.size(); i++) {
            moles.add(new Mole(i, moleViews.get(i)));
        }
    }

    public void startGame() {
        currentScore = 0;
        timeRemaining = 30;
        isGameRunning = true;

        updateScoreText();
        startTimer();
        startMoleLoop();
    }

    private void startTimer() {
        gameTimer = new CountDownTimer(GAME_DURATION, 1000) {
            public void onTick(long millisUntilFinished) {
                timeRemaining = (int) (millisUntilFinished / 1000);
                timerTextView.setText("Time: " + timeRemaining);
            }

            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void startMoleLoop() {
        moleRunnable = new Runnable() {
            @Override
            public void run() {
                if (isGameRunning) {
                    showRandomMole();
                    moleHandler.postDelayed(this, MOLE_DISPLAY_TIME);
                }
            }
        };
        moleHandler.post(moleRunnable);
    }

    private void showRandomMole() {
        // 隐藏当前地鼠
        if (currentMoleIndex != -1) {
            moles.get(currentMoleIndex).setVisible(false);
        }

        // 显示新的随机地鼠
        currentMoleIndex = random.nextInt(moles.size());
        moles.get(currentMoleIndex).setVisible(true);
    }

    public void hideMole(int index) {
        if (isGameRunning && index == currentMoleIndex && moles.get(index).isVisible()) {
            currentScore++;
            updateScoreText();
            moles.get(index).setVisible(false);
            currentMoleIndex = -1;
        }
    }

    private void updateScoreText() {
        scoreTextView.setText("Score: " + currentScore);
    }

    private void endGame() {
        isGameRunning = false;
        if (moleHandler != null && moleRunnable != null) {
            moleHandler.removeCallbacks(moleRunnable);
        }
        if (gameTimer != null) {
            gameTimer.cancel();
        }

        // 隐藏当前地鼠
        if (currentMoleIndex != -1) {
            moles.get(currentMoleIndex).setVisible(false);
        }

        // 跳转到玩家信息输入界面
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra("SCORE", currentScore);
        context.startActivity(intent);
    }

    public void stopGame() {
        isGameRunning = false;
        if (moleHandler != null && moleRunnable != null) {
            moleHandler.removeCallbacks(moleRunnable);
        }
        if (gameTimer != null) {
            gameTimer.cancel();
        }
    }
}