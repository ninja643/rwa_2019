package rs.ac.ni.pmf.web.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private ResourceType resourceType;

	public ResourceNotFoundException(ResourceType resourceType, String message) {
		super(message);

		this.resourceType = resourceType;
	}
}
