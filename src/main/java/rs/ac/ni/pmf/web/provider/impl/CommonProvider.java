package rs.ac.ni.pmf.web.provider.impl;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.ResourceType;
import rs.ac.ni.pmf.web.model.converter.CourseConverter;
import rs.ac.ni.pmf.web.model.converter.ProfessorConverter;
import rs.ac.ni.pmf.web.model.converter.StudentConverter;
import rs.ac.ni.pmf.web.model.data.CourseEntity;
import rs.ac.ni.pmf.web.model.data.ProfessorEntity;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.repository.CourseRepository;
import rs.ac.ni.pmf.web.repository.ProfessorRepository;
import rs.ac.ni.pmf.web.repository.StudentRepository;

@Component
@RequiredArgsConstructor
public class CommonProvider {

	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;

	private final CourseConverter courseConverter;
	private final StudentConverter studentConverter;
	private final ProfessorConverter professorConverter;

	public CourseEntity getCourse(int id) throws ResourceNotFoundException {
		return courseRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.COURSE, "Course with id " + id + " does not exist"));
	}

	public StudentEntity getStudent(int id) throws ResourceNotFoundException {
		return studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.STUDENT, "Student with id " + id + " does not exist"));
	}
	
	public ProfessorEntity getProfessor(int id) throws ResourceNotFoundException {
		return professorRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.PROFESSOR, "Professor with id " + id + " does not exist"));
	}
}
