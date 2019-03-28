package rs.ac.ni.pmf.web.model.converter;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.data.StudentEntity;

@Component
public class StudentConverter {

	public StudentDTO fromEntity(final StudentEntity entity) {
		return StudentDTO.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName())
				.studentId(entity.getStudentId()).build();
	}

	public StudentEntity fromDto(final StudentDTO dto) {
		return StudentEntity.builder().id(dto.getId()).firstName(dto.getFirstName()).lastName(dto.getLastName())
				.studentId(dto.getStudentId()).build();
	}

}
