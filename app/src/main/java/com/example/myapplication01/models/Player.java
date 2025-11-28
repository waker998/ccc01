package com.example.myapplication01.models;

public class Player {
    private final String playerName;
    private final int playerAvatarResId;  // 改为资源ID
    private final int playerScore;

    public Player(String name, int avatarResId, int score) {
        this.playerName = name;
        this.playerAvatarResId = avatarResId;
        this.playerScore = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerAvatar() {  // 返回资源ID
        return playerAvatarResId;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}