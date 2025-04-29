package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Transaction tx = null;
        Session session = factory.openSession();
        try {
            tx = session.beginTransaction();
            session.persist(actor);
            tx.commit();
            return actor;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new DataProcessingException("Can't add Actor " + actor, e);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            Actor actor = session.createQuery(
                            "SELECT a FROM Actor a LEFT JOIN FETCH a" +
                                    ".country WHERE a.id = :id", Actor.class)
                    .setParameter("id", id)
                    .uniqueResult();
            return Optional.ofNullable(actor);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Actor by id " + id, e);
        }
    }
}
