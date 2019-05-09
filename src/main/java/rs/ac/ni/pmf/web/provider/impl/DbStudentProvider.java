package rs.ac.ni.pmf.web.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.ResourceType;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.converter.StudentConverter;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.provider.IStudentProvider;
import rs.ac.ni.pmf.web.repository.StudentRepository;
import rs.ac.ni.pmf.web.specification.StudentSearchSpecification;

@Component
public class DbStudentProvider implements IStudentProvider {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentConverter converter;

	@Override
	public List<StudentDTO> getAllStudents() {

		return studentRepository.findAll().stream().map(e -> converter.fromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public StudentDTO getStudent(int id) throws ResourceNotFoundException {

		StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.STUDENT, "Student with id " + id + " not found"));

		return converter.fromEntity(studentEntity);
	}

	@Override
	public int addStudent(StudentDTO student) {
		StudentEntity studentEntity = converter.fromDto(student);
		StudentEntity savedEntity = studentRepository.save(studentEntity);

		return savedEntity.getId();
	}

	@Override
	public int updateStudent(int id, StudentDTO student) throws ResourceNotFoundException {
		StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.STUDENT, "Student with id " + id + " not found"));

		studentEntity.setFirstName(student.getFirstName());
		studentEntity.setLastName(student.getLastName());
		studentEntity.setStudentId(student.getStudentId());

		StudentEntity savedEntity = studentRepository.save(studentEntity);

		return savedEntity.getId();
	}

	@Override
	public void deleteStudent(int id) throws ResourceNotFoundException {
		if (!studentRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.STUDENT, "Student with id " + id + " not found");
		}

		studentRepository.deleteById(id);
	}

	public List<StudentDTO> searchStudents(String firstNameFilter, String lastNameFilter, Integer minEmails) {

		StudentSearchSpecification specification = new StudentSearchSpecification(firstNameFilter, lastNameFilter, minEmails);

		return studentRepository.findAll(specification).stream().map(e -> converter.fromEntity(e))
				.collect(Collectors.toList());
	}
}
