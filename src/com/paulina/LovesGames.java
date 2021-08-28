package com.paulina;

import java.util.Date;

public class LovesGames {

    private int LovedGamesId;
    private int PlayerId;
    private int GameId;
    private Date PlayDate;

    public int getLovedGamesId() {
        return LovedGamesId;
    }

    public void setLovedGamesId(int lovedGamesId) {
        LovedGamesId = lovedGamesId;
    }

    public int getPlayerId() {
        Player p = new Player();
        return p.getPlayerId();
    }

    public void setPlayerId(int playerId) {
        Player p = new Player();
        p.setPlayerId(playerId);
    }

    public int getGameId() {
        Game g = new Game();
        return g.getGameId();
    }

    public void setGameId(int gameId) {
        Game g = new Game();
        g.setGameId(gameId);
    }

    public Date getPlayDate() {
        return PlayDate;
    }

    public void setPlayDate(Date playDate) {
        Date d = new Date();
        PlayDate = d;
    }
}


