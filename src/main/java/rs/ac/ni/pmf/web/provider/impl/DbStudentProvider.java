package rs.ac.ni.pmf.web.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.converter.StudentConverter;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.provider.IStudentProvider;
import rs.ac.ni.pmf.web.repository.StudentRepository;

public class DbStudentProvider implements IStudentProvider {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentConverter converter;

	@Override
	public List<StudentDTO> getStudents() {

		return studentRepository.findAll().stream().map(e -> converter.fromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public StudentDTO getStudent(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addStudent(StudentDTO student) {
		StudentEntity savedEntity = studentRepository.save(converter.fromDto(student));

		return savedEntity.getId();
	}

	@Override
	public int updateStudent(int id, StudentDTO student) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteStudent(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub

	}

}
