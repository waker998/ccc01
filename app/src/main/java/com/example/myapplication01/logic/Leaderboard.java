package com.example.myapplication01.logic;

import com.example.myapplication01.models.Player;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {
    private static Leaderboard instance;
    private ArrayList<Player> leaderboard;
    private static final int MAX_LEADERBOARD_SIZE = 5;

    private Leaderboard() {
        leaderboard = new ArrayList<>();
        // 添加一些示例数据用于测试
        leaderboard.add(new Player("Judy", android.R.color.holo_blue_light, 16));
        leaderboard.add(new Player("Judy", android.R.color.holo_green_light, 7));
        leaderboard.add(new Player("Mahroor", android.R.color.holo_orange_light, 5));
        leaderboard.add(new Player("Mahroor", android.R.color.holo_red_light, 3));
        leaderboard.add(new Player("Mahroor", android.R.color.darker_gray, 1));
    }

    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public void updateLeaderboard(Player newPlayer) {
        leaderboard.add(newPlayer);
        // 按分数排序（降序）
        Collections.sort(leaderboard, (p1, p2) -> p2.getPlayerScore() - p1.getPlayerScore());

        // 如果超过最大大小，移除最低分
        if (leaderboard.size() > MAX_LEADERBOARD_SIZE) {
            leaderboard = new ArrayList<>(leaderboard.subList(0, MAX_LEADERBOARD_SIZE));
        }
    }

    public ArrayList<Player> getLeaderboard() {
        return new ArrayList<>(leaderboard);
    }

    public void clearLeaderboard() {
        leaderboard.clear();
    }
}