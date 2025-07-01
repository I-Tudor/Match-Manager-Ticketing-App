package org.example.repository;

import org.example.domain.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MatchRepository implements IRepository<Match, Integer> {
    private static final Logger logger = LoggerFactory.getLogger(MatchRepository.class);

    @Autowired
    public MatchRepository() {}

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseConfig.getDbUrl(), DatabaseConfig.getDbUsername(), DatabaseConfig.getDbPassword());
    }

    @Override
    public Match add(Match match) {
        String sql = "INSERT INTO `match` (teamA, teamB, ticketPrice, availableSeats) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, match.getTeamA());
            stmt.setString(2, match.getTeamB());
            stmt.setDouble(3, match.getTicketPrice());
            stmt.setInt(4, match.getAvailableSeats());
            stmt.executeUpdate();
            logger.info("Match with ID {} added successfully.", match.getId());
            return match;
        } catch (SQLException e) {
            logger.error("Error adding match with ID {}: {}", match.getId(), e.getMessage());
        }
        return null;
    }

    @Override
    public List<Match> getAll() {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM `match`";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Match match = new Match(rs.getInt("id"), rs.getString("teamA"), rs.getString("teamB"),
                        rs.getDouble("ticketPrice"), rs.getInt("availableSeats"));
                matches.add(match);
            }
            logger.info("Fetched {} matches from the database.", matches.size());
        } catch (SQLException e) {
            logger.error("Error fetching matches: {}", e.getMessage(), e);        }
        return matches;
    }

    @Override
    public Match getById(Integer id) {
        String sql = "SELECT * FROM `match` WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Match match = new Match(rs.getInt("id"), rs.getString("teamA"), rs.getString("teamB"),
                            rs.getDouble("ticketPrice"), rs.getInt("availableSeats"));

                    logger.info("Fetched match with ID {} from the database.", id);
                    return match;
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching match with ID {}: {}", id, e.getMessage());
        }
        return null;
    }

    @Override
    public Match update(Integer id, Match match) {
        String sql = "UPDATE `match` SET teamA = ?, teamB = ?, ticketPrice = ?, availableSeats = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, match.getTeamA());
            stmt.setString(2, match.getTeamB());
            stmt.setDouble(3, match.getTicketPrice());
            stmt.setInt(4, match.getAvailableSeats());
            stmt.setInt(5, id);
            stmt.executeUpdate();
            logger.info("Match with ID {} updated successfully.", id);
            return match;
        } catch (SQLException e) {
            logger.error("Error updating match with ID {}: {}", id, e.getMessage());
        }
        return match;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM `match` WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Match with ID {} deleted successfully.", id);
        } catch (SQLException e) {
            logger.error("Error deleting match with ID {}: {}", id, e.getMessage());
        }
    }
}
