package com.sukla.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="JRTP_COURSE_DETAIS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetails
{
	@Id
	@GeneratedValue
	private Integer courseId;
	@Column(length=50)
	private String courseName;
	@Column(length=30)
	private String location;
	@Column(length=30)
	private String courseCategory;
	@Column(length=30)
	private String facultyName;
	private Double fee;
	@Column(length=30)
	private String adminName;
	private Long adminContact;
	@Column(length=30)
	private String trainingMode;
	private LocalDateTime startDate;
	@Column(length=10)
	private String courseStatus;
	@CreationTimestamp
	@Column(insertable = true,updatable = false)
	private LocalDateTime creationDate;
	@UpdateTimestamp
	@Column(insertable = false,updatable = true)
	private LocalDateTime updationDate;
	@Column(length=30)
	private String createdBy;
	@Column(length=30)
	private String updatedBy;
	

}
