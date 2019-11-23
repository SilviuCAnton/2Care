package repositories;

import domain.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDBRepository implements CrudRepository<Integer, UserAccount> {

    private Connection connection;

    public UserAccountDBRepository(String connectionString, String userName, String password) {
        this.connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager
                    .getConnection(connectionString, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserAccount findOne(Integer id) {
        String username, password;
        boolean isDoctor;
        int userID;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserAccounts WHERE id = ?;")) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            userID = resultSet.getInt("id");
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            isDoctor = resultSet.getBoolean("isDoctor");
            return new UserAccount(userID, username, password, isDoctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<UserAccount> findAll() {
        List<UserAccount> allUsers = new ArrayList<>();
        UserAccount user;
        String username, password;
        int userId;
        boolean isDoctor;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserAccounts;")) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt("id");
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                isDoctor = resultSet.getBoolean("isDoctor");
                user = new UserAccount(userId, username, password, isDoctor);
                allUsers.add(user);
            }
            return allUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            return allUsers;
        }
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        if (userAccount == null) {
            throw new IllegalArgumentException("Student cannot be null!!");
        }
        UserAccount user = findOne(userAccount.getId());
        if (user != null) {
            return user;
        }
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO UserAccounts(id, username, password, isDoctor) VALUES(?,?,?,?);")) {
            stmt.setInt(1, userAccount.getId());
            stmt.setString(2, userAccount.getUsername());
            stmt.setString(3, userAccount.getPassword());
            stmt.setBoolean(4, userAccount.isDoctor());
            stmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserAccount delete(Integer userId) {
        UserAccount user = findOne(userId);
        if (user == null) {
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM UserAccounts WHERE id = ?;")) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        UserAccount user = findOne(userAccount.getId());
        if (user == null) {
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE UserAccounts SET username = ?, password = ?, isDoctor = ? WHERE id = ?")) {
            stmt.setString(1, userAccount.getUsername());
            stmt.setString(2, userAccount.getPassword());
            stmt.setBoolean(3, userAccount.isDoctor());
            stmt.setInt(4, userAccount.getId());
            stmt.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
