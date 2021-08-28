package com.paulina;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelperPlayer {

    String fileName = "DBGames.db";
    String url = "jdbc:sqlite:C:\\Users\\andritz\\sqlite\\db\\" + fileName;
    DBHelper db = new DBHelper();

    public int insertNewPlayer(String name, String nick, int age) {
        System.out.println("Inserting new Player into the table");
        int rowCount = 0;

        String insert = "INSERT INTO Player (PlayerName, PlayerNick, PlayerAge)\n ";
        insert += " VALUES (?, ?, ?);";

        try (PreparedStatement stmt = db.connect(url).prepareStatement(insert)) {

            stmt.setString(1, name);
            stmt.setString(2, nick);
            stmt.setInt(3, age);
            rowCount = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("No of new rows in Player: " + rowCount);
        return rowCount;
    }

    public void readAllPlayers() {
        String query = "SELECT * FROM Player;";
        System.out.println("\nRead all players:");
        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String player = rs.getString("PlayerName") + " Age: " + rs.getInt("PlayerAge");
                System.out.println(player);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void filteredByNick(String nick) {
        System.out.println("ReadPlayersFilteredByGenre");
        String query = "SELECT * FROM Player WHERE PlayerNick = ? ";
        readStatementFilter(query, nick);
    }

    public void filteredByName(String name) {
        System.out.println("ReadPlayersFilteredByName");
        String query = "SELECT * FROM Player WHERE PlayerName LIKE ? ";
        readStatementFilter(query, name);
    }

    public void filteredByAge(int age) {
        System.out.println("ReadPlayersFilteredByLeves");
        String query = "SELECT * FROM Player WHERE PlayerAge < ? ";

        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            stmt.setInt(1, age);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String player = rs.getString("PlayerName") + " Age: " + rs.getInt("PlayerAge");
                System.out.println(player);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void playersOrderedBy(String orderColumn) {
        System.out.println("Players ordered by " + orderColumn);
        String query = "SELECT * FROM Player ORDER BY " + orderColumn;
        readStatementOrderBy(query);
    }

    private void readStatementFilter(String query, String nick) {
        System.out.println("Prepared SQL Statement: " + query + "\n");

        try (PreparedStatement stmt = db.connect(url).prepareStatement(query)) {
            stmt.setString(1, nick);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String player = rs.getString("PlayerName") + " Age: " + rs.getInt("PlayerAge");
                System.out.println(player);
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
                String player = rs.getString("PlayerName") + " Age: " + rs.getInt("PlayerAge");
                System.out.println(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayer(Player p) {
        int affectedRows = 0;

        String updateSQL = "UPDATE Player SET ";
        updateSQL += " PlayerName = ?, ";
        updateSQL += " PlayerNick = ?, ";
        updateSQL += " PlayerAge = ? ";
        updateSQL += " WHERE PlayerId = ? ";
        try (PreparedStatement stmt = db.connect(url).prepareStatement(updateSQL)) {
            stmt.setString(1, p.getPlayerName());
            stmt.setString(2, p.getPlayerNick());
            stmt.setInt(3, p.getPlayerAge());
            stmt.setInt(4, p.getPlayerId());

            affectedRows = stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (affectedRows == 0) System.out.println("Game was not found.");
            if (affectedRows > 1) System.out.println("More than one Game with same ID.");
            if (affectedRows == 1) System.out.println("Data for Game with id " + p.getPlayerId() + " succesfully updated.");

        }
    }

    public int getLastInsertRowIDP() {
        int lastId = 0;
        String query = "SELECT MAX(PlayerId), PlayerName FROM Player;";

        try (Statement stmt = db.connect(url).createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                lastId = rs.getInt("MAX(PlayerId)");
                System.out.println("Last ID in the table Player is: " + lastId + " " + rs.getString("PlayerName"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  lastId;
    }


}
