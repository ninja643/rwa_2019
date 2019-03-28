package rs.ac.ni.pmf.web.model.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String firstName;

	String lastName;

	String studentId;
}
