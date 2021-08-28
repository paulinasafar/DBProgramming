package com.paulina;

import java.sql.*;

public class DBHelper {

    public void createNewDatabase(String url) {
        connect(url);
    }

    protected Connection connect(String url) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return conn;
    }

    public void createTableGame(String url) {
        int rowCount = 0;
        String sql = "CREATE TABLE IF NOT EXISTS Game (\n"
                + "	GameId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	GameName VARCHAR(255) NOT NULL,\n"
                + "	GameType VARCHAR(255) NOT NULL,\n"
                + "	MaxLevel INTEGER \n"
                + ");";

        try (Statement stmt = connect(url).createStatement()) {
            stmt.execute(sql);

            String insStatement = "INSERT INTO Game (GameName, GameType, MaxLevel)";
            insStatement = insStatement + " VALUES('Dina IV','Strategy', 125),\n"
                    + " ('Dina V','Strategy', 150),\n"
                    + " ('Monkey Island 1', 'Adventure', 200),\n"
                    + " ('Monkey Island 2', 'Adventure', 270),\n"
                    + " ('Resident Evil 2', 'Horror', 180),\n"
                    + " ('Resident Evil 2', 'Horror', 410),\n"
                    + " ('Barbie Castle 1', 'Fairytale', 520),\n"
                    + " ('Barbie Castle 2', 'Fairytale', 370)";

            rowCount = stmt.executeUpdate(insStatement);
            connect(url).close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Number of Games entered: " + rowCount);
    }

    public void createTablePlayer(String url) {
        int rowCount = 0;
        String sql = "CREATE TABLE IF NOT EXISTS Player (\n"
                + "	PlayerId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	PlayerName VARCHAR(255) NOT NULL,\n"
                + "	PlayerNick VARCHAR(255) NOT NULL,\n"
                + "	PlayerAge INTEGER \n"
                + ");";

        try (Statement stmt = connect(url).createStatement()) {
            stmt.execute(sql);

            String insStatement = "INSERT INTO Player (PlayerName, PlayerNick, PlayerAge)";
            insStatement = insStatement + " VALUES('Martina Weiss','MW', 18),\n"
                                        + " ('Laura Schwarz', 'LS', 23),\n"
                                        + " ('John Coltrane', 'JC', 52),\n"
                                        + " ('Rudy Giulianni', 'RG', 68),\n"
                                        + " ('Bruno Mars', 'BM', 35);";

            rowCount = stmt.executeUpdate(insStatement);
            connect(url).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Number of Players entered: " + rowCount);
    }

    public void createTableFavGames(String url) {
        int rowCount = 0;
        String sql = "CREATE TABLE IF NOT EXISTS FavGames (\n"
                + "	FavGamesId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	PlayerId INTEGER NOT NULL,\n"
                + "	GameID INTEGER NOT NULL,\n"
                + "	PlayDate DATE, \n"
                + "	FOREIGN KEY(PlayerId) REFERENCES Player(PlayerId), \n"
                + "	FOREIGN KEY(GameId) REFERENCES Game(GameId) \n"
                + ");";

        try (Statement stmt = connect(url).createStatement()) {
            stmt.execute(sql);

            String insStatement = "INSERT INTO FavGames (PlayerId, GameId, PlayDate)";
            insStatement = insStatement + " VALUES(6, 3, '2021-02-14'),\n"
                    + " (1, 7, '2020-12-8'),\n"
                    + " (2, 4, '2021-01-28'),\n"
                    + " (3, 3, '2021-06-04'),\n"
                    + " (5, 2, '2021-04-20'),\n"
                    + " (6, 1, '2021-07-28'),\n"
                    + " (3, 8, '2021-08-15'),\n"
                    + " (1, 5, '2021-08-15'),\n"
                    + " (2, 8, '2021-08-15'),\n"
                    + " (5, 26, '2021-08-15'),\n"
                    + " (4, 25, '2021-05-08');";

            rowCount = stmt.executeUpdate(insStatement);
            connect(url).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Number of Favorite Games entered: " + rowCount);
    }

    public void createNewTable(String url, String filename, String att1, String att2, String att3) {

        String sql = "CREATE TABLE IF NOT EXISTS " + filename + " (\n"
                + att1 + "	INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + att2 + "	VARCHAR(255) NOT NULL,\n"
                + att3 + "	INTEGER \n"
                + ");";

        try (Statement stmt = connect(url).createStatement()) {

            stmt.execute(sql);
            System.out.println("Created new table " + filename);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTable(String url, String tableName) {

//        PRAGMA foreign_keys = OFF;
//        DROP TABLE addresses;
//        UPDATE people
//        SET address_id = NULL;
//        PRAGMA foreign_keys = ON;

        String sql = "DROP TABLE " + tableName + ";\n";

        try (Statement stmt = connect(url).createStatement()) {
            stmt.executeUpdate(sql);

            System.out.println("Deleted table " + tableName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
