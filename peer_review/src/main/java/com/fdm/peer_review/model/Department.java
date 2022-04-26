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
public class Department {
    @Id
    @SequenceGenerator(name = "DEPT_SEQ_GNTR", sequenceName = "DEPT_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "DEPT_SEQ_GNTR")
    @Column(name = "department_id")
    private int deparmentId;
    
    @Column(name = "department_name", unique = true)
    private String departmentName;
    
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<Employee>();
    
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<ReviewRound> reviewRounds = new ArrayList<ReviewRound>();

    public Department() {
	super();
    }
    
    public Department(String departmentName) {
	super();
	this.departmentName = departmentName;
    }

    public int getDeparmentId() {
        return deparmentId;
    }

    public void setDeparmentId(int deparmentId) {
        this.deparmentId = deparmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<ReviewRound> getReviewRounds() {
        return reviewRounds;
    }

    public void setReviewRounds(List<ReviewRound> reviewRounds) {
        this.reviewRounds = reviewRounds;
    }

    @Override
    public int hashCode() {
	return Objects.hash(deparmentId, departmentName);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Department other = (Department) obj;
	return deparmentId == other.deparmentId && Objects.equals(departmentName, other.departmentName);
    }
}
