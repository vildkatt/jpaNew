package com.capgemini.domain;

import javax.persistence.*;

import com.capgemini.listeners.ModificationListeners;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
//
@Entity
@Table(name = "EMPLOYEES")
@EntityListeners({ ModificationListeners.class })

@NamedQuery(name="Employee.findAllEmployees", query="select e from EmployeeEntity e")

public class EmployeeEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="EMPLOYEE_ID")
    private Long employeeId;
	
    @Column(nullable = false, length = 50)
    private String firstName;
    
    @Column(nullable = false, length = 50)
    private String surname;
    
    @Column(nullable = false, length = 11)
    private String pesel;
    
    @Column(nullable = true, length = 50)
    private String email;
    
    @Column(nullable = true, length = 50)
    private String homeNumber;
    
    @Column(nullable = true, length = 16)
    private String companyNumber;
    
	@Temporal(TemporalType.DATE)
    private Date birthDate;

	@OneToMany(targetEntity=EmployeeProjectsEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="employee", orphanRemoval = true)
	private Set<EmployeeProjectsEntity> employeeProject = new HashSet<>();
	
	@ManyToOne	(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID", nullable = true)
	private DepartmentEntity department;

	
	
    // for hibernate
    public EmployeeEntity() {
    	super();
}

    public EmployeeEntity(String pesel, String firstName, String surname, Date bithDate, Set<EmployeeProjectsEntity> employeeProject, DepartmentEntity department) {
        super();
    	this.pesel = pesel;
        this.firstName=firstName;
        this.surname = surname;
        this.employeeProject = employeeProject;
        this.department = department;
}


	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Set<EmployeeProjectsEntity> getEmployeeProject() {
		return employeeProject;
	}

	public void setEmployeeProject(Set<EmployeeProjectsEntity> employeeProject) {
		this.employeeProject = employeeProject;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((companyNumber == null) ? 0 : companyNumber.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((employeeProject == null) ? 0 : employeeProject.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((homeNumber == null) ? 0 : homeNumber.hashCode());
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeEntity other = (EmployeeEntity) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (companyNumber == null) {
			if (other.companyNumber != null)
				return false;
		} else if (!companyNumber.equals(other.companyNumber))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (employeeProject == null) {
			if (other.employeeProject != null)
				return false;
		} else if (!employeeProject.equals(other.employeeProject))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeNumber == null) {
			if (other.homeNumber != null)
				return false;
		} else if (!homeNumber.equals(other.homeNumber))
			return false;
		if (pesel == null) {
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}



	
    
}
