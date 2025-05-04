package mate.academy.hibernate.relations.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.dao.ActorDao;
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
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Long actorId = (Long) session.save(actor);
            transaction.commit();
            actor.setId(actorId);
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while adding actor to the database. Actor: "
                    + actor, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error getting actor with ID: " + id, e);
        }
    }

    @Override
    public List<Actor> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Actor", Actor.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting actors from the database", e);
        }
    }

    @Override
    public Actor update(Actor actor) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(actor);
            transaction.commit();
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error updating actor: " + actor, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Actor actorToDelete = session.get(Actor.class, id);
            if (actorToDelete != null) {
                session.delete(actorToDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error deleting actor. Actor ID: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
