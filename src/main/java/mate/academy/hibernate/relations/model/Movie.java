package mate.academy.hibernate.relations.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "actors")
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
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
}
