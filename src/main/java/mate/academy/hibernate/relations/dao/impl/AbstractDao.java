package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public T add(T entity) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Long entityId = (Long) session.save(entity);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            throw new DataProcessingException("Error while adding entity: " + entity, e);
        }
    }

    public T get(Long id, Class<T> clazz) {
        try (Session session = factory.openSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting entity with id " + id, e);
        }
    }
}
