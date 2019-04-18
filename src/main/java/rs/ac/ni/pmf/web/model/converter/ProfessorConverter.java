package rs.ac.ni.pmf.web.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.api.ProfessorDTO;
import rs.ac.ni.pmf.web.model.data.ProfessorEntity;

@Component
@RequiredArgsConstructor
public class ProfessorConverter {

	private final ModelMapper modelMapper;

	public ProfessorDTO fromEntity(ProfessorEntity entity) {
		return modelMapper.map(entity, ProfessorDTO.class);
	}

	public List<ProfessorDTO> fromEntities(List<ProfessorEntity> entities) {

		return entities.stream().map(e -> fromEntity(e)).collect(Collectors.toList());
	}

	public ProfessorEntity fromDto(ProfessorDTO dto, String password) {
		ProfessorEntity entity = modelMapper.map(dto, ProfessorEntity.class);
		entity.setPassword(password);

		return entity;
	}
}
