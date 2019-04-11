package rs.ac.ni.pmf.web.model.converter;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.CourseDTO;
import rs.ac.ni.pmf.web.model.data.CourseEntity;

@Component
public class CourseConverter {

	public CourseDTO fromEntity(final CourseEntity entity) {
		return CourseDTO.builder().id(entity.getId()).name(entity.getName()).build();
	}
	
	public CourseEntity fromDto(final CourseDTO dto) {
		return CourseEntity.builder().id(dto.getId()).name(dto.getName()).build();
	}
}
