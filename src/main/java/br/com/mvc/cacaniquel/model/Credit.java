package br.com.mvc.cacaniquel.model;

import javax.persistence.*;

@Entity
@Table(name="credit")
public class Credit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double creditValue;

    @OneToOne(optional = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(double creditValue) {
        this.creditValue = creditValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
