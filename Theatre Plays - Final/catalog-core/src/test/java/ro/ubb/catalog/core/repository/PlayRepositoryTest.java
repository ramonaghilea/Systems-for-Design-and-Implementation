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
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.play.PlayRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF.dbtest/db-data.xml")
public class PlayRepositoryTest {
    @Autowired
    private PlayRepository playRepository;

    @Test
    public void findByDurationEquals() {
        List<Play> playList = playRepository.findByDurationEquals(100L);
        assertEquals(1, playList.size());
    }

    @Test
    public void findByOrderByDurationDesc() {
        List<Play> playList = playRepository.findByOrderByDurationDesc();
        assertEquals("othello", playList.get(0).getPlayName());
    }

    @Test
    public void findAllWithDirector() {
        List<Play> playList = playRepository.findAllWithDirector();
        assertEquals(3, playList.size());
    }

    @Test
    public void findAllWithPerformances() {
        List<Play> playList = playRepository.findAllWithPerformances();
        assertEquals(3, playList.size());
    }

    @Test
    public void findAllWithPerformancesWithActor() {
        List<Play> playList = playRepository.findAllWithPerformancesWithActor();
        assertEquals(3, playList.size());
    }

    @Test
    public void findAllWithDirectorQuery() {
        List<Play> playList = playRepository.findAllWithDirectorQuery();
        assertEquals(3, playList.size());
    }

    @Test
    public void findAllWithPerformancesQuery() {
        List<Play> playList = playRepository.findAllWithPerformancesQuery();
        assertEquals(3, playList.size());
    }

    @Test
    public void findAllWithPerformancesWithActorQuery() {
        List<Play> playList = playRepository.findAllWithPerformancesWithActorQuery();
        assertEquals(3, playList.size());
    }
}
