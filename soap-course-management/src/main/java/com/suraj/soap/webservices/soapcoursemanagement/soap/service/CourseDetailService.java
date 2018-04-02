package com.suraj.soap.webservices.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.suraj.soap.webservices.soapcoursemanagement.soap.bean.Course;

@Component
public class CourseDetailService {
	
	public enum Status{
		SUCCESS,
		FAILURE;
	}
	
	private static ArrayList<Course> courses=new ArrayList<Course>();
	
	static {
		
		 Course course1=new Course(1,"Spring","Spring Framework");
		 courses.add(course1);
		 
		 Course course2=new Course(2,"SpringBoot","Spring React");
		 courses.add(course2);
		 
		 Course course3=new Course(3,"WebServices","Spring SOAP");
		 courses.add(course3);
		 
		 Course course4=new Course(4,"SpringRest","SpringBoot Rest");
		 courses.add(course4);
	}
	
	//course find by id
	
	public Course findById(int id)
	{
		for(Course course:courses)
		{
			if(course.getId()==id)
			{
				return course;
			}
		}
		
		return null;
	}
	
	//courses 
	
	public List<Course> findAll()
	{
		return courses;
	}
	
	//delete courses
	
	public Status deleteById(int id)
	{
		Iterator<Course> itr=courses.iterator();
		
		while(itr.hasNext())
		{
			Course course=itr.next();
			if(course.getId()==id)
			{
				itr.remove();
				return Status.SUCCESS;
			}
			
		}
		
		return Status.FAILURE;
	}
	

}
