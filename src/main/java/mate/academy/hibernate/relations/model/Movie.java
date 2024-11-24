package mate.academy.hibernate.relations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Movie implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany
    private List<Actor> actors;

    public Movie(String title) {
        this.title = title;
    }

    @Override
    public Movie clone() {
        try {
            Movie movie = (Movie) super.clone();
            if (movie.getActors() != null) {
                List<Actor> actors = new ArrayList<>();
                for (Actor actor : movie.getActors()) {
                    actors.add(actor.clone());
                }
                movie.setActors(actors);
            }
            return movie;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't make clone of " + this, e);
        }
    }

    @Override
    public String toString() {
        return "Movie{"
                + "id=" + id
                + ", title='" + title + '\''
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Movie movie = (Movie) obj;
        return Objects.equals(id, movie.id)
                && Objects.equals(title, movie.title)
                && Objects.equals(actors, movie.actors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(actors);
        return result;
    }
}
