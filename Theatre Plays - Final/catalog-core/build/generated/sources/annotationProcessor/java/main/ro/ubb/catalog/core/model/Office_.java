package ro.ubb.catalog.core.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Office.class)
public abstract class Office_ extends ro.ubb.catalog.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Office, String> address;
	public static volatile SingularAttribute<Office, Director> director;
	public static volatile SingularAttribute<Office, String> officeNumber;

	public static final String ADDRESS = "address";
	public static final String DIRECTOR = "director";
	public static final String OFFICE_NUMBER = "officeNumber";

}

