package com.capgemini.listeners;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.capgemini.domain.AbstractEntity;

public class ModificationListeners {

	public class ModificationListener <T extends AbstractEntity> {

		
		  @PrePersist
		  public void setCreationTime(T entity) {
		    entity.setCreationTime(new Date());
		    entity.setLastModificationTime(new Date());
		 
		  }

		
		  @PreUpdate
		  public void updateLastModificationTime(T entity) {
		    entity.setLastModificationTime(new Date());
		  }
		}
}
