package ro.ubb.catalog.core.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Director.class)
public abstract class Director_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile ListAttribute<Director, Play> plays;
	public static volatile SingularAttribute<Director, String> gender;
	public static volatile SingularAttribute<Director, String> name;
	public static volatile SingularAttribute<Director, Office> office;
	public static volatile SingularAttribute<Director, Integer> age;

	public static final String PLAYS = "plays";
	public static final String GENDER = "gender";
	public static final String NAME = "name";
	public static final String OFFICE = "office";
	public static final String AGE = "age";

}

