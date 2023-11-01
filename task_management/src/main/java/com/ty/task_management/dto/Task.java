package com.ty.task_management.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	@CreationTimestamp
	private LocalDateTime creationDateAndTime;
	@UpdateTimestamp
	private LocalDateTime completedDateAndTime;
	private String status;
	private String assignedTo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreationDateAndTime() {
		return creationDateAndTime;
	}

	public void setCreationDateAndTime(LocalDateTime creationDateAndTime) {
		this.creationDateAndTime = creationDateAndTime;
	}

	public LocalDateTime getCompletedDateAndTime() {
		return completedDateAndTime;
	}

	public void setCompletedDateAndTime(LocalDateTime completedDateAndTime) {
		this.completedDateAndTime = completedDateAndTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

}
