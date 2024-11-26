package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.util.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    private final SessionFactory sessionFactory = super.factory;
    private final DaoUtil daoUtil = new DaoUtil();

    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            daoUtil.processCountryByActor(actor, session);
            actor = session.merge(actor);
            transaction.commit();
            return actor;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while adding actor object:"
                    + actor.toString(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return Optional.ofNullable(session.get(Actor.class, id));
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
