package mate.academy.hibernate.relations.dao;

import java.util.Optional;

public interface Dao<T> {
    T add(T entity);

    Optional<T> get(Long id);
}
