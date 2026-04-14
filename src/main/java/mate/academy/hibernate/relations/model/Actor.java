package mate.academy.hibernate.relations.model;

import jakarta.persistence.*;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Country country;

    public Actor() {}

    public Actor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}

