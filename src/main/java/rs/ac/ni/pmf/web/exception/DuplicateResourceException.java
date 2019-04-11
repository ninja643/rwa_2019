package rs.ac.ni.pmf.web.exception;

public class DuplicateResourceException extends Exception {

	private static final long serialVersionUID = 1L;

	private final ResourceType resourceType;

	public DuplicateResourceException(ResourceType resourceType, String message) {
		super(message);

		this.resourceType = resourceType;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}
}
