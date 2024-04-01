package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorServiceImpl implements ActorService {
    @Override
    public Actor add(Actor actor) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(actor);
        transaction.commit();
        session.close();
        return actor;
    }

    @Override
    public Actor get(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.get(Actor.class, id);
    }
}
