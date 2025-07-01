package org.example.repository;

import org.example.domain.Match;
import org.example.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IRepository<User, String> {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseConfig.getDbUrl(), DatabaseConfig.getDbUsername(), DatabaseConfig.getDbPassword());
    }

    @Override
    public Match add(User user) {
        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            logger.info("User with username {} added successfully.", user.getUsername());
        } catch (SQLException e) {
            logger.error("Error adding user with username {}: {}", user.getUsername(), e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"));
                users.add(user);
            }
            logger.info("Fetched {} users from the database.", users.size());
        } catch (SQLException e) {
            logger.error("Error fetching users: {}", e.getMessage());
        }
        return users;
    }

    @Override
    public User getById(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("password"));
                    logger.info("Fetched user with username {} from the database.", username);
                    return user;
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching user with username {}: {}", username, e.getMessage());
        }
        return null;
    }

    @Override
    public Match update(String username, User user) {
        String sql = "UPDATE user SET password = ? WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, username);
            stmt.executeUpdate();
            logger.info("User with username {} updated successfully.", username);
        } catch (SQLException e) {
            logger.error("Error updating user with username {}: {}", username, e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(String username) {
        String sql = "DELETE FROM user WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
            logger.info("User with username {} deleted successfully.", username);
        } catch (SQLException e) {
            logger.error("Error deleting user with username {}: {}", username, e.getMessage());
        }
    }
}
