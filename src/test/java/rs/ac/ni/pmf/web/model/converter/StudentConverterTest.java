package rs.ac.ni.pmf.web.model.converter;

import static org.assertj.core.api.Assertions.assertThat;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;

import rs.ac.ni.pmf.web.model.InfoType;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.data.InfoEntity;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.repository.InfoRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentConverterTest
{

	@Injectable
	private InfoRepository infoRepository;

	@Tested
	private StudentConverter converter;

	@Test
	public void shouldConvertEntityToDto(@Injectable("email") String email, @Injectable("phone") String phone)
	{
		List<InfoEntity> infoEntites = Arrays.asList(InfoEntity.builder().type(InfoType.EMAIL).value(email).build(),
				InfoEntity.builder().type(InfoType.TEL).value(phone).build());

		final StudentEntity entity = StudentEntity.builder().id(1).firstName("Pera").lastName("Petrovic")
				.studentId("1234-RN").build();
		final StudentDTO expected = StudentDTO.builder()
				.id(1)
				.firstName("Pera")
				.lastName("Petrovic")
				.studentId("1234-RN")
				.emails(Arrays.asList(email))
				.phones(Arrays.asList(phone))
				.build();

		new Expectations()
		{
			{
				infoRepository.findByStudent(entity);
				result = infoEntites;
			}
		};

		final StudentDTO actual = converter.fromEntity(entity);

		assertThat(actual).isEqualTo(expected);
	}
}
