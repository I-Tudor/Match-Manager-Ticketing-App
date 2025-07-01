package org.example.GUI;

import org.example.domain.Match;
import org.example.domain.Ticket;
import org.example.domain.User;
import org.example.repository.MatchRepository;
import org.example.repository.TicketRepository;
import org.example.repository.UserRepository;

import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MatchRepository matchRepo = new MatchRepository();
        TicketRepository ticketRepo = new TicketRepository();
        UserRepository userRepo = new UserRepository();

        Match match1 = new Match(1, "Team A", "Team B", 50.0, 100);
        Match match2 = new Match(2, "Team C", "Team D", 60.0, 120);

        Ticket ticket1 = new Ticket(1, 1, "Customer1", 2);
        Ticket ticket2 = new Ticket(2, 2, "Customer2", 3);

        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");

       matchRepo.add(match1);
    }
}