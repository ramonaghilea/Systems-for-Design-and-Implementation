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
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.repository.director.DirectorRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF.dbtest/db-data.xml")
public class DirectorRepositoryTest {
    @Autowired
    private DirectorRepository directorRepository;

    @Test
    public void findAllWithOffices() {
        List<Director> directorList = directorRepository.findAllWithOffices();
        assertEquals(3, directorList.size());
    }

    @Test
    public void findAllWithOfficeAndPlays() {
        List<Director> directorList = directorRepository.findAllWithOfficeAndPlays();
        assertEquals(3, directorList.size());
    }

    @Test
    public void findAllWithOfficesQuery() {
        List<Director> directorList = directorRepository.findAllWithOfficesQuery();
        assertEquals(3, directorList.size());
    }

    @Test
    public void findAllWithOfficeAndPlaysQuery() {
        List<Director> directorList = directorRepository.findAllWithOfficeAndPlaysQuery();
        assertEquals(3, directorList.size());
    }
}
