package com.example.myapplication01.logic;

import com.example.myapplication01.models.Player;
import com.example.myapplication01.R;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {
    private static Leaderboard instance;
    private ArrayList<Player> leaderboard;
    private static final int MAX_LEADERBOARD_SIZE = 5;

    private Leaderboard() {
        leaderboard = new ArrayList<>();
        // 可以添加一些示例数据用于测试（可选）
        leaderboard.add(new Player("Judy", R.drawable.img_blue_mole, 16));
        leaderboard.add(new Player("Judy", R.drawable.img_green_mole, 7));
        leaderboard.add(new Player("Mahroor", R.drawable.img_orange_mole, 5));
        leaderboard.add(new Player("Mahroor", R.drawable.img_pink_mole, 3));
        leaderboard.add(new Player("Mahroor", R.drawable.img_grey_mole, 1));
    }

    public static synchronized Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public void updateLeaderboard(Player newPlayer) {
        leaderboard.add(newPlayer);
        // 按分数排序（降序）
        Collections.sort(leaderboard, (p1, p2) -> Integer.compare(p2.getPlayerScore(), p1.getPlayerScore()));

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