package org.example.repository;

import org.example.domain.Match;
import org.example.domain.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository implements IRepository<Ticket, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(TicketRepository.class);

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseConfig.getDbUrl(), DatabaseConfig.getDbUsername(), DatabaseConfig.getDbPassword());
    }

    @Override
    public Match add(Ticket ticket) {
        String sql = "INSERT INTO ticket (id, matchId, customerName, seatsBought) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getId());
            stmt.setInt(2, ticket.getMatchId());
            stmt.setString(3, ticket.getCustomerName());
            stmt.setInt(4, ticket.getSeatsBought());
            stmt.executeUpdate();
            logger.info("Ticket with ID {} added successfully.", ticket.getId());
        } catch (SQLException e) {
            logger.error("Error adding ticket with ID {}: {}", ticket.getId(), e.getMessage());
        }
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getInt("id"), rs.getInt("matchId"), rs.getString("customerName"), rs.getInt("seatsBought"));
                tickets.add(ticket);
            }
            logger.info("Fetched {} tickets from the database.", tickets.size());
        } catch (SQLException e) {
            logger.error("Error fetching tickets: {}", e.getMessage());
        }
        return tickets;
    }

    @Override
    public Ticket getById(Integer id) {
        String sql = "SELECT * FROM ticket WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Ticket ticket = new Ticket(rs.getInt("id"), rs.getInt("matchId"), rs.getString("customerName"), rs.getInt("seatsBought"));
                    logger.info("Fetched ticket with ID {} from the database.", id);
                    return ticket;
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching ticket with ID {}: {}", id, e.getMessage());
        }
        return null;
    }

    @Override
    public Match update(Integer id, Ticket ticket) {
        String sql = "UPDATE ticket SET matchId = ?, customerName = ?, seatsBought = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getMatchId());
            stmt.setString(2, ticket.getCustomerName());
            stmt.setInt(3, ticket.getSeatsBought());
            stmt.setInt(4, id);
            stmt.executeUpdate();
            logger.info("Ticket with ID {} updated successfully.", id);
        } catch (SQLException e) {
            logger.error("Error updating ticket with ID {}: {}", id, e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM ticket WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Ticket with ID {} deleted successfully.", id);
        } catch (SQLException e) {
            logger.error("Error deleting ticket with ID {}: {}", id, e.getMessage());
        }
    }
}
