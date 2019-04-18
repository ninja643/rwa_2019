package rs.ac.ni.pmf.web.provider;

import java.util.List;

import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.StudentDTO;

public interface IStudentProvider {

	public List<StudentDTO> getAllStudents();
	
	public StudentDTO getStudent(int id) throws ResourceNotFoundException;
	
	public int addStudent(StudentDTO student);
	
	public int updateStudent(int id, StudentDTO student) throws ResourceNotFoundException;
	
	public void deleteStudent(int id) throws ResourceNotFoundException;
}
