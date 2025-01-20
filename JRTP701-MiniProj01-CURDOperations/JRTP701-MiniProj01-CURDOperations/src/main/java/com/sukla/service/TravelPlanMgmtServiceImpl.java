package com.sukla.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukla.config.AppConfigProperties;
import com.sukla.constants.TravelPlanConstants;
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
	
	private Map<String, String> messages;
	
	@Autowired
	public TravelPlanMgmtServiceImpl(AppConfigProperties props)
	{
		System.out.println(props);
		messages=props.getMessages();
		System.out.println("messages::"+messages);
	}

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
		
		return saved.getPlanId()!=null?messages.get(TravelPlanConstants.SAVE_SUCCESS)+saved.getPlanId():messages.get(TravelPlanConstants.SAVE_FAILURE);
		
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
		
		return travelPlanRepo.findById(planId).orElseThrow(()->new IllegalArgumentException(messages.get(TravelPlanConstants.FIND_BY_ID_FAILURE)));
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
			return plan.getPlanId()+messages.get(TravelPlanConstants.UPDATE_SUCCESS);
		}
		else
		{
			return plan.getPlanId()+messages.get(TravelPlanConstants.UPDATE_FAILURE);
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
			return planId+messages.get(TravelPlanConstants.DELETE_SUCCESS);
		}
		else
		{
			return planId+messages.get(TravelPlanConstants.DELETE_FAILURE);
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
			return planId+messages.get(TravelPlanConstants.STATUS_CHANGE_SUCCESS);
		}
		else
		{
			return planId+messages.get(TravelPlanConstants.STATUS_CHANGE_FAILURE);
		}
	}

}
