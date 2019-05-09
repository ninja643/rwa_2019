package rs.ac.ni.pmf.web.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Value;
import rs.ac.ni.pmf.web.model.InfoType;
import rs.ac.ni.pmf.web.model.data.InfoEntity_;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.model.data.StudentEntity_;

@Value
public class StudentSearchSpecification implements Specification<StudentEntity> {

	private static final long serialVersionUID = 1L;

	private final String firstNameFilter;
	private final String lastNameFilter;
	private final Integer minEmailCount;

	@Override
	public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<>();

		Path<String> firstName = root.get(StudentEntity_.firstName);
		Path<String> lastName = root.get(StudentEntity_.lastName);

		if ((firstNameFilter != null) && !firstNameFilter.isEmpty()) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(firstName), firstNameFilter.toLowerCase() + "%"));
		}

		if ((lastNameFilter != null) && !lastNameFilter.isEmpty()) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(lastName), lastNameFilter.toLowerCase() + "%"));
		}

		if (minEmailCount != null) {
			Path<InfoType> type = root.join(StudentEntity_.infos).get(InfoEntity_.type);

			predicates.add(criteriaBuilder.equal(type, InfoType.EMAIL));

			query.groupBy(root.get(StudentEntity_.id));
			query.having(criteriaBuilder.ge(criteriaBuilder.count(type), minEmailCount));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
