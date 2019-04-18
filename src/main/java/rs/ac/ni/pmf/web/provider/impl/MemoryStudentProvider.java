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

		students.put(1, StudentDTO.builder().id(idGenerator.incrementAndGet()).firstName("Pera").lastName("Perić")
				.studentId("1").build());
		students.put(2, StudentDTO.builder().id(idGenerator.incrementAndGet()).firstName("Djoka").lastName("Djokic")
				.studentId("2").build());
	}

	@Override
	public List<StudentDTO> getAllStudents() {
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

		students.put(generatedId, StudentDTO.builder().id(generatedId).firstName(student.getFirstName())
				.lastName(student.getLastName()).studentId(student.getStudentId()).build());

		return generatedId;
	}

	@Override
	public int updateStudent(int id, StudentDTO student) throws ResourceNotFoundException {

		if (!students.containsKey(id)) {
			throw new ResourceNotFoundException(ResourceType.STUDENT, "Student with id " + id + " does not exist");
		}

		final StudentDTO updated = StudentDTO.builder().id(student.getId()).firstName(student.getFirstName())
				.lastName(student.getLastName()).studentId(student.getStudentId()).build();
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
