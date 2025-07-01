package org.example.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "match_id", nullable = false)
    private int matchId;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Column(name = "seats_bought", nullable = false)
    private int seatsBought;


    public Ticket() {}

    public Ticket(int id, int matchId, String customerName, int seatsBought) {
        this.id = id;
        this.matchId = matchId;
        this.customerName = customerName;
        this.seatsBought = seatsBought;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getSeatsBought() {
        return seatsBought;
    }

    public void setSeatsBought(int seatsBought) {
        this.seatsBought = seatsBought;
    }
}
