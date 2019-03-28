package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.data.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
	
	List<StudentEntity> findAll();
}
