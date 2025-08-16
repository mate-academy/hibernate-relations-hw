package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    private static final String CANT_ADD_MSG = "Can't add actor: ";
    private static final String CANT_GET_BY_ID_MSG = "Can't get actor by id: ";

    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(actor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(CANT_ADD_MSG + actor, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = null;
        Actor actor = null;
        try {
            session = factory.openSession();
            actor = session.get(Actor.class, id);
        } catch (Exception e) {
            throw new DataProcessingException(CANT_GET_BY_ID_MSG + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(actor);
    }
}
