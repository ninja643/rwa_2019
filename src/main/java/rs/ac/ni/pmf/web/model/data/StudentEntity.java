package rs.ac.ni.pmf.web.model.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(unique = true, nullable = true)
	String studentId;

	@OneToMany(cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY,
			mappedBy = "student")
	List<InfoEntity> infos;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
	List<CourseEntity> courses;
}
