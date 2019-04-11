package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.data.CourseEntity;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

	List<CourseEntity> findAll();
}
