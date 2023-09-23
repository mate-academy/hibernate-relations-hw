package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
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
        Session session = this.factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(actor);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            session.close();
            throw new DataProcessingException();
        }
        session.close();
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = this.factory.openSession();
        Optional<Actor> answer = Optional.ofNullable(session.get(Actor.class,id));
        session.close();
        return answer;
    }
}
