package com.sukla.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukla.entity.PlanCategory;
import com.sukla.entity.TravelPlan;
import com.sukla.repository.IPlanCategoryRepository;
import com.sukla.repository.ITravelPlanRepository;

@Service
public class TravelPlanMgmtServiceImpl implements ITravelPlanMgmtService
{
	@Autowired
	private ITravelPlanRepository travelPlanRepo;
	
	@Autowired
	private IPlanCategoryRepository planCategoryRepo;

	@Override
	public String registerTravelPlan(TravelPlan plan)
	{
		// save the object
		
		TravelPlan saved = travelPlanRepo.save(plan);
		/*
		if(saved.getPlanId()!=null)
		{
			return "Travel Plan is saved with id value :"+saved.getPlanId();
		}
		else
		{
			return "Problem in saving the Travel Plan";
		}*/
		
		return saved.getPlanId()!=null?"Travel plan is saved with id value::"+saved.getPlanId():"Problem in saving tour plan";
		
	}

	@Override
	public Map<Integer, String> getTravelPlanCategories()
	{
		//get All TravelPlan Categories
		
		List<PlanCategory> list = planCategoryRepo.findAll();
		Map<Integer, String> categoriesMap = new HashMap<Integer,String>();
		
		list.forEach(category->
		{
			categoriesMap.put(category.getCatagoryId(), category.getCategoryName());
			
		});
		return categoriesMap;
	}

	@Override
	public List<TravelPlan> showAllTravelPlans()
	{
		
		return travelPlanRepo.findAll();
	}

	@Override
	public TravelPlan showTravelPlanById(Integer planId)
	{
		
		return travelPlanRepo.findById(planId).orElseThrow(()->new IllegalArgumentException("Travel Plan is not found"));
		/*
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		
		if(opt.isEmpty())
		{
			return opt.get();
		}
		else
		{
			throw new IllegalArgumentException("plan is is not found");
		}*/
	}

	@Override
	public String updateTravelPlan(TravelPlan plan)
	{
		
		Optional<TravelPlan> opt= travelPlanRepo.findById(plan.getPlanId());
		
		if(opt.isPresent())
		{
			//update the record 
			travelPlanRepo.save(plan);
			return plan.getPlanId()+"Travel  is updated";
		}
		else
		{
			return plan.getPlanId()+"Travel Plan is not found";
		}
		
		//update the update
		/*
		TravelPlan updated = travelPlanRepo.save(plan);
		return updated.getPlanId()+"Travel Plan is Updated";
		*/
	}

	@Override
	public String deleteTravelPlan(Integer planId)
	{
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		
		if(opt.isPresent())
		{
			travelPlanRepo.deleteById(planId);
			return planId+"Travel Plan is deleted";
		}
		else
		{
			return planId+"Travel Plan is not found";
		}
	}

	@Override
	public String changeTravelPlanStatus(Integer planId, String status)
	{
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		
		if(opt.isPresent())
		{
			TravelPlan plan = opt.get();
			plan.setActiveSW(status);
			travelPlanRepo.save(plan);
			return planId+"Travel plan status is changed";
		}
		else
		{
			return planId+"Travel Plan is not found for updation";
		}
	}

}
