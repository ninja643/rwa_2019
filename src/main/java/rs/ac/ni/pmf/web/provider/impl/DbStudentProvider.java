package rs.ac.ni.pmf.web.provider.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.ResourceType;
import rs.ac.ni.pmf.web.model.InfoType;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.api.StudentLiteDTO;
import rs.ac.ni.pmf.web.model.converter.StudentConverter;
import rs.ac.ni.pmf.web.model.data.InfoEntity;
import rs.ac.ni.pmf.web.model.data.InfoEntity_;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.model.data.StudentEntity_;
import rs.ac.ni.pmf.web.provider.IStudentProvider;
import rs.ac.ni.pmf.web.repository.InfoRepository;
import rs.ac.ni.pmf.web.repository.StudentRepository;
import rs.ac.ni.pmf.web.searchoptions.StudentSearchOptions;
import rs.ac.ni.pmf.web.specification.InfoByCourseSpecification;
import rs.ac.ni.pmf.web.specification.StudentSearchSpecification;

@Component
public class DbStudentProvider implements IStudentProvider {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private InfoRepository infoRepository;

	@Autowired
	private StudentConverter converter;
	
	@Autowired
	private EntityManager em;

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

	public List<StudentDTO> searchStudents(final StudentSearchOptions searchOptions) {

		StudentSearchSpecification specification = new StudentSearchSpecification(searchOptions);

		return studentRepository
				.findAll(specification, PageRequest.of(searchOptions.getPage(), searchOptions.getCount())).stream()
				.map(e -> converter.fromEntity(e)).collect(Collectors.toList());
	}

	public List<String> getEmailsByCourse(int courseId) {

		final Pageable page = PageRequest.of(1, 2);

		Page<InfoEntity> result = infoRepository.findAll(new InfoByCourseSpecification(courseId), page);

		return result.stream().map(e -> e.getValue()).collect(Collectors.toList());
	}
	
	public List<StudentLiteDTO> getStudentLiteData() {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		final CriteriaQuery<Object> query = cb.createQuery();
		
		final Root<StudentEntity> root = query.from(StudentEntity.class);
		
		final Path<String> firstName = root.get(StudentEntity_.firstName);
		final Path<String> lastName = root.get(StudentEntity_.lastName);
		
		ListJoin<StudentEntity, InfoEntity> join = root.join(StudentEntity_.infos);
		
		final Path<InfoType> infoType = join.get(InfoEntity_.type); 
		final Path<String> info = join.get(InfoEntity_.value);
		
		query.where(cb.equal(infoType, InfoType.EMAIL));
		
		query.multiselect(firstName, lastName, info);
		
		List<Object> results = em.createQuery(query).getResultList();

		return results.stream().map(row -> parseRow(row)).collect(Collectors.toList());
	}
	
	private StudentLiteDTO parseRow(Object row) {
		Object[] items = (Object[]) row;
		
		final String name = (String)items[0] + " " + (String)items[1];
		final String info = (String)items[2];
		
		return new StudentLiteDTO(name, info);
	}
}
