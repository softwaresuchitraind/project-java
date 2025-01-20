package com.sukla.service;

import java.util.List;
import java.util.Map;

import com.sukla.entity.TravelPlan;

public interface ITravelPlanMgmtService
{
	public String registerTravelPlan(TravelPlan plan);//save
	public Map<Integer,String> getTravelPlanCategories();//for select operation
	public List<TravelPlan> showAllTravelPlans();//for select operation
	public TravelPlan showTravelPlanById(Integer planId);//for edit operation to show the existing record for editing
	public String updateTravelPlan(TravelPlan plan);//for edit operation form submit
	public String deleteTravelPlan(Integer planId);//for delete operation
	public String changeTravelPlanStatus(Integer planId,String status);//for soft deletion activity

	
}
