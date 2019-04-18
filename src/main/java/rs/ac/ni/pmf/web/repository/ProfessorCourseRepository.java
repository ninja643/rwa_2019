package rs.ac.ni.pmf.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.data.ProfessorCourseEntity;
import rs.ac.ni.pmf.web.model.data.ProfessorCourseId;

@Repository
public interface ProfessorCourseRepository extends CrudRepository<ProfessorCourseEntity, ProfessorCourseId> {

}
