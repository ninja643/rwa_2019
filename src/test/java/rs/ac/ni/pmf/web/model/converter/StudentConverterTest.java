package rs.ac.ni.pmf.web.model.converter;

import static org.assertj.core.api.Assertions.assertThat;

import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;

import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.repository.InfoRepository;

import java.util.Collections;

public class StudentConverterTest
{

	@Injectable
	private InfoRepository infoRepository;

	@Tested
	private StudentConverter converter;

	@Test
	public void shouldConvertEntityToDto()
	{
		final StudentEntity entity = StudentEntity.builder().id(1).firstName("Pera").lastName("Petrovic")
				.studentId("1234-RN").build();
		final StudentDTO expected = StudentDTO.builder()
				.id(1)
				.firstName("Pera")
				.lastName("Petrovic")
				.studentId("1234-RN")
				.emails(Collections.emptyList())
				.phones(Collections.emptyList())
				.build();

		final StudentDTO actual = converter.fromEntity(entity);

		assertThat(actual).isEqualTo(expected);
	}
}
