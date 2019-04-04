package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.data.InfoEntity;
import rs.ac.ni.pmf.web.model.data.StudentEntity;

@Repository
public interface InfoRepository extends CrudRepository<InfoEntity, Integer> {

	List<InfoEntity> findByStudent(StudentEntity studentEntity);
}
