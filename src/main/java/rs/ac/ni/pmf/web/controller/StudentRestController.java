package rs.ac.ni.pmf.web.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.StudentDTO;
import rs.ac.ni.pmf.web.provider.impl.DbStudentProvider;

@RestController
@RequestMapping(path = "/student")
@RequiredArgsConstructor
public class StudentRestController {

	private final DbStudentProvider studentProvider;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<StudentDTO> getStudents() {
		return studentProvider.getAllStudents();
	}

	@RequestMapping(path = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<StudentDTO> searchStudents(@RequestParam(name = "firstName", required = false) String firstNameFilter,
			@RequestParam(name = "lastName", required = false) String lastNameFilter) {
		return studentProvider.searchStudents(firstNameFilter, lastNameFilter);
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
