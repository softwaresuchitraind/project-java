package com.sukla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sukla.entity.PlanCategory;

public interface IPlanCategoryRepository extends JpaRepository<PlanCategory, Integer>
{

}
