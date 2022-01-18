package ro.ubb.catalog.core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import ro.ubb.catalog.core.ITConfig;
import ro.ubb.catalog.core.model.Actor;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF.dbtest/db-data.xml")
public class
ActorServiceTest {
    @Autowired
    private ActorService actorService;

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void addActor() {
        List<Actor> actorList = actorService.getAllActors();
        assertEquals(3, actorList.size());
        Actor newActor = new Actor("andrew", 20, "male");
        Actor addedActor = actorService.addActor(newActor);
        assertEquals(addedActor.getName(), "andrew");
        actorList = actorService.getAllActors();
        assertEquals(4, actorList.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void getAllActors() {
        List<Actor> actorList = actorService.getAllActors();
        assertEquals(3, actorList.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void deleteActor() {
        List<Actor> actorList = actorService.getAllActors();
        assertEquals(3, actorList.size());
        Long actorId = actorList.get(2).getId();
        actorService.deleteActor(actorId);
        actorList = actorService.getAllActors();
        assertEquals(2, actorList.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void updateActor() {
        List<Actor> actorList = actorService.getAllActors();
        assertEquals(3, actorList.size());
        Actor updatedActor = new Actor("georgia", 25, "female");
        updatedActor.setId(13L);
        Actor actor = actorService.updateActor(updatedActor);
        assertTrue(actor.getAge().equals(25));
        actorList = actorService.getAllActors();
        assertEquals(3, actorList.size());
    }
}
