package com.paulina;

import java.sql.Date;

public class FavGames {

    private int FavGamesId;
    private int PlayerId;
    private int GameId;
    private Date PlayDate;

    Player p = new Player();
    Game g = new Game();

    public int getFavGamesId() {
        return FavGamesId;
    }

    public void setFavGamesId(int favGamesId) {
        FavGamesId = favGamesId;
    }

    public int getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(int playerId) {
        PlayerId = playerId;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public Date getPlayDate() {
        return PlayDate;
    }

    public void setPlayDate(Date playDate) {
        PlayDate = playDate;
    }

    @Override
    public String toString() {
        return FavGamesId + PlayerId + p.getPlayerName() + GameId + g.getGameName() + PlayDate;
    }
}


