package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Play;

import java.util.List;

public interface PlayService {
    List<Play> getAllPlays();
    Play getPlay(Long id);
    Play addPlay(Play play);
    void deletePlay(Long id);
    Play updatePlay(Play play);
    List<Play> filterPlaysByDuration(Long duration);
    List<Play> sortPlaysOnDuration();
    List<Play> sortPlaysOnDurationDir(String dir);
    Play getTheLongestPLay();

    List<Play> getAllPlaysReport();
}
