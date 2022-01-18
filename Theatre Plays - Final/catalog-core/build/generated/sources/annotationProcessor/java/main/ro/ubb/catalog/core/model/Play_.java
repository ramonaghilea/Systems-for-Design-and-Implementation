package ro.ubb.catalog.core.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Play.class)
public abstract class Play_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Play, Long> duration;
	public static volatile SingularAttribute<Play, Director> director;
	public static volatile SingularAttribute<Play, String> playName;
	public static volatile SetAttribute<Play, Performance> performances;

	public static final String DURATION = "duration";
	public static final String DIRECTOR = "director";
	public static final String PLAY_NAME = "playName";
	public static final String PERFORMANCES = "performances";

}

