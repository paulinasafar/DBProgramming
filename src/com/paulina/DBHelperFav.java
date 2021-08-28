package com.paulina;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class DBHelperFav {

    String fileName = "DBGames.db";
    String url = "jdbc:sqlite:C:\\Users\\andritz\\sqlite\\db\\" + fileName;
    DBHelper db = new DBHelper();

    public int insertNewFavGame(int playerID, int gameID) {
        System.out.println("Inserting new Favourite Game into the table");
        int rowCount = 0;

        String insert = "INSERT INTO FavGames (PlayerId, GameId, PlayDate)\n ";
        insert += " VALUES (?, ?, ?);";

        try (PreparedStatement stmt = db.connect(url).prepareStatement(insert)) {

            stmt.setInt(1, playerID);
            stmt.setInt(2, gameID);
            //stmt.setDate(3, date);
            rowCount = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("No of new rows in FavGames: " + rowCount);
        return rowCount;
    }

    public void readAllFavGames() {
        String query = "SELECT * FROM FavGames;";
        System.out.println("\nRead all favourite games:");
        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String favGame = rs.getString("PlayerId") + " : " + rs.getString("GameId");
                System.out.println(favGame);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void filteredByPlayerID(int player) {
        System.out.println("ReadGamesFilteredByPlayerId");
        String query = "SELECT * FROM FavGames WHERE PlayerId = ? ";
        readStatementFilter(query, player);
    }

    public void filteredByGame(int game) {
        System.out.println("ReadPlayersFilteredByPlayedGames");
        String query = "SELECT * FROM FavGames WHERE GameId < ? ";
        readStatementFilter(query, game);
    }

    public void gamesOrderedBy(String orderColumn) {
        System.out.println("FavGames ordered by " + orderColumn);
        String query = "SELECT * FROM FavGames ORDER BY " + orderColumn;
        readStatementOrderBy(query);
    }

    private void readStatementFilter(String query, int placeHolder) {
        System.out.println("Prepared SQL Statement: " + query + "\n");

        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            stmt.setInt(1, placeHolder);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String favGame = rs.getString("PlayerId") + " : " + rs.getString("GameId");
                System.out.println(favGame);
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
                String favGame = rs.getString("PlayerId") + " : " + rs.getString("GameId");
                System.out.println(favGame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFavGames(FavGames fg) {
        int affectedRows = 0;

        String updateSQL = "UPDATE FavGames SET ";
        updateSQL += " PlayerId = ?, ";
        updateSQL += " GameId = ?, ";
        updateSQL += " PlayDate = ? ";
        updateSQL += " WHERE FavGamesId = ? ";
        try (PreparedStatement stmt = db.connect(url).prepareStatement(updateSQL)) {
            stmt.setInt(1, fg.getPlayerId());
            stmt.setInt(2, fg.getGameId());
            stmt.setDate(3, fg.getPlayDate());
            stmt.setInt(4, fg.getFavGamesId());

            affectedRows = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (affectedRows == 0) System.out.println("Favourite Game was not found.");
            if (affectedRows > 1) System.out.println("More than one Favourite Game with same ID.");
            if (affectedRows == 1) System.out.println("Data for Favourite Game with id " + fg.getFavGamesId() + " succesfully updated.");
        }
    }

    public int getLastInsertRowIDFG() {
        int lastId = 0;
        String query = "SELECT MAX(FavGamesId) FROM FavGames;";

        try (Statement stmt = db.connect(url).createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                lastId = rs.getInt("MAX(FavGamesId)");
                System.out.println("Last ID in the table Favourite Games is: " + lastId);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  lastId;
    }

}
