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
        Session session = null;
        Transaction transaction = null;
        Actor mergedActor = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            mergedActor = (Actor) session.merge(actor); // Зберігаємо результат merge
            transaction.commit();
            return mergedActor; // Повертаємо об'єкт з оновленим id
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert or update actor " + actor, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public Optional<Actor> get(Long id) {
        try (Session session = factory.openSession()) {
            // Метод get() повертає або об'єкт, або null, якщо його не знайдено
            return Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get actor by id: " + id, e);
        }
    }
}
