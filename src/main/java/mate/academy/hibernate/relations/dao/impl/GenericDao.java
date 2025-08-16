package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;

public interface GenericDao<T> {
    T add(T t);

    Optional<T> get(Long id);
}
