package org.example.service;
import org.example.domain.Match;
import org.example.domain.Ticket;
import org.example.repository.IRepository;
import org.example.repository.MatchRepository;
import org.example.repository.TicketRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private IRepository<Match, Integer> matchRepo;
    private IRepository<Ticket, Integer> ticketRepo;

    public TicketService(IRepository matchRepo, IRepository ticketRepo) {
        this.matchRepo = matchRepo;
        this.ticketRepo = ticketRepo;
    }

    public List<Match> getMatches() {
        return matchRepo.getAll();
    }

    public boolean sellTicket(int matchId, String customerName, int seats) {
        Match match = matchRepo.getAll().stream()
                .filter(m -> m.getId() == matchId)
                .findFirst()
                .orElse(null);

        if (match != null) {
            if (match.getAvailableSeats() >= seats) {
                int newTicketId = ticketRepo.getAll().size() + 1;

                Ticket ticket = new Ticket(newTicketId, matchId, customerName, seats);
                ticketRepo.add(ticket);

                match.setAvailableSeats(match.getAvailableSeats() - seats);
                matchRepo.update(match.getId(), match);


                return true;
            }
        }

        return false;
    }

    public List<Match> getAvailableMatches() {
        return matchRepo.getAll().stream()
                .filter(m ->  m.getAvailableSeats() > 0)
                .sorted(Comparator.comparingInt(Match::getAvailableSeats).reversed())
                .collect(Collectors.toList());
    }

}
