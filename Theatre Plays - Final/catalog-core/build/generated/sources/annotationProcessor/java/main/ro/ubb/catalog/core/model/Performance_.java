package ro.ubb.catalog.core.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Performance.class)
public abstract class Performance_ {

	public static volatile SingularAttribute<Performance, Play> play;
	public static volatile SingularAttribute<Performance, Actor> actor;
	public static volatile SingularAttribute<Performance, String> role;

	public static final String PLAY = "play";
	public static final String ACTOR = "actor";
	public static final String ROLE = "role";

}

