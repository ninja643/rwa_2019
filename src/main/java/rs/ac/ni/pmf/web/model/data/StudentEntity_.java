package rs.ac.ni.pmf.web.model.data;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(StudentEntity.class)
public class StudentEntity_ {

	public static volatile SingularAttribute<StudentEntity, Integer> id;
	public static volatile SingularAttribute<StudentEntity, String> firstName;
	public static volatile SingularAttribute<StudentEntity, String> lastName;
	public static volatile SingularAttribute<StudentEntity, String> studentId;
	
	public static volatile ListAttribute<StudentEntity, InfoEntity> infos;
	public static volatile ListAttribute<StudentEntity, CourseEntity> courses;
}