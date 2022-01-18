package ro.ubb.catalog.core.model.metamodel;

import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Performance;
import ro.ubb.catalog.core.model.Play;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Play.class)
public class Play_ {
    public static volatile SingularAttribute<Play, String> playName;
    public static volatile SingularAttribute<Play, Long> duration;
    public static volatile SingularAttribute<Play, Director> director;
    public static volatile SetAttribute<Play, Performance> performances;
}
