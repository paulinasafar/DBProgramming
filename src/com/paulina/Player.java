package com.paulina;

public class Player {

    private int playerId;
    private String playerName;
    private String playerNick;
    private int playerAge;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    @Override
    public String toString() {
        return playerId + " " + playerName + " " + playerNick + " " + playerAge;
    }
}
