package rs.ac.ni.pmf.web.model.data;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorCourseId implements Serializable {
	
	private static final long serialVersionUID = -8383088442712800560L;

	@Column
	int professorId;
	
	@Column
	int courseId;
}
