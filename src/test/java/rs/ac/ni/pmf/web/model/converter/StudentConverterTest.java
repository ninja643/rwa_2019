package rs.ac.ni.pmf.web.model.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.data.StudentEntity;

public class StudentConverterTest {

	private StudentConverter converter = new StudentConverter();

	@Test
	public void shouldConvertEntityToDto() {
		final StudentEntity entity = StudentEntity.builder().id(1).firstName("Pera").lastName("Petrovic")
				.studentId("1234-RN").build();
		final StudentDTO expected = StudentDTO.builder().id(1).firstName("Pera").lastName("Petrovic").build();

		final StudentDTO actual = converter.fromEntity(entity);

		assertThat(actual).isEqualTo(expected);
	}
}
