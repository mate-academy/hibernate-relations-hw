package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;

class DaoUtil {

    public void processCountryByActor(Actor actor, Session session) {
        Optional<Country> optionalCountry = Optional.empty();
        Country country;
        if (actor.getCountry() != null) {
            optionalCountry
                    = Optional.ofNullable(
                    session.get(Country.class, actor.getCountry().getId()));
        }
        if (optionalCountry.isEmpty() && actor.getCountry() != null) {
            country = actor.getCountry();
            session.persist(country);
        } else {
            optionalCountry.ifPresent(actor::setCountry);
        }
    }

    public void processActorListByMovie(Movie movie, Session session) {
        if (movie.getActors() == null || movie.getActors().isEmpty()) {
            return;
        }
        for (Actor actor : movie.getActors()) {
            processActorByActorList(actor, session);
        }
    }

    private Actor processActorByActorList(Actor singleActor, Session session) {
        if (singleActor != null && singleActor.getId() != null) {
            Optional<Actor> optionalActor
                    = Optional.ofNullable(session.get(Actor.class, singleActor.getId()));
            if (optionalActor.isPresent()) {
                return optionalActor.get();
            }
        }
        if (singleActor != null) {
            processCountryByActor(singleActor, session);
            session.persist(singleActor);
            return singleActor;
        }

        return null;
    }

}
