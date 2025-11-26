package com.example.myapplication01.models;

public class Player {
    private String playerName;
    private int playerAvatar;
    private int playerScore;

    public Player(String name, int avatar, int score) {
        this.playerName = name;
        this.playerAvatar = avatar;
        this.playerScore = score;
    }

    public String getPlayerName() { return playerName; }
    public int getPlayerAvatar() { return playerAvatar; }
    public int getPlayerScore() { return playerScore; }
}
