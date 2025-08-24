package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        Session session = null;
        Transaction transaction = null;
        Country mergedCountry = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            mergedCountry = (Country) session.merge(country); // Зберігаємо результат merge
            transaction.commit();
            return mergedCountry; // Повертаємо об'єкт з оновленим id
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert or update country " + country, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public Optional<Country> get(Long id) {
        try (Session session = factory.openSession()) {
            // Метод get() повертає або об'єкт, або null, якщо його не знайдено
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get country by id: " + id, e);
        }
    }
}
