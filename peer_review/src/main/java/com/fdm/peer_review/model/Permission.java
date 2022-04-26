package com.fdm.peer_review.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Permission {
    @Id
    @SequenceGenerator(name = "PMSN_SEQ_GNTR", sequenceName = "PMSN_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PMSN_SEQ_GNTR")
    @Column(name = "permission_id")
    private int permissionId;
    
    @Column(name = "permission_description")
    private String permissionDescription;
    
    @Column(name = "is_department_manager")
    private boolean isDepartmentManager;
    
    @Column(name = "is_HR")
    private boolean isHR;
    
    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<Employee>();
    
    public Permission() {
	super();
    }
    
    public Permission(String permissionDescription, boolean isDepartmentManager, boolean isHR) {
	super();
	this.permissionDescription = permissionDescription;
	this.isDepartmentManager = isDepartmentManager;
	this.isHR = isHR;
    }

    public Permission(boolean isDepartmentManager, boolean isHR) {
	super();
	this.isDepartmentManager = isDepartmentManager;
	this.isHR = isHR;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public boolean isDepartmentManager() {
        return isDepartmentManager;
    }

    public void setDepartmentManager(boolean isDepartmentManager) {
        this.isDepartmentManager = isDepartmentManager;
    }

    public boolean isHR() {
        return isHR;
    }

    public void setHR(boolean isHR) {
        this.isHR = isHR;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int hashCode() {
	return Objects.hash(isDepartmentManager, isHR, permissionId);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Permission other = (Permission) obj;
	return isDepartmentManager == other.isDepartmentManager && isHR == other.isHR
		&& permissionId == other.permissionId;
    }
    
    
}
