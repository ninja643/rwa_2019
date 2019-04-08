package rs.ac.ni.pmf.web.model.api;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class StudentDTO {

	private int id;

	@NotEmpty(message = "firstName cannot be null")
	private String firstName;

	@NotEmpty
	private String lastName;

	private String studentId;
	
	private List<String> phones;
	private List<String> emails;
}
