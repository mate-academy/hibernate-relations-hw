package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.impl.GenericDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.service.exception.EntityNotFoundException;

public class CountryServiceImpl extends GenericServiceImpl<Country> implements CountryService {
    public CountryServiceImpl(GenericDao<Country> actorDao) {
        super(actorDao);
    }

    @Override
    public Country get(Long id) {
        return dao.get(id).orElseThrow(()
                -> new EntityNotFoundException("Country not found with id "
                + id));
    }
}
