package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;

public class ActorServiceImpl implements ActorService {
    private final ActorDao ad;

    public ActorServiceImpl(ActorDao ad) {
        this.ad = ad;
    }

    @Override
    public Actor add(Actor actor) {
        return ad.add(actor);
    }

    @Override
    public Actor get(Long id) {
        return ad.get(id).orElseThrow(
                () -> new RuntimeException("Cant get actor by id: " + id));
    }
}
