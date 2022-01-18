package ro.ubb.catalog.core.model.metamodel;

import ro.ubb.catalog.core.model.Actor;
import ro.ubb.catalog.core.model.Performance;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Actor.class)
public class Actor_ {
    public static volatile SingularAttribute<Actor, String> name;
    public static volatile SingularAttribute<Actor, Integer> age;
    public static volatile SingularAttribute<Actor, String> gender;
    public static volatile SetAttribute<Actor, Performance> performances;
}
