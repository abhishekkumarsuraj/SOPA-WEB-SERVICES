package com.suraj.soap.webservices.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable webServices
@EnableWs
//Spring Configuration
@Configuration
public class WebServiceConfig {

	//Message Dispatcher Servlet
	//ApplicationContext
	
	//url -> /ws/*
	
	@Bean
	ServletRegistrationBean messageDispatcherServlet(ApplicationContext context)
	{
		//MessageDispatcherServlet "/ws/*"
		
		MessageDispatcherServlet messageDispatcherServlet= new
				MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);		
		return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");
		
	}
	
	// /ws/courses.wsdl
	// Port-type =CoursePort
	//namespace "http://suraj.com/courses"
	// Course-Detail.xsd
	
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema courseSchema)
	{
		DefaultWsdl11Definition definition=new DefaultWsdl11Definition();
		
		// Port-type =CoursePort
		definition.setPortTypeName("CoursePort");
		
		// /ws
		definition.setLocationUri("/ws");
		
		//namespace "http://suraj.com/courses"
		definition.setTargetNamespace("http://suraj.com/courses");
		
		definition.setSchema(courseSchema);
		
		return definition;
		
	}
	
	@Bean
	XsdSchema courseSchema()
	{
		return new SimpleXsdSchema(new ClassPathResource("Course-Detail.xsd")); 
	}
	
}
