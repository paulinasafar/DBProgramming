package com.paulina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelperGame {

    String fileName = "DBGames.db";
    String url = "jdbc:sqlite:C:\\Users\\andritz\\sqlite\\db\\" + fileName;
    DBHelper db = new DBHelper();

    public int insertNewGame(String name, String type, int level) {
        System.out.println("Inserting new Game into the table");
        int rowCount = 0;

        String insert = "INSERT INTO Game (GameName, GameType, MaxLevel)\n ";
        insert += " VALUES (?, ?, ?);";

        try (PreparedStatement stmt = db.connect(url).prepareStatement(insert)) {

            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setInt(3, level);
            rowCount = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("No of new rows in Game: " + rowCount);
        return rowCount;
    }

    public void readAllGames() {
        String query = "SELECT * FROM Game;";
        System.out.println("\nRead all games:");
        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String game = rs.getString("GameName") + " MaxLevel: " + rs.getInt("MaxLevel");
                System.out.println(game);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void filteredByGenre(String type) {
        System.out.println("ReadGamesFilteredByGenre");
        String query = "SELECT * FROM Game WHERE GameType = ? ";
        readStatementFilter(query, type);
    }

    public void filteredByName(String name) {
        System.out.println("ReadGamesFilteredByName");
        String query = "SELECT * FROM Game WHERE GameName LIKE ? ";
        readStatementFilter(query, name);
    }

    public void filteredByLevels(int level) {
        System.out.println("ReadGamesFilteredByLeves");
        String query = "SELECT * FROM Game WHERE MaxLevel < ? ";

        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            stmt.setInt(1, level);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String game = rs.getString("GameName") + " MaxLevel: " + rs.getInt("MaxLevel");
                System.out.println(game);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void gamesOrderedBy(String orderColumn) {
        System.out.println("Games ordered by " + orderColumn);
        String query = "SELECT * FROM Game ORDER BY " + orderColumn;
        readStatementOrderBy(query);
    }

    private void readStatementFilter(String query, String type) {
        System.out.println("Prepared SQL Statement: " + query + "\n");

        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String game = rs.getString("GameName") + " MaxLevel: " + rs.getInt("MaxLevel");
                System.out.println(game);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readStatementOrderBy(String query) {
        System.out.println("Prepared SQL Statement: " + query + "\n");
        try (Statement stmt = db.connect(url).createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String game = rs.getString("GameName") + " MaxLevel: " + rs.getInt("MaxLevel");
                System.out.println(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGame(Game g) {
        int affectedRows = 0;

        String updateSQL = "UPDATE Game SET ";
        updateSQL += " GameName = ?, ";
        updateSQL += " GameType = ?, ";
        updateSQL += " MaxLevel = ? ";
        updateSQL += " WHERE GameId = ? ";
        try (PreparedStatement stmt = db.connect(url).prepareStatement(updateSQL)) {
            stmt.setString(1, g.getGameName());
            stmt.setString(2, g.getGameType());
            stmt.setInt(3, g.getMaxLevel());
            stmt.setInt(4, g.getGameId());

            affectedRows = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (affectedRows == 0) System.out.println("Game was not found.");
            if (affectedRows > 1) System.out.println("More than one Game with same ID.");
            if (affectedRows == 1)
                System.out.println("Data for Game with id " + g.getGameId() + " succesfully updated.");
        }
    }

    public int increaseMaxLevelByXForTypeY(int incValue, String type) {
        int rowCount = 0;
        String updateSQL = "";
        updateSQL = "UPDATE Game SET ";
        updateSQL += " MaxLevel = MaxLevel + ? ";
        updateSQL += " WHERE GameType = ? ";
        try (PreparedStatement stmt = db.connect(url).prepareStatement(updateSQL)) {
            stmt.setInt(1, incValue);
            stmt.setString(2, type);

            rowCount = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Number of rows changed : " + rowCount);
        return rowCount;
    }

    public Game getGameByID(int gameId) {
        Game g = new Game();

        String query = "SELECT * FROM Game WHERE GameId = ? ";
        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            stmt.setInt(1, gameId);
            ResultSet rs = stmt.executeQuery();
                    g.setGameId(gameId);
                    g.setGameName(rs.getString("GameName"));
                    g.setGameType(rs.getString("GameType"));
                    g.setMaxLevel(rs.getInt("MaxLevel"));
//            while (rs.next()) {
//                String game = rs.getString("GameName") + " MaxLevel: " + rs.getInt("MaxLevel");
//                System.out.println(game);
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return g;
    }

   public int getLastInsertRowIDG() {
        int lastId = 0;
        String query = "SELECT MAX(GameId), GameName FROM Game;";

        try (Statement stmt = db.connect(url).createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                lastId = rs.getInt("MAX(GameId)");
                System.out.println("Last ID in the table Game is: " + lastId + " " + rs.getString("GameName"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  lastId;
    }

    public List<Game> getAllGamesInArray() {
        List<Game> games = new ArrayList<Game>();
        String query = "SELECT * FROM Game;";

        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int gameId = rs.getInt("GameId");
                Game g = getGameByID(gameId);
                games.add(g);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Games saved in the list.");
        }
        for (Game g : games) {
            System.out.println(g);
        }
        return games;
    }


}



