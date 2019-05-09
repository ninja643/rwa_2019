package rs.ac.ni.pmf.web.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.InfoType;
import rs.ac.ni.pmf.web.model.data.CourseEntity_;
import rs.ac.ni.pmf.web.model.data.InfoEntity;
import rs.ac.ni.pmf.web.model.data.InfoEntity_;
import rs.ac.ni.pmf.web.model.data.StudentEntity_;

@RequiredArgsConstructor

public class InfoByCourseSpecification implements Specification<InfoEntity> {

	private static final long serialVersionUID = 1L;

	private final Integer id;

	@Override
	public Predicate toPredicate(Root<InfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		final Path<InfoType> type = root.get(InfoEntity_.type);
		final Path<Integer> courseId = root.join(InfoEntity_.student).join(StudentEntity_.courses)
				.get(CourseEntity_.id);

		return cb.and(cb.equal(type, InfoType.EMAIL), cb.equal(courseId, id));
	}

}
