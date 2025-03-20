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
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            if (isActorExists(actor.getName(), session)) {
                return session.createQuery("FROM Actor a WHERE a.name = :name", Actor.class)
                        .setParameter("name", actor.getName())
                        .uniqueResult();
            }

            session.save(actor.getCountry());
            session.save(actor);
            transaction.commit();
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert actor " + actor, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private boolean isActorExists(String actorName, Session session) {
        String hql = "FROM Actor a WHERE a.name = :name";
        return session.createQuery(hql, Actor.class)
                .setParameter("name", actorName)
                .uniqueResult() != null;
    }

    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get actor by id " + id, e);
        }
    }
}
