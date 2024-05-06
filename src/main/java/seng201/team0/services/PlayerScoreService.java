package seng201.team0.services;

import seng201.team0.models.PlayerScore;

public class PlayerScoreService {
    private final PlayerScore playerScore;

    public PlayerScoreService() { playerScore = new PlayerScore(); }

    public int getPlayerScore() { return playerScore.getScore(); }

    public void setPlayerScore(int score) { playerScore.setScore(score); }

    public void addPlayerScore(int score) { playerScore.addScore(score); }
}
