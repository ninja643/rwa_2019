package rs.ac.ni.pmf.web.model.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import rs.ac.ni.pmf.web.model.api.ProfessorDTO;
import rs.ac.ni.pmf.web.model.data.ProfessorEntity;

public class ProfessorConverterTest {

	@Test
	public void shouldConvertToDto() {
		final ProfessorEntity entity = ProfessorEntity.builder().id(1).firstName("Tasa").lastName("Tasic")
				.password("123").username("tasa").build();
		final ProfessorDTO expected = ProfessorDTO.builder().id(1).firstName("Tasa").lastName("Tasic").username("tasa")
				.build();

		final ModelMapper mapper = new ModelMapper();

		final ProfessorDTO dto = mapper.map(entity, ProfessorDTO.class);

		assertThat(dto).isEqualTo(expected);
	}
}
