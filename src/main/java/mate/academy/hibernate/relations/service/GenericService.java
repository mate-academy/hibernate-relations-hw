package mate.academy.hibernate.relations.service;

import java.util.List;

public interface GenericService<T> {
    T add(T elem);

    T get(Long id);

    T update(T elem);

    T delete(T elem);

    List<T> getAll();
}
