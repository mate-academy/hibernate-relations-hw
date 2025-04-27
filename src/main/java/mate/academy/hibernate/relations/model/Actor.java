package mate.academy.hibernate.relations.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "actors")
public class Actor implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;

    public Actor() {}
    public Actor(String name) { this.name = name; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }

    @Override
    public Actor clone() {
        try {
            Actor clone = (Actor) super.clone();
            if (country != null) {
                clone.country = country.clone();
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't clone Actor", e);
        }
    }

    @Override
    public String toString() { return "Actor{id=" + id + ", name='" + name + "', country=" + country + "}"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id);}
}
