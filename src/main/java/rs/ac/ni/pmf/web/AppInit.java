package rs.ac.ni.pmf.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import rs.ac.ni.pmf.web.config.ApplicationConfiguration;

public class AppInit implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ApplicationConfiguration.class);
		
		Dynamic servletRegistration = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		servletRegistration.addMapping("/services/rest/*");
		servletRegistration.setLoadOnStartup(1);
	}

}
