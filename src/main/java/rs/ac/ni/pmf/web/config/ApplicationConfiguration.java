package rs.ac.ni.pmf.web.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan({ "rs.ac.ni.pmf.web.controller", "rs.ac.ni.pmf.web.model.converter", "rs.ac.ni.pmf.web.provider" })
@Import({ PersistenceConfig.class, SwaggerConfig.class })
public class ApplicationConfiguration {

//	@Bean
//	public IStudentProvider getStudentProvider() {
//		return new DbStudentProvider();
//	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
