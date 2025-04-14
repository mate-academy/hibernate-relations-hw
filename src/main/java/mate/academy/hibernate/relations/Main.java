package mate.academy.hibernate.relations;

import mate.academy.hibernate.relations.lib.Injector;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    private static final Injector injector = Injector
            .getInstance("mate.academy.hibernate.relations");

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        System.out.println("Hello world!");
    }
}
