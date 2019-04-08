package rs.ac.ni.pmf.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.provider.IStudentProvider;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(path = "/student")
@RequiredArgsConstructor
public class StudentRestController {

	private final IStudentProvider studentProvider;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<StudentDTO> getStudents() {
		return studentProvider.getStudents();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public StudentDTO getStudent(@PathVariable(name = "id") @Min(0) int id) throws ResourceNotFoundException {
		return studentProvider.getStudent(id);
	}

	@RequestMapping(path = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int addStudent(@RequestBody @Valid StudentDTO student) {
		return studentProvider.addStudent(student);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int updateStudent(@PathVariable("id") int id, @RequestBody @Valid StudentDTO student)
			throws ResourceNotFoundException {
		return studentProvider.updateStudent(id, student);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable("id") int id) throws ResourceNotFoundException {
		studentProvider.deleteStudent(id);
	}
}
