package ro.ubb.catalog.core.model.metamodel;

import ro.ubb.catalog.core.model.Director;
import ro.ubb.catalog.core.model.Office;
import ro.ubb.catalog.core.model.Play;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Director.class)
public class Director_ {

    public static volatile SingularAttribute<Director, String> name;
    public static volatile SingularAttribute<Director, Integer> age;
    public static volatile SingularAttribute<Director, String> gender;
    public static volatile SingularAttribute<Director, Office> office;
    public static volatile ListAttribute<Director, Play> plays;
}
