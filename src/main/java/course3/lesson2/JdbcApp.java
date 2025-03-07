package course3.lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class JdbcApp {
    private static Connection connection;
    private static Statement stmt;
    private static Random random = new Random();

    public static void main(String[] args) {
        try {
            connect();
            createTableEx();
            readEx();
            System.out.println();
            insertEx();
            insertOneStudent("Bob", "Java", 100);
            insertManyStudents();
            readEx();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:demobase.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
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

    private static void createTableEx() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (\n" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                " name TEXT,\n" +
                " group_name TEXT,\n" +
                " score INTEGER\n" +
                " );");
    }

    private static void dropTableEx() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS students;");
    }

    private static void readEx() throws SQLException {
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM students;")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " +
                        rs.getString(3) + " " + rs.getInt(4));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void clearTableEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM students;");
    }

    private static void deleteEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM students WHERE name = 'Bob4';");
    }

    private static void updateEx() throws SQLException {
        stmt.executeUpdate("UPDATE students SET score = 100 WHERE name = 'Bob';");
    }

    private static void insertOneStudent(String name, String groupName, int score) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO students (name, group_name, score) VALUES" +
                " (?, ?, ?);")) {
            pstmt.setString(1, name);
            pstmt.setString(2, groupName);
            pstmt.setInt(3, score);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertManyStudents() throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO students (name, group_name, score) VALUES" +
                " (?, ?, ?)")) {
            for (int i = 1; i <= 10; i++) {
                pstmt.setString(1, "Jack" + i);
                pstmt.setString(2, "53");
                pstmt.setInt(3, random.nextInt(6));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, group_name, score) VALUES" +
                " ('Bob', '53', 60);");
    }
}
