package rs.ac.ni.pmf.web.controller;

import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.ErrorInfo;
import rs.ac.ni.pmf.web.model.api.ErrorInfo.ErrorCode;

@ControllerAdvice
@ResponseBody
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorInfo handleResourceNotFound(ResourceNotFoundException e) {

		ErrorCode errorCode;

		switch (e.getResourceType()) {
		case STUDENT:
			errorCode = ErrorCode.STUDENT_NOT_FOUND;
			break;
		default:
			errorCode = ErrorCode.GENERAL_ERROR;
			break;
		}

		return new ErrorInfo(errorCode, e.getMessage());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo handlePersistenceException(DataIntegrityViolationException e) {
		return new ErrorInfo(ErrorCode.DATABASE_ERROR, e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo handleGeneralException(Exception e) {
		return new ErrorInfo(ErrorCode.GENERAL_ERROR, e.getMessage());
	}
}
