package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<EntityT> {
    protected final SessionFactory factory;
    private final Class<EntityT> entityClass;

    public AbstractDao(SessionFactory factory, Class<EntityT> entityClass) {
        this.factory = factory;
        this.entityClass = entityClass;
    }

    public EntityT add(EntityT entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<EntityT> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(entityClass, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get entity by id: " + id, e);
        }
    }
}
