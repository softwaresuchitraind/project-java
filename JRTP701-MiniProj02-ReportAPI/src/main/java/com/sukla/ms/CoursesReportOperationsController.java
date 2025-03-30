//ReportingOperationsController.java
package com.sukla.ms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sukla.model.SearchInputs;
import com.sukla.model.SearchResults;
import com.sukla.service.ICourseMgmtService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reporting/api")
public class CoursesReportOperationsController
{
	@Autowired
	private ICourseMgmtService courseService;
	
	@GetMapping("/courses")
	public ResponseEntity<?> fetchCourseCategories()
	{
		try 
		{
			Set<String> coursesInfo =courseService.showAllCourseCategories();
			return new ResponseEntity<Set<String>>(coursesInfo,HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}//method
	
	@GetMapping("/training-mode")
	public ResponseEntity<?> fetchTrainingModes()
	{
		try 
		{
			Set<String> traingModeInfo =courseService.showAllTrainingModes();
			return new ResponseEntity<Set<String>>(traingModeInfo,HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}//method
	
	@GetMapping("/faculties")
	public ResponseEntity<?> fetchFaculties()
	{
		try 
		{
			Set<String> facultiesInfo =courseService.showAllFaculties();
			return new ResponseEntity<Set<String>>(facultiesInfo,HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}//method
	
	@PostMapping("/search")
	public ResponseEntity<?> fetchCoursesByFilters(@RequestBody SearchInputs inputs)
	{
		try 
		{
			List<SearchResults> list = courseService.showCoursesByFilters(inputs);
			return new ResponseEntity<List<SearchResults>>(list,HttpStatus.OK);
		}
		catch (Exception e) 
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}//method
	@PostMapping("/pdf-report")
	public void showPdfReport(@RequestBody SearchInputs inputs,HttpServletResponse res)
	{
		try 
		{
			//set the response content type
			res.setContentType("application/pdf");
			//set the content-disposition header to response content going to browser as downloadable file.
			res.setHeader("content-Disposition", "attachment;fileName=courses.pdf");
			//use service
			courseService.generatePdfReports(inputs, res);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}//method
	@PostMapping("/excel-report")
	public void showExcelReport(@RequestBody SearchInputs inputs,HttpServletResponse res)
	{
		try 
		{
			//set the response content type
			res.setContentType("application/vnd.ms-excel");
			//set the content-disposition header to response content going to browser as downloadable file.
			res.setHeader("content-Disposition", "attachment;fileName=courses.xls");
			//use service
			courseService.generatePdfReports(inputs, res);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}//method
}//end of class
