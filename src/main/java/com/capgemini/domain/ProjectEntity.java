package com.capgemini.domain;

import javax.persistence.*;

import com.capgemini.listeners.ModificationListeners;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROJECTS")
@EntityListeners({ ModificationListeners.class })

//@NamedQuery(name="books.findBooksByAuthor", query="select b from BookEntity b join b.authors a where a.id = :authorId")

public class ProjectEntity extends AbstractEntity implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -379284209620632699L;
	
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long projectId;
		
	    @Column(nullable = false, length = 50)
	    private String pName;
	    
	    @OneToMany(targetEntity=EmployeeProjectsEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="project")
	    private Set<EmployeeProjectsEntity> employeeProject = new HashSet<>();
	    
	    @Enumerated (EnumType.STRING)
	    private ProjectLocation location;

		    
	    // for hibernate
	   
	    public ProjectEntity() {	super();}

	    public ProjectEntity(String pName, Set<EmployeeProjectsEntity> employeeProject, ProjectLocation location) {
	    	super();
	    	this.pName = pName;
	        this.employeeProject=employeeProject;
	        this.location = location;

	}

		public Long getProjectId() {
			return projectId;
		}

		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}

		public String getpName() {
			return pName;
		}

		public void setpName(String pName) {
			this.pName = pName;
		}

		public Set<EmployeeProjectsEntity> getEmployeeProject() {
			return employeeProject;
		}

		public void setEmployeeProject(Set<EmployeeProjectsEntity> employeeProject) {
			this.employeeProject = employeeProject;
		}

		public ProjectLocation getLocation() {
			return location;
		}

		public void setLocation(ProjectLocation location) {
			this.location = location;
		}




	    
		
	    
	}


