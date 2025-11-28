package com.example.myapplication01.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication01.R;
import com.example.myapplication01.logic.Leaderboard;
import com.example.myapplication01.models.Player;
import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        displayLeaderboard();
    }

    private void displayLeaderboard() {
        LinearLayout container = findViewById(R.id.leaderboard_container);
        container.removeAllViews(); // 清空现有视图

        ArrayList<Player> leaderboard = Leaderboard.getInstance().getLeaderboard();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < leaderboard.size(); i++) {
            Player player = leaderboard.get(i);

            // 加载排行榜条目布局
            View itemView = inflater.inflate(R.layout.item_leaderboard, container, false);

            // 设置地鼠头像图片
            ImageView avatarView = itemView.findViewById(R.id.iv_leaderboard_avatar);
            avatarView.setImageResource(player.getPlayerAvatar());  // 设置图片资源
            avatarView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);  // 设置图片缩放方式

            // 设置玩家姓名
            TextView nameView = itemView.findViewById(R.id.tv_leaderboard_name);
            nameView.setText(player.getPlayerName());

            // 设置分数
            TextView scoreView = itemView.findViewById(R.id.tv_leaderboard_score);
            scoreView.setText(player.getPlayerScore() + " points");

            // 添加到容器
            container.addView(itemView);
        }

        // 如果排行榜为空，显示提示信息
        if (leaderboard.isEmpty()) {
            TextView emptyText = new TextView(this);
            emptyText.setText("No scores yet! Play the game to get on the leaderboard.");
            emptyText.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            emptyText.setPadding(0, 50, 0, 0);
            container.addView(emptyText);
        }
    }
}