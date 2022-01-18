package ro.ubb.catalog.core.service;

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
import ro.ubb.catalog.core.model.Performance;
import ro.ubb.catalog.core.model.Play;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF.dbtest/db-data.xml")
public class PerformanceServiceTest {
    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private PlayService playService;

    @Autowired
    private ActorService actorService;

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void addPerformance() {
        List<Performance> performances = performanceService.getAllPerformances();
        assertEquals(2, performances.size());

        List<Play> plays = playService.getAllPlays();
        List<Actor> actors = actorService.getAllActors();

        Performance newPerformance = new Performance(plays.get(2), actors.get(2), "newrole");
        Performance addedPerformance = performanceService.addPerformance(newPerformance);

        assertEquals(addedPerformance.getPlay(), plays.get(2));

        performances = performanceService.getAllPerformances();
        assertEquals(3, performances.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void deletePerformance() {
        List<Performance> performances = performanceService.getAllPerformances();
        assertEquals(2, performances.size());

        performanceService.deletePerformance(11L, 12L);

        performances = performanceService.getAllPerformances();
        assertEquals(1, performances.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void getAllPerformances() {
        List<Performance> performances = performanceService.getAllPerformances();
        assertEquals(2, performances.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void updatePerformance() {
        List<Performance> performances = performanceService.getAllPerformances();
        assertEquals(2, performances.size());

        List<Play> plays = playService.getAllPlays();
        List<Actor> actors = actorService.getAllActors();

        Performance newPerformance = new Performance(plays.get(2), actors.get(2), "newrole");
        performanceService.addPerformance(newPerformance);

        Long playId = plays.get(2).getId();
        Long actorId = actors.get(2).getId();
        Performance updatePerformance = new Performance(plays.get(2), actors.get(2), "newjuliet");
        performanceService.updatePerformance(updatePerformance);

        Performance updatePerformance2 = performanceService.getPerformance(playId, actorId);
        assertEquals("newjuliet", updatePerformance2.getRole());
    }
}
