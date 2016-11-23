package com.capgemini.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.listeners.ModificationListeners;
import com.capgemini.listeners.ModificationListeners.ModificationListener;

@Entity
@Table(name = "EMPLOYEE_PROJECTS")
@EntityListeners({ ModificationListeners.class })



public class EmployeeProjectsEntity extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 345860414284984216L;

	@Id
	@GeneratedValue
	private Long epId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID", nullable = true)
	private EmployeeEntity employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID", nullable = true)
	private ProjectEntity project;

	@Column(nullable = true)
	private String role;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date finishDate;

	@Column
	private Long dailyRate;

	public EmployeeProjectsEntity() {
		super();
	}

	public EmployeeProjectsEntity(EmployeeEntity employee, ProjectEntity project, String role, Date startDate,
			Long dailyRate) {
		super();
		this.employee = employee;
		this.project = project;
		this.role = role;
		this.startDate = startDate;
		this.dailyRate = dailyRate;

	}

	public Long getEpId() {
		return epId;
	}

	public void setEpId(Long epId) {
		this.epId = epId;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Long getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(Long dailyRate) {
		this.dailyRate = dailyRate;
	}

}
