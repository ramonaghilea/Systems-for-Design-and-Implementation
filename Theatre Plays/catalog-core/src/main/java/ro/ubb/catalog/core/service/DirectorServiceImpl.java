package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.repository.DirectorRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

  @Override
  public Director getDirector(Long id) {
    log.trace(" > getDirector - method was entered.");
//    Play play = this.playRepository.findById(id);
    Director director = this.directorRepository.findAll()
            .stream()
            .filter(d -> d.getId().equals(id))
            .collect(Collectors.toList())
            .get(0);
    log.trace(" > getDirector - result = {}", director);
    return director;
  }

  @Override
  public void deleteDirector(Long id) {
    log.trace(" > deleteDirector - method was entered. id = {}", id);
    this.directorRepository.deleteById(id);
    log.trace(" > deleteDirector - method was exited.");
  }

  @Override
  public List<Director> getAllDirectorsReport() {
    log.trace(" > getAllDirectorsReport - method was entered.");
    List<Director> directors =  this.directorRepository.findAll();

    List<Director> result = directors.stream()
            .sorted(Comparator.comparingInt(Director::getNumberPlays)
                    .reversed())
            .collect(Collectors.toList());

    log.trace(" > getAllDirectorsReport - result = {}", result);
    return result;
  }
}
