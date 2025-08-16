package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    private static final String EXCEPTION_CAN_T_GET_COUNTRY_MESSAGE =
            "Can not get country with id: ";

    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(country);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw new DataProcessingException(
                        "Can not to add country with name: " + country.getName(), e);
            }
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException(EXCEPTION_CAN_T_GET_COUNTRY_MESSAGE + id, e);
        }
    }
}
