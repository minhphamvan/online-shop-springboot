package com.shopspringboot.config;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter{
	
//	@Autowired
//	private org.springframework.context.ApplicationContext applicationContext;
//	
//	@Bean
//	public SpringResourceTemplateResolver templateResolver(){
//	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//	    templateResolver.setApplicationContext(this.applicationContext);
//	    templateResolver.setPrefix("/WEB-INF/templates/");
//	    templateResolver.setSuffix(".html");
//	    templateResolver.setTemplateMode(TemplateMode.HTML);
//	    templateResolver.setCacheable(false);
//	    return templateResolver;
//	}
//	
//	@Bean
//	public SpringTemplateEngine templateEngine(){
//	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//	    templateEngine.setTemplateResolver(templateResolver());
//	    templateEngine.setEnableSpringELCompiler(true);
//	    return templateEngine;
//	}
//	
//	@Bean
//	public ThymeleafViewResolver viewResolver(){
//	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//	    viewResolver.setTemplateEngine(templateEngine());
//	    return viewResolver;
//	}
}
