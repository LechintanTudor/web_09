package ro.ubb.imgur.persistence;

import ro.ubb.imgur.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DB_CONNECTION_STRING = "jdbc:postgresql://localhost:5432/imgur";
    private static final String DB_USERNAME = "imgur";
    private static final String DB_PASSWORD = "imgur";

    private static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_CONNECTION_STRING, DB_USERNAME, DB_PASSWORD);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }

    public static List<Account> getAllAccounts() {
        try (
                Connection connection = connect();
                Statement statement = connection.createStatement()
        ) {
            String query = "SELECT id, username, password FROM account";
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<Account> accounts = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                accounts.add(new Account(id, username, password));
            }

            return accounts;

        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }

    public static Account authenticate(String username, String password) {
        String query = "SELECT id FROM account WHERE username = ? AND password = ?";

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getInt("id");
                return new Account(id, username, password);
            } else {
                return null;
            }
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
