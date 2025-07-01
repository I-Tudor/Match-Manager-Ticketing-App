package org.example.test;

import org.example.domain.Match;
import org.example.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService = new MatchService();

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Match> create(@RequestBody Match match) {
        matchService.createMatch(match.getTeamA(), match.getTeamB(), match.getTicketPrice(), match.getAvailableSeats());
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    @PutMapping("/{id}")
    public Match update(@PathVariable int id, @RequestBody Match match) {
        match.setId(id);
        return matchService.updateMatch(id, match);
    }

    @GetMapping("/{id}")
    public Match getById(@PathVariable int id) {
        return matchService.getMatchById(id);
    }

    @GetMapping
    public List<Match> getAll() {
        return matchService.getAllMatches();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        matchService.deleteMatch(id);
    }
}
