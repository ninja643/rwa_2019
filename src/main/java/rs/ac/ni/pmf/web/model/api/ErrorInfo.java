package rs.ac.ni.pmf.web.model.api;

import lombok.Value;

@Value
public class ErrorInfo {
	
	public enum ErrorCode {
		STUDENT_NOT_FOUND,
		GENERAL_ERROR
	};
	
	private ErrorCode errorCode;
	private String message;
}
