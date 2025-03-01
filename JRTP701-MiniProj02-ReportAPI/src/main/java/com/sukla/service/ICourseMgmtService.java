package com.sukla.service;

import java.util.List;
import java.util.Set;

import com.sukla.model.SearchInputs;
import com.sukla.model.SearchResults;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourseMgmtService
{
	public Set<String> showAllCourseCategories();
	public Set<String> showAllTrainingModes();
	public Set<String> showAllFaculties();
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs);
	public void generatePdfReports(SearchInputs inputs,HttpServletResponse res);
	public void generateExcelReports(SearchInputs inputs,HttpServletResponse res);
}
