package rs.ac.ni.pmf.web.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.web.model.InfoType;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.model.data.InfoEntity;
import rs.ac.ni.pmf.web.model.data.StudentEntity;
import rs.ac.ni.pmf.web.repository.InfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter
{

	@Autowired
	private InfoRepository infoRepository;

	public StudentDTO fromEntity(final StudentEntity entity)
	{

		List<InfoEntity> infoEntites = infoRepository.findByStudent(entity);

		List<String> phones = new ArrayList<>();
		List<String> emails = new ArrayList<>();

		if (infoEntites != null)
		{
			for (InfoEntity infoEntity : infoEntites)
			{
				switch (infoEntity.getType())
				{
					case TEL:
						phones.add(infoEntity.getValue());
						break;

					case EMAIL:
						emails.add(infoEntity.getValue());
						break;
					default:
						break;
				}
			}
		}

		StudentDTO studentDto = StudentDTO.builder().id(entity.getId()).firstName(entity.getFirstName())
				.lastName(entity.getLastName()).studentId(entity.getStudentId()).emails(emails).phones(phones).build();

		return studentDto;
	}

	public StudentEntity fromDto(final StudentDTO dto)
	{

		List<InfoEntity> infos = new ArrayList<>();

		if (dto.getEmails() != null)
		{
			infos.addAll(dto.getEmails().stream().map(v -> InfoEntity.builder().value(v).type(InfoType.EMAIL).build())
					.collect(Collectors.toList()));
		}

		if (dto.getPhones() != null)
		{
			for (String phone : dto.getPhones())
			{
				InfoEntity entity = InfoEntity.builder().value(phone).type(InfoType.TEL).build();

				infos.add(entity);
			}
		}

		return StudentEntity.builder().id(dto.getId()).firstName(dto.getFirstName()).lastName(dto.getLastName())
				.studentId(dto.getStudentId()).infos(infos).build();
	}

}
