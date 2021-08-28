package com.paulina;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        String fileName = "DBGames.db";
        String url = "jdbc:sqlite:C:\\Users\\andritz\\sqlite\\db\\" + fileName;

        DBHelper dbHelper = new DBHelper();
        dbHelper.createNewDatabase(url);
        dbHelper.createTableGame(url);
        dbHelper.createTablePlayer(url);
        dbHelper.createNewTable(url, "Competitions", "CompetitionsId", "CompetitionName", "WinnerPoints");
        dbHelper.createTableFavGames(url);
        dbHelper.deleteTable(url, "Competitions");

        DBHelperGame dbGame = new DBHelperGame();
        dbGame.filteredByGenre("Adventure");
        dbGame.readAllGames();
        dbGame.filteredByName("Mon%");
        dbGame.gamesOrderedBy("MaxLevel");
        dbGame.insertNewGame("Monopoly 2", "Strategy", 90);
        dbGame.increaseMaxLevelByXForTypeY(30, "Strategy");
        dbGame.getGameByID(7);
        dbGame.getLastInsertRowIDG();
        dbGame.getAllGamesInArray();

        Game g1 = new Game();
        g1.setGameName("Monopoly 1");
        g1.setGameType("Strategy");
        g1.setMaxLevel(130);
        g1.setGameId(25);
        dbGame.updateGame(g1);

        Player p1 = new Player();
        DBHelperPlayer dbPlayer = new DBHelperPlayer();
        dbPlayer.insertNewPlayer("Lizzi Borden", "Lizzi", 32);
        dbPlayer.filteredByAge(35);
        p1.setPlayerName("Donald Trump");
        p1.setPlayerNick("DonnyBoy");
        p1.setPlayerAge(80);
        p1.setPlayerId(4);
        dbPlayer.updatePlayer(p1);
        dbPlayer.filteredByNick("MW");
        dbPlayer.playersOrderedBy("PlayerNick");
        dbPlayer.filteredByName("J%");



        FavGames fg = new FavGames();
        DBHelperFav dbFav = new DBHelperFav();
        dbFav.insertNewFavGame(6,25);
        dbFav.filteredByGame(3);
        dbFav.filteredByPlayerID(6);
        dbFav.gamesOrderedBy("PlayDate");
        dbFav.getLastInsertRowIDFG();
        dbFav.readAllFavGames();
        fg.setPlayerId(2);
        fg.setGameId(6);
        String s = "2021-05-05";
        Date date = Date.valueOf(s);
        fg.setPlayDate(date);
        fg.setFavGamesId(15);
        dbFav.updateFavGames(fg);

        FavGames fg1 = new FavGames();
        Date d = Date.valueOf(LocalDate.now());
        fg1.setPlayerId(2);
        fg1.setGameId(6);
        fg1.setPlayDate(d);
        fg1.setFavGamesId(15);
        dbFav.updateFavGames(fg1);

    }

    /* Beispiel-Aufgaben
        Player --- List of LovedGames and a List of PlayedGames
        Aggregation / Komposition, Aggregate / Composite
        ArrayList<LovedGames> lg = p1.getLovedGames();
        SELECT * FROM LovedGames WHERE PlayerID = ?
        ArrayList<GameHistory> gh = p1.getGameHistory();
        Player pHighestNumberPlayedGames = dbHelper.getPlayerHighestNumberPlayedGames();
     */

/*
Aggregation in SQL
SELECT p.Firstname, l.* FROM Player p
JOIN LovedGames l
ON p.PlayerId=l.PlayerId
 */


}
