package rs.ac.ni.pmf.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import rs.ac.ni.pmf.web.provider.IStudentProvider;
import rs.ac.ni.pmf.web.provider.impl.DbStudentProvider;

@Configuration
@EnableWebMvc
@ComponentScan({ "rs.ac.ni.pmf.web.controller", "rs.ac.ni.pmf.web.model.converter" })
@Import(PersistenceConfig.class)
public class ApplicationConfiguration {

	@Bean
	public IStudentProvider getStudentProvider() {
		return new DbStudentProvider();
	}
}
