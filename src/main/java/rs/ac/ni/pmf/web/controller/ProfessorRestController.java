package rs.ac.ni.pmf.web.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.ProfessorDTO;
import rs.ac.ni.pmf.web.model.converter.ProfessorConverter;
import rs.ac.ni.pmf.web.model.data.ProfessorCourseEntity;
import rs.ac.ni.pmf.web.model.data.ProfessorCourseEntity.ProfessorCourseId;
import rs.ac.ni.pmf.web.model.data.ProfessorEntity;
import rs.ac.ni.pmf.web.provider.impl.CommonProvider;
import rs.ac.ni.pmf.web.repository.ProfessorCourseRepository;
import rs.ac.ni.pmf.web.repository.ProfessorRepository;

@RestController
@RequestMapping(path = "/professor")
@RequiredArgsConstructor
public class ProfessorRestController {

	private final CommonProvider commonProvider;

	private final ProfessorRepository professorRepository;
	private final ProfessorConverter professorConverter;

	private final ProfessorCourseRepository professorCourseRepository;

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

	@RequestMapping(method = RequestMethod.PUT, path = "/{professorId}/course/{courseId}")
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addCourseToProfessorForYear(@PathVariable(name = "professorId", required = true) int professorId,
			@PathVariable(name = "courseId", required = true) int courseId,
			@RequestParam(name = "year", required = true) @NotEmpty String year) throws ResourceNotFoundException {

		ProfessorCourseId pcId = ProfessorCourseId.builder().courseId(courseId).professorId(professorId)
				.year(year).build();
		
		ProfessorCourseEntity pce = ProfessorCourseEntity.builder().id(pcId).build();

		professorCourseRepository.save(pce);
	}
}
