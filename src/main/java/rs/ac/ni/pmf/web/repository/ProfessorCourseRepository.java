package rs.ac.ni.pmf.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.data.ProfessorCourseEntity;

@Repository
public interface ProfessorCourseRepository
		extends CrudRepository<ProfessorCourseEntity, ProfessorCourseEntity.ProfessorCourseId> {

}
