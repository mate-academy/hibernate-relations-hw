package mate.academy.hibernate.relations.service.impl;

public interface GenericService<T> {
    T add(T t);

    T get(Long id);
}
