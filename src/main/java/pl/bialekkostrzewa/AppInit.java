package pl.bialekkostrzewa;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import pl.bialekkostrzewa.configuration.WebConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(ac);


        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");

        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("endcodingFilter", new CharacterEncodingFilter());
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");

        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        filterRegistration.addMappingForUrlPatterns(null, true, "/*");

    }
}