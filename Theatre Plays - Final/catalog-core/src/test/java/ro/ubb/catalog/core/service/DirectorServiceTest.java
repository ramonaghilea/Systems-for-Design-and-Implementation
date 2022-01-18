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
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF.dbtest/db-data.xml")
public class DirectorServiceTest {
    @Autowired
    private DirectorService directorService;

    @Autowired
    private OfficeService officeService;

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void addDirector() {
        List<Director> directors = directorService.getAllDirectors();
        assertEquals(3, directors.size());
        Director newDirector = new Director("kira", 30, "female", new Office("34P", "str 34"));
        Director addedDirector = directorService.addDirector(newDirector);
        assertEquals(addedDirector.getName(), "kira");
        directors = directorService.getAllDirectors();
        List<Office> offices = officeService.getAllOffices();
        assertEquals(4, directors.size());
        assertEquals(3, offices.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void getAllDirectors() {
        List<Director> directors = directorService.getAllDirectors();
        assertEquals(3, directors.size());
    }

    @Test
    @DatabaseSetup("/META-INF.dbtest/db-data.xml")
    public void deleteDirector() {
        List<Director> directors = directorService.getAllDirectors();
        assertEquals(3, directors.size());
        directorService.deleteDirector(11L);
        directors = directorService.getAllDirectors();
        assertEquals(2, directors.size());
    }
}
