package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Props;

import java.util.List;

public interface PropsService {
    List<Props> getAllProps();
    Props getProps(Long id);
    Props addProps(Props props);
}
