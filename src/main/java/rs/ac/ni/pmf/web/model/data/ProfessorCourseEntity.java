package rs.ac.ni.pmf.web.model.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "professors_courses")
public class ProfessorCourseEntity {

	@EmbeddedId
	ProfessorCourseId id;
	
	@ManyToOne
	@JoinColumn(name = "professorId", insertable = false, updatable = false)
	ProfessorEntity professor;
	
	@ManyToOne
	@JoinColumn(name = "courseId", insertable = false, updatable = false)
	CourseEntity course;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@Embeddable
	public static class ProfessorCourseId implements Serializable {
		
		private static final long serialVersionUID = -8383088442712800560L;

		@Column
		int professorId;
		
		@Column
		int courseId;
		
		@Column
		String year;
	}
}
