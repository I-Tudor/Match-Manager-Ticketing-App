package org.example.service;

import org.example.domain.Match;
import org.example.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {
    private final MatchRepository repository = new MatchRepository();

    public Match createMatch(String teamA, String teamB, double ticketPrice, int availableSeats) {
        Match match = new Match(0, teamA, teamB, ticketPrice, availableSeats);
        return repository.add(match);
    }

    @Autowired
    public MatchService() {
    }

    public Match updateMatch(int id, Match match) {
        return repository.update(id, match);
    }

    public Match getMatchById(int id) {
        return repository.getById(id);
    }

    public List<Match> getAllMatches() {
        return repository.getAll();
    }

    public void deleteMatch(int id) {
        repository.delete(id);
    }
}
