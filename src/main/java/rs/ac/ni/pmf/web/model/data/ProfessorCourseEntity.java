package rs.ac.ni.pmf.web.model.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(ProfessorCourseId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "professors_courses")
public class ProfessorCourseEntity {

	@Id
	int professorId;
	
	@Id
	int courseId;
	
	String year;

	@ManyToOne
	@JoinColumn(name = "professorId", insertable = false, updatable = false)
	ProfessorEntity professor;
	
	@ManyToOne
	@JoinColumn(name = "courseId", insertable = false, updatable = false)
	CourseEntity course;
}
