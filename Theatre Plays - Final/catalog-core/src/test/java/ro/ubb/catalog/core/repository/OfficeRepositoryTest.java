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
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.repository.office.OfficeRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF.dbtest/db-data.xml")
public class OfficeRepositoryTest {
    @Autowired
    private OfficeRepository officeRepository;

    @Test
    public void findAllWithDirectorQuery() {
        List<Office> officeList = officeRepository.findAllWithDirectorQuery();
        assertEquals(2, officeList.size());
    }

    @Test
    public void findAllWithDirectorAndPlaysQuery() {
        List<Office> officeList = officeRepository.findAllWithDirectorAndPlaysQuery();
        assertEquals(2, officeList.size());
    }

    @Test
    public void findAllWithDirector() {
        List<Office> officeList = officeRepository.findAllWithDirector();
        assertEquals(2, officeList.size());
    }

    @Test
    public void findAllWithDirectorAndPlays() {
        List<Office> officeList = officeRepository.findAllWithDirectorAndPlays();
        assertEquals(2, officeList.size());
    }
}
