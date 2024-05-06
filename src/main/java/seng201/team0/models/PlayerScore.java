package seng201.team0.models;

public class PlayerScore {
    private int score;

    public PlayerScore() { score = 0; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public void addScore(int score) { this.score += score; }
}