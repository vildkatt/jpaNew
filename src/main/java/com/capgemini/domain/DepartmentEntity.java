package com.capgemini.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.capgemini.listeners.ModificationListeners;

@Entity
@Table(name = "DEPARTMENTS")
//@EntityListeners(ModificationListeners.class)
//@EntityListeners(AuditingEntityListener.class)
public class DepartmentEntity extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3765447821392360205L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	@Column(nullable = false, length = 50)
	private String departmentName;
	@OneToMany(targetEntity = EmployeeEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "department")
	private Set<EmployeeEntity> employees = new HashSet<>();
	@Column(nullable = true, length = 50)
	private String email;
	@Column(nullable = true, length = 50)
	private String landLine;
	@Column(nullable = true, length = 16)
	private String companyNumber;


	


	// for hibernate
	public DepartmentEntity() {
		super();
	}

	public DepartmentEntity(String departmentName) {
		super();
		this.departmentName = departmentName;

	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}




}