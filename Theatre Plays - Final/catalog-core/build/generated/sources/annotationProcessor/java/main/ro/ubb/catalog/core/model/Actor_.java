package ro.ubb.catalog.core.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Actor.class)
public abstract class Actor_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Actor, String> gender;
	public static volatile SingularAttribute<Actor, String> name;
	public static volatile SetAttribute<Actor, Performance> performances;
	public static volatile SingularAttribute<Actor, Integer> age;

	public static final String GENDER = "gender";
	public static final String NAME = "name";
	public static final String PERFORMANCES = "performances";
	public static final String AGE = "age";

}

