package ro.ubb.catalog.core.model.metamodel;

import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Performance;
import ro.ubb.catalog.core.model.Play;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Performance.class)
public class Performance_ {
    public static volatile SingularAttribute<Performance, Play> play;
    public static volatile SingularAttribute<Performance, Actor> actor;
    public static volatile SingularAttribute<Performance, String> role;
}
