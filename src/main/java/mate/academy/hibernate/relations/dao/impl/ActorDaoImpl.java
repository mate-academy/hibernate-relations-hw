package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
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
        Session currentSession = null;
        Transaction transaction = null;
        try {
            currentSession = super.factory.openSession();
            transaction = currentSession.beginTransaction();
            currentSession.save(actor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add actor: " + actor,e);
        } finally {
            currentSession.close();
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session currentSession = super.factory.openSession();) {
            Actor actor = currentSession.get(Actor.class, id);
            return Optional.ofNullable(actor);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get by id: " + id, e);
        }
    }
}
