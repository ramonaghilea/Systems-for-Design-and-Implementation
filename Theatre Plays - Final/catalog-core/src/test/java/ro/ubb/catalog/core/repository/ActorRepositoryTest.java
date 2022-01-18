package ro.ubb.catalog.core.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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
import ro.ubb.catalog.core.repository.actor.ActorRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF.dbtest/db-data.xml")
public class ActorRepositoryTest {
    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void findByNameContains() {
        List<Actor> actorList = actorRepository.findByNameContains("a");
        assertEquals(2, actorList.size());
    }

    @Test
    public void findByOrderByAgeDesc() {
        List<Actor> actorList = actorRepository.findByOrderByAgeDesc();
        assertEquals("maria", actorList.get(0).getName());
    }

    @Test
    public void findAll() {
        List<Actor> actorList = actorRepository.findAll();
        assertEquals(3, actorList.size());
    }

    @Test
    public void findAllWithPerformancesQuery() {
        List<Actor> actorList = actorRepository.findAllWithPerformancesQuery();
        assertEquals(3, actorList.size());
    }

    @Test
    public void findOneWithPerformancesQuery() {
        Actor actor = actorRepository.findOneWithPerformancesQuery(11L);
        assertTrue(actor.getName().equals("john"));
    }

    @Test
    public void findAllWithPerformancesWithPlaysQuery() {
        List<Actor> actorList = actorRepository.findAllWithPerformancesWithPlaysQuery();
        assertEquals(3, actorList.size());
    }

    @Test
    public void findAllWithPerformances() {
        List<Actor> actorList = actorRepository.findAllWithPerformances();
        assertEquals(3, actorList.size());
    }

    @Test
    public void findAllWithPerformancesWithPlays() {
        List<Actor> actorList = actorRepository.findAllWithPerformancesWithPlays();
        assertEquals(3, actorList.size());
    }
}
