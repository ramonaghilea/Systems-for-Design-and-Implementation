package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.repository.DirectorRepository;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService{
  public static final Logger log = LoggerFactory.getLogger(DirectorServiceImpl.class);

  @Autowired
  private DirectorRepository directorRepository;

  @Override
  public Director addDirector(Director director) {
    log.trace(" > addDirector - method was entered. actor = {}", director);
//    validator.validate(director);
    Director result = this.directorRepository.save(director);
    log.trace(" > addDirector - method was finished.");
    return result;
  }

  @Override
  public List<Director> getAllDirectors() {
    log.trace(" > getAllDirectors - method was entered.");
    List<Director> directors =  this.directorRepository.findAll();
    log.trace(" > getAllDirectors - result = {}", directors);
    return directors;
  }
}
