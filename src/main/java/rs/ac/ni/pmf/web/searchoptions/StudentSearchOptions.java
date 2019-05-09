package rs.ac.ni.pmf.web.searchoptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StudentSearchOptions {
	String firstNameFilter;
	String lastNameFilter;
	Integer minEmailCount;
	int page;
	int count;
}
