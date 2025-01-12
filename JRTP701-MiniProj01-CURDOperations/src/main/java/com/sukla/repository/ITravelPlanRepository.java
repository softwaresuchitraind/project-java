package com.sukla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sukla.entity.TravelPlan;

public interface ITravelPlanRepository extends JpaRepository<TravelPlan, Integer>
{

}
