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
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF.dbtest/db-data.xml")
public class PlayServiceTest {
    @Autowired
    private PlayService playService;

    @Autowired
    private DirectorService directorService;

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void getAllPlays() {
        List<Play> plays = playService.getAllPlays();
        assertEquals(3, plays.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void addPlay() {
        List<Play> plays = playService.getAllPlays();
        assertEquals(3, plays.size());
        Director director = directorService.getAllDirectorsReport().get(0);
        Play newPlay = new Play("play5", 300L, director);
        Play play = playService.addPlay(newPlay);
        assertEquals(play.getPlayName(), "play5");
        plays = playService.getAllPlays();
        assertEquals(4, plays.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void deletePlay() {
        List<Play> plays = playService.getAllPlays();
        assertEquals(3, plays.size());
        playService.deletePlay(12L);
        plays = playService.getAllPlays();
        assertEquals(2, plays.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void updatePlay() {
        List<Play> plays = playService.getAllPlays();
        assertEquals(3, plays.size());
        Director director = directorService.getAllDirectorsReport().get(0);
        Play updatedPlay = new Play("play6", 300L, director);
        updatedPlay.setId(12L);
        playService.updatePlay(updatedPlay);
        plays = playService.getAllPlays();
        assertEquals(3, plays.size());
        assertEquals("play6", playService.getPlay(12L).getPlayName());
    }
}
