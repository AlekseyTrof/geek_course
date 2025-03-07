package chat.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class DbAuthServer implements AuthService {
    private static Connection connection;
    private static Statement statement;


    public DbAuthServer() {
        try {
            start();
            createTable();
            System.out.println("Auth service is running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:baseClients.db");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String pass) {
        try (ResultSet rs = statement.executeQuery("SELECT * FROM clients;")) {
            while (rs.next()) {
                StringBuilder client = new StringBuilder();
                client = client.append(rs.getString("login"));
                client = client.append(rs.getString("password"));
                if (client.toString().equals(login + pass)) {
                    return Optional.of(rs.getString("login"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void createTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS clients (\n" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                " login TEXT UNIQUE NOT NULL,\n" +
                " password TEXT NOT NULL\n" +
                " );");
    }

    public void insertOneClient(String login, String password) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO clients (login, password) VALUES" +
                " (?, ?);")) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClientFromBase(String login) throws SQLException {
        statement.executeUpdate("DELETE FROM clients WHERE login = '" + login + "';");
    }

    public void updateLoginOfClient(String loginOlg, String loginNew) throws SQLException {
        statement.executeUpdate("UPDATE clients SET login = '" + loginNew + "' WHERE login = '" + loginOlg + "';");
    }

    public void clearTableOfClients() throws SQLException {
        statement.executeUpdate("DELETE FROM clients;");
    }

    public void dropTableClients() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS clients;");
    }
}
