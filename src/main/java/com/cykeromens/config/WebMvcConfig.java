package com.cykeromens.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Created by omens on 7/18/15.
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("pages/home");
        registry.addViewController("/").setViewName("pages/home");
        registry.addViewController("/about").setViewName("pages/about");
        registry.addViewController("/contact").setViewName("pages/contact");
        registry.addViewController("/login").setViewName("pages/login");
        registry.addViewController("/login?error").setViewName("pages/login");
        registry.addViewController("/logout");
        //registry.addViewController("/users/list").setViewName("users/list");


        registry.addViewController("/employee/create").setViewName("/employee/edit");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return super.getMessageCodesResolver();
    }


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new       ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);// # -1 : never reload, 0 always reload
        return messageSource;
    }

//    @Bean
//    public TemplateResolver templateResolver(){
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setTemplateMode("HTML5");
//        templateResolver.setPrefix("classpath:/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setCacheTTLMs(3600000L);
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine springTemplateEngine(){
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        return templateEngine;
//    }
//
//    @Bean
//    public ViewResolver viewResolver(){
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setOrder(1);
//        viewResolver.setTemplateEngine(springTemplateEngine());
//        return viewResolver;
//    }


//    @Bean
//    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine);
//        return viewResolver;
//    }
//
//   @Bean
//    public TemplateEngine templateEngine(TemplateResolver templateResolver) {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        //templateEngine.addDialect(new SpringSecurityDialect());
//        return templateEngine;
//    }
//
//    @Bean
//    public TemplateResolver templateResolver() {
//        TemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setOrder(1);
//        templateResolver.setPrefix("classpath:templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        templateResolver.setCacheTTLMs(3600000L);
//        return templateResolver;
//    }

}
