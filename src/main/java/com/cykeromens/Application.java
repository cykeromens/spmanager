package com.cykeromens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by omens on 7/18/15.
 */
@SpringBootApplication
public class Application implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }

    public static void main(String args[]){
        ApplicationContext context = SpringApplication.run(Application.class);

    }

}
