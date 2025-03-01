package com.sukla.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.sukla.entity.CourseDetails;
import com.sukla.model.SearchInputs;
import com.sukla.model.SearchResults;
import com.sukla.repository.ICourseDetailsRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service("courseService")
public class CourseMgmtServiceImpl implements ICourseMgmtService
{
	@Autowired
	private ICourseDetailsRepository courseRepo;

	@Override
	public Set<String> showAllCourseCategories()
	{
		return courseRepo.getUniqueCourseCategories();

	}

	@Override
	public Set<String> showAllTrainingModes()
	{
		return courseRepo.getUniqueTrainingMode();
	}

	@Override
	public Set<String> showAllFaculties()
	{
		return courseRepo.getUniqueFacultyNames();
	}

	@Override
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs)
	{
		// get NonNull and non empty String values from the inputs object and prepare
		// Entity object having
		// that non null data and also place that entity object inside the Example obj

		CourseDetails entity = new CourseDetails();
		String category = inputs.getCourseCategory();
		if (StringUtils.hasLength(category))
		{
			entity.setCourseCategory(category);
		}

		String facultyName = inputs.getFacultyName();
		if (StringUtils.hasLength(facultyName)) {
			entity.setFacultyName(facultyName);
		}

		String trainingMode = inputs.getTrainingMode();
		if (StringUtils.hasLength(trainingMode)) {
			entity.setTrainingMode(trainingMode);
		}

		LocalDateTime startDate = inputs.getStartsOn();
		if (!ObjectUtils.isEmpty(startDate)) {
			entity.setStartDate(startDate);
		}

		Example<CourseDetails> example = Example.of(entity);

		// perform the search operation with Filters data of the Example Entity obj
		List<CourseDetails> listEntities = courseRepo.findAll();
		// convert List<Entity obj> to List<SearchResults> obj
		List<SearchResults> listResults = new ArrayList();
		listEntities.forEach(course -> {

			SearchResults result = new SearchResults();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
		return listResults;
	}

	@Override
	public void generatePdfReports(SearchInputs inputs, HttpServletResponse res)
	{
		

	}

	@Override
	public void generateExcelReports(SearchInputs inputs, HttpServletResponse res)
	{
		//get the SearchResult
		List<SearchResults> list = showCoursesByFilters(inputs);
		
		//create ExcelWorkBook
		HSSFWorkbook workbook = new HSSFWorkbook();
		//create Sheet in the Work book
		HSSFSheet sheet1 = workbook.createSheet("CourseDetails");
		//create Heading Row in sheet1
		HSSFRow headerRow = sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("CourseID");
		headerRow.createCell(1).setCellValue("CourseName");
		headerRow.createCell(2).setCellValue("Location");
		headerRow.createCell(3).setCellValue("CourseCategory");
		headerRow.createCell(4).setCellValue("FacultyName");
		headerRow.createCell(5).setCellValue("fee");
		headerRow.createCell(6).setCellValue("adminContact");
		headerRow.createCell(7).setCellValue("trainingMode");
		headerRow.createCell(8).setCellValue("startDate");
		headerRow.createCell(9).setCellValue("courseStatus");

	}

}
