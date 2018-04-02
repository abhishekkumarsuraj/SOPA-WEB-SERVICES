package com.suraj.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.suraj.courses.CourseDetail;
import com.suraj.courses.DeleteCourseDetailRequest;
import com.suraj.courses.DeleteCourseDetailResponse;
import com.suraj.courses.GetAllCourseDetailRequest;
import com.suraj.courses.GetAllCourseDetailResponse;
import com.suraj.courses.GetCourseDetailRequest;
import com.suraj.courses.GetCourseDetailResponse;
import com.suraj.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.suraj.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.suraj.soap.webservices.soapcoursemanagement.soap.service.CourseDetailService;
import com.suraj.soap.webservices.soapcoursemanagement.soap.service.CourseDetailService.Status;

@Endpoint
public class CourseDetailEndPoint {
	
	@Autowired
	CourseDetailService service;
	
	//method 
	//input - GetCourseDetailRequest
	//output - GetCourseDetailResponse
	
	//http://suraj.com/courses
	//GetCourseDetailRequest
	
	@PayloadRoot(namespace="http://suraj.com/courses",localPart="GetCourseDetailRequest")
	@ResponsePayload
	public GetCourseDetailResponse processCourceDetailRequest(@RequestPayload GetCourseDetailRequest request)
	{
		
		Course course=service.findById(request.getId());
		
		if(course==null)
		{
			throw new CourseNotFoundException("Invalid Course Id "+request.getId());
		}
	
		return mapCourseDetails(course); 
		
	}


	private GetCourseDetailResponse mapCourseDetails(Course course) {
		GetCourseDetailResponse response=new GetCourseDetailResponse();
		CourseDetail courseDetail = mapCourse(course);
		response.setCourseDetail(courseDetail);
		return response;
	}

	
	private GetAllCourseDetailResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailResponse response=new GetAllCourseDetailResponse();
		
		for(Course course:courses)
		{

			CourseDetail courseDetail = mapCourse(course);
			response.getCourseDetail().add(courseDetail);
			
		}
		
		return response;
	}


	private CourseDetail mapCourse(Course course) {
		CourseDetail courseDetail=new CourseDetail();
		courseDetail.setId(course.getId());
		courseDetail.setName(course.getName());
		courseDetail.setDescription(course.getDescription());
		return courseDetail;
	}
	
	
	@PayloadRoot(namespace="http://suraj.com/courses",localPart="GetAllCourseDetailRequest")
	@ResponsePayload
	public GetAllCourseDetailResponse processAllCourceDetailRequest(@RequestPayload GetAllCourseDetailRequest request)
	{
		List<Course> courses=service.findAll();
		return mapAllCourseDetails(courses); 
		
	}
	
	
	@PayloadRoot(namespace="http://suraj.com/courses",localPart="DeleteCourseDetailRequest")
	@ResponsePayload
	public DeleteCourseDetailResponse deleteCourceDetailRequest(@RequestPayload DeleteCourseDetailRequest request)
	{
		Status status=service.deleteById(request.getId());
		
		DeleteCourseDetailResponse response=new DeleteCourseDetailResponse();
		response.setStatus(mapStatus(status));
		return response;
		
	}


	private com.suraj.courses.Status mapStatus(Status status) {
		
		if(status==Status.FAILURE)
		{
			return com.suraj.courses.Status.FAILURE;
		}
		return com.suraj.courses.Status.SUCCESS;
	}
	
	
	

}
