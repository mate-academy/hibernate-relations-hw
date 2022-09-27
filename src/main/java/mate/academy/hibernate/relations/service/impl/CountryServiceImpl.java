package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class CountryServiceImpl implements CountryService {
    private CountryDao dao = new CountryDaoImpl(HibernateUtil.getSessionFactory());
    
    @Override
    public Country add(Country country) {
        return dao.add(country);
    }
    
    @Override
    public Country get(Long id) {
        return dao.get(id).orElseThrow(() -> new DataProcessingException(
                "No country with ID: " + id));
    }
}
