package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(actor);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
//            throw new DataProcessingException TO BE IMPLEMENTED
        } finally {
          session.close();
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Actor actor = null;
        try {
            actor = session.get(Actor.class, id);
        } catch (RuntimeException e) {
            throw new DataProcessingException("Error when adding an actor", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(actor);
    }
}
