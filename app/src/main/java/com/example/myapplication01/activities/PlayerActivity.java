package com.example.myapplication01.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication01.R;
import com.example.myapplication01.logic.Leaderboard;
import com.example.myapplication01.models.Player;

public class PlayerActivity extends AppCompatActivity {
    private TextView tvPlayerScore;
    private EditText etPlayerName;
    private RadioGroup rgAvatar;
    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initializeViews();

        // 获取从GameActivity传递过来的分数
        finalScore = getIntent().getIntExtra("SCORE", 0);
        tvPlayerScore.setText("Score: " + finalScore);
    }

    private void initializeViews() {
        tvPlayerScore = findViewById(R.id.tv_playerscore);
        etPlayerName = findViewById(R.id.et_playername);
        rgAvatar = findViewById(R.id.rg_avatar);
    }

    public void onClickSubmit(View view) {
        String playerName = etPlayerName.getText().toString().trim();
        if (playerName.isEmpty()) {
            playerName = "Anonymous";
        }

        int selectedColorId = rgAvatar.getCheckedRadioButtonId();
        int avatarResId = getAvatarResourceId(selectedColorId);  // 改为获取资源ID

        // 创建玩家对象并添加到排行榜
        Player newPlayer = new Player(playerName, avatarResId, finalScore);
        Leaderboard.getInstance().updateLeaderboard(newPlayer);

        // 跳转回主界面
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish(); // 结束当前Activity
    }

    private int getAvatarResourceId(int radioButtonId) {
        // 根据选择的单选按钮返回对应的图片资源ID
        if (radioButtonId == R.id.rb_grey) {
            return R.drawable.img_grey_mole;
        } else if (radioButtonId == R.id.rb_blue) {
            return R.drawable.img_blue_mole;
        } else if (radioButtonId == R.id.rb_orange) {
            return R.drawable.img_orange_mole;
        } else if (radioButtonId == R.id.rb_green) {
            return R.drawable.img_green_mole;
        } else if (radioButtonId == R.id.rb_purple) {
            return R.drawable.img_purple_mole;
        } else if (radioButtonId == R.id.rb_pink) {
            return R.drawable.img_pink_mole;
        }
        return R.drawable.img_grey_mole; // 默认灰色地鼠
    }
}