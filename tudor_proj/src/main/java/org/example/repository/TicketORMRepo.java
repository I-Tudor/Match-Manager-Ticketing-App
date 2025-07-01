package org.example.repository;

import org.example.domain.Match;
import org.example.domain.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.utils.HibernateUtil;

import java.util.List;

public class TicketORMRepo implements IRepository<Ticket, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(TicketRepository.class);

    @Override
    public Match add(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            try {
                session.merge(ticket);
                tx.commit();
                logger.info("Ticket added with ID {}", ticket.getId());
            } catch (Exception e) {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
                logger.error("Error adding ticket: {}", e.getMessage());
                throw e;
            }
        }
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Ticket> tickets = session.createQuery("FROM Ticket", Ticket.class).list();
            logger.info("Fetched {} tickets", tickets.size());
            return tickets;
        } catch (Exception e) {
            logger.error("Error fetching tickets: {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public Ticket getById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                logger.info("Fetched ticket with ID {}", id);
            }
            return ticket;
        } catch (Exception e) {
            logger.error("Error fetching ticket with ID {}: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public Match update(Integer id, Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ticket.setId(id);
            session.merge(ticket);
            tx.commit();
            logger.info("Ticket with ID {} updated", id);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error updating ticket with ID {}: {}", id, e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
                logger.info("Ticket with ID {} deleted", id);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error deleting ticket with ID {}: {}", id, e.getMessage());
        }
    }
}
