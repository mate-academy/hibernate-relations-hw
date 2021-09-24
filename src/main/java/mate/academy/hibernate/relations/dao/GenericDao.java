package mate.academy.hibernate.relations.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    T add(T elem);

    Optional<T> get(Long id);

    T update(T elem);

    List<T> getAll();

    T delete(T elem);
}
