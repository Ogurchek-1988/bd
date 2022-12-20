package ru.portal.semusadba.jsf;

import com.sun.faces.config.ConfigureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

@Configuration
@ComponentScan(basePackages = {"ru.portal.semusadba"})

public class JsfContextInitializer implements ServletContextAware {

    private final ServletRegistrationBean servletRegistrationBean;

    @Autowired
    public JsfContextInitializer(ServletRegistrationBean servletRegistrationBean) {
        this.servletRegistrationBean = servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(servlet, "*.xhtml", "*.jsf");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(
                new ConfigureListener()
        );
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        return;
    }
}