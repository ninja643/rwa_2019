package rs.ac.ni.pmf.web.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.api.ProfessorDTO;
import rs.ac.ni.pmf.web.model.converter.ProfessorConverter;
import rs.ac.ni.pmf.web.model.data.ProfessorEntity;
import rs.ac.ni.pmf.web.repository.ProfessorRepository;

@RestController
@RequestMapping(path = "/professor")
@RequiredArgsConstructor
public class ProfessorRestController {

	private final ProfessorRepository professorRepository;
	private final ProfessorConverter professorConverter;

	@RequestMapping(method = RequestMethod.GET, path = "", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE /*
													 * , MediaType.APPLICATION_XML_VALUE
													 */ })
	public List<ProfessorDTO> getProfessors() {
		return professorConverter.fromEntities(professorRepository.findAll());
	}

	@RequestMapping(method = RequestMethod.POST, path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int saveProfessor(@RequestBody ProfessorDTO professorDTO) {
		ProfessorEntity entity = professorConverter.fromDto(professorDTO, "123");
		
		ProfessorEntity savedEntity = professorRepository.save(entity);
		
		return savedEntity.getId();
	}
}
