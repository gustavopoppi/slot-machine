package br.com.mvc.cacaniquel.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bet")
public class SlotMachineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double betValue;
    private int multiplier;
    private LocalDate date;
    private double totalBet;

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBetValue() {
        return betValue;
    }

    public void setBetValue(double betValue) {
        this.betValue = betValue;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalBet() {
        return totalBet;
    }

    public void setTotalBet(double totalBet) {
        this.totalBet = totalBet;
    }
}
