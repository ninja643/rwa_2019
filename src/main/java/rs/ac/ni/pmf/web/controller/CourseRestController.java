package rs.ac.ni.pmf.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.ResourceType;
import rs.ac.ni.pmf.web.model.api.CourseDTO;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.converter.CourseConverter;
import rs.ac.ni.pmf.web.model.converter.StudentConverter;
import rs.ac.ni.pmf.web.model.data.CourseEntity;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.repository.CourseRepository;
import rs.ac.ni.pmf.web.repository.StudentRepository;

@RestController
@RequestMapping(path = "/course")
@RequiredArgsConstructor
public class CourseRestController {

	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;

	private final CourseConverter courseConverter;
	private final StudentConverter studentConverter;

	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CourseDTO> getAll() {
		List<CourseEntity> entities = courseRepository.findAll();

		return entities.stream().map(e -> courseConverter.fromEntity(e)).collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CourseDTO getById(@PathVariable(name = "id") int id) throws ResourceNotFoundException {

		final CourseEntity courseEntity = courseRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.COURSE, "Course with id " + id + " does not exist"));

		return courseConverter.fromEntity(courseEntity);
	}

	@RequestMapping(method = RequestMethod.POST, path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int saveCourse(@RequestBody @Valid CourseDTO courseDTO) {
		final CourseEntity courseEntity = courseConverter.fromDto(courseDTO);
		final CourseEntity savedCourse = courseRepository.save(courseEntity);

		return savedCourse.getId();
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int updateCourse(@PathVariable(name = "id") int id, @RequestBody @Valid CourseDTO courseDTO)
			throws ResourceNotFoundException {

		final CourseEntity courseEntity = courseRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.COURSE, "Course with id " + id + " does not exist"));

		courseEntity.setName(courseDTO.getName());

		final CourseEntity updatedCourse = courseRepository.save(courseEntity);

		return updatedCourse.getId();
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCourse(@PathVariable(name = "id") int id) {
		courseRepository.deleteById(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{courseId}/student")
	@Transactional
	public List<StudentDTO> getCourseStudents(@PathVariable(name = "courseId") int courseId)
			throws ResourceNotFoundException {
		final CourseEntity courseEntity = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COURSE,
						"Course with id " + courseId + " does not exist"));

		return courseEntity.getStudents().stream().map(s -> studentConverter.fromEntity(s))
				.collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{courseId}/student/{studentId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	public int addStudentToCourse(@PathVariable(name = "courseId") int courseId,
			@PathVariable(name = "studentId") int studentId)
			throws ResourceNotFoundException, DuplicateResourceException {
		final CourseEntity courseEntity = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COURSE,
						"Course with id " + courseId + " does not exist"));

		final StudentEntity studentEntity = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.STUDENT,
						"Course with id " + studentId + " does not exist"));

		if (courseEntity.getStudents().contains(studentEntity)) {
			throw new DuplicateResourceException(ResourceType.STUDENT_COURSE,
					"Student " + studentId + " is already enlisted for course " + courseId);
		}

		courseEntity.getStudents().add(studentEntity);

		courseRepository.save(courseEntity);

		return courseEntity.getId();
	}
}
