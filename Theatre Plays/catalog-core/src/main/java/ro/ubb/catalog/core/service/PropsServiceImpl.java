package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Play;
import ro.ubb.catalog.core.model.Props;
import ro.ubb.catalog.core.repository.PropsRepository;

import java.util.List;

@Service
public class PropsServiceImpl implements PropsService{
    public static final Logger log = LoggerFactory.getLogger(PropsServiceImpl.class);

    @Autowired
    private PropsRepository propsRepository;

    @Override
    public List<Props> getAllProps() {
        return null;
    }

    @Override
    public Props getProps(Long id) {
        return null;
    }

    @Override
    public Props addProps(Props props) {
        log.trace(" > addProps - method was entered. props = {}", props);
        Props result = this.propsRepository.save(props);
        log.trace(" > addProps - method was finished.");
        return result;
    }
}
