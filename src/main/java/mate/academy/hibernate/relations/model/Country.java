package mate.academy.hibernate.relations.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "countries")
public class Country implements Cloneable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    @Override
    public Country clone() {
        try {
            return (Country) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't make clone of " + this, e);
        }
    }

    @Override
    public String toString() {
        return "Country{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
