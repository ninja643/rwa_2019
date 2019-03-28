package rs.ac.ni.pmf.web.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.ResourceType;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.provider.IStudentProvider;

public class MemoryStudentProvider implements IStudentProvider {

	private static final AtomicInteger idGenerator = new AtomicInteger();

	private final Map<Integer, StudentDTO> students = new HashMap<>();

	public MemoryStudentProvider() {
		students.put(1, new StudentDTO(idGenerator.incrementAndGet(), "Pera", "Perić"));
		students.put(2, new StudentDTO(idGenerator.incrementAndGet(), "Đoka", "Đokić"));
	}

	@Override
	public List<StudentDTO> getStudents() {
		return new ArrayList<>(students.values());
	}

	@Override
	public StudentDTO getStudent(int id) throws ResourceNotFoundException {
		StudentDTO result = students.get(id);

		if (result == null) {
			throw new ResourceNotFoundException(ResourceType.STUDENT, "Student with id " + id + " does not exist");
		}

		return result;
	}

	@Override
	public int addStudent(StudentDTO student) {
		int generatedId = idGenerator.incrementAndGet();

		students.put(generatedId, new StudentDTO(generatedId, student.getFirstName(), student.getLastName()));

		return generatedId;
	}

	@Override
	public int updateStudent(int id, StudentDTO student) throws ResourceNotFoundException {

		if (!students.containsKey(id)) {
			throw new ResourceNotFoundException(ResourceType.STUDENT, "Student with id " + id + " does not exist");
		}

		final StudentDTO updated = new StudentDTO(id, student.getFirstName(), student.getLastName());
		students.put(id, updated);

		return id;
	}

	@Override
	public void deleteStudent(int id) throws ResourceNotFoundException {

		if (!students.containsKey(id)) {
			throw new ResourceNotFoundException(ResourceType.STUDENT, "Student with id " + id + " does not exist");
		}

		students.remove(id);
	}
}
