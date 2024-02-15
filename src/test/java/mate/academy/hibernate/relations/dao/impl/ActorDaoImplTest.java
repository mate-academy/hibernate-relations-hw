package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.AbstractTest;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import org.junit.Assert;
import org.junit.Test;

public class ActorDaoImplTest extends AbstractTest {
    private static final Actor christianBale = new Actor("Christian Bale");
    private static final Actor bradPitt = new Actor("Brad Pitt");

    @Override
    protected Class<?>[] entities() {
        return new Class[]{Actor.class, Country.class};
    }

    @Test
    public void create_Ok() {
        ActorDao actorDao = new ActorDaoImpl(getSessionFactory());
        verifyCreateActorWorks(actorDao, christianBale.clone(), 1L);
        verifyCreateActorWorks(actorDao, bradPitt.clone(), 2L);
    }

    @Test
    public void getById_Ok() {
        ActorDao actorDao = new ActorDaoImpl(getSessionFactory());
        verifyCreateActorWorks(actorDao, christianBale.clone(), 1L);
        Optional<Actor> christianBaleOptional = actorDao.get(1L);
        Assert.assertTrue(christianBaleOptional.isPresent());
        Actor actualChristianBale = christianBaleOptional.get();
        Assert.assertNotNull(actualChristianBale);
        Assert.assertEquals(1L, actualChristianBale.getId().longValue());
        Assert.assertEquals(christianBale.getName(), actualChristianBale.getName());

        verifyCreateActorWorks(actorDao, bradPitt.clone(), 2L);
        Optional<Actor> bradPittOptional = actorDao.get(2L);
        Assert.assertTrue(bradPittOptional.isPresent());
        Actor actualBradPitt = bradPittOptional.get();
        Assert.assertNotNull(actualBradPitt);
        Assert.assertEquals(2L, actualBradPitt.getId().longValue());
        Assert.assertEquals(bradPitt.getName(), actualBradPitt.getName());
    }

    @Test
    public void getByNotExistingId_Ok() {
        ActorDao actorDao = new ActorDaoImpl(getSessionFactory());
        Optional<Actor> actual = actorDao.get(100L);
        Assert.assertFalse(actual.isPresent());
    }

    static void verifyCreateActorWorks(ActorDao actorDao, Actor actor, Long expectedId) {
        Actor actual = actorDao.add(actor);
        Assert.assertNotNull("Check you have implemented the `create` method " +
                "in the ActorDaoImpl class", actual);
        Assert.assertNotNull("ID for actor should be autogenerated", actual.getId());
        Assert.assertEquals(expectedId, actual.getId());
        Assert.assertEquals(actor.getName(), actual.getName());
    }
}
