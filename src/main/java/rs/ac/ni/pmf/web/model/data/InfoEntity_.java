package rs.ac.ni.pmf.web.model.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import rs.ac.ni.pmf.web.model.InfoType;

@StaticMetamodel(InfoEntity.class)
public class InfoEntity_ {

	public static volatile SingularAttribute<InfoEntity, Integer> id;
	public static volatile SingularAttribute<InfoEntity, InfoType> type;
	public static volatile SingularAttribute<InfoEntity, String> value;

	public static volatile SingularAttribute<InfoEntity, StudentEntity> student;
}
