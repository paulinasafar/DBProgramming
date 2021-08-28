package com.paulina;

public class Main {

    public static void main(String[] args) {

        String fileName = "DBGames.db";
        String url = "jdbc:sqlite:C:\\Users\\andritz\\sqlite\\db\\" + fileName;

        DBHelper dbHelper = new DBHelper();
        //dbHelper.createNewDatabase(url);
        //dbHelper.createTableGame(url);
        //dbHelper.createTablePlayer(url);
        //dbHelper.createNewTable(url, "Competitions", "CompetitionsId", "CompetitionName", "WinnerPoints");

        DBHelperGame dbGame = new DBHelperGame();
        //dbGame.filteredByGenre("Adventure");
        //dbGame.readAllGames();
        //dbGame.filteredByName("Mon%");
        //dbGame.gamesOrderedBy("MaxLevel");
        //dbGame.insertNewGame("Monopoly 2", "Strategy", 90);
        //dbGame.increaseMaxLevelByXForTypeY(30, "Strategy");
        //dbGame.getGameByID(7);
        dbGame.getLastInsertRowID();

        Game g1 = new Game();
        g1.setGameName("Monopoly 1");
        g1.setGameType("Strategy");
        g1.setMaxLevel(130);
        g1.setGameId(25);
        //dbGame.updateGame(g1);

        Player p1 = new Player();
        DBHelperPlayer dbPlayer = new DBHelperPlayer();
        //dbPlayer.insertNewPlayer("Lizzi Borden", "Lizzi", 32);
        //dbPlayer.filteredByAge(35);
        p1.setPlayerName("Donald Trump");
        p1.setPlayerNick("DonnyBoy");
        p1.setPlayerAge(80);
        p1.setPlayerId(4);
        //dbPlayer.updatePlayer(p1);
        //dbPlayer.filteredByNick("MW");
        //dbPlayer.playersOrderedBy("PlayerNick");
        //dbPlayer.filteredByName("J%");

    }

    /* Beispiel-Aufgaben
        LovedGames -- Create Table, addLovedGames, updateLovedGames, deleteLovedGames
        Car --> 4 Wheels, Engine, Seats
        Player --- List of LovedGames and a List of PlayedGames
        Aggregation / Komposition, Aggregate / Composite
        ArrayList<LovedGames> lg = p1.getLovedGames();
        SELECT * FROM LovedGames WHERE PlayerID = ?
        ArrayList<GameHistory> gh = p1.getGameHistory();
        Player pHighestNumberPlayedGames = dbHelper.getPlayerHighestNumberPlayedGames();
     */

/*CREATE TABLE LovedGames (
    LovedGamesId INTEGER PRIMARY KEY,
    PlayerId     INTEGER REFERENCES Player (PlayerId),
    GameId       INTEGER REFERENCES Game (GameId),
    PlayDate     DATE
);
Aggregation in SQL
SELECT p.Firstname, l.* FROM Player p
JOIN LovedGames l
ON p.PlayerId=l.PlayerId
 */


}
