package com.fdm.peer_review.model;


import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Employee {
    @Id
    @SequenceGenerator(name = "EMP_SEQ_GNTR", sequenceName = "EMP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ_GNTR")
    @Column(name = "employee_id")
    private int employeeId;
    
    @Column(name = "manager_id")
    private int managerId;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "username", unique = true)
    private String userName;
    
    @Column(name = "password")
    private String passWord;
    
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;
    
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
   
    public Employee() {
	super();
    }
    
    public Employee(String firstName, String lastName, String userName, String passWord,
	    Department department, Permission permission) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.passWord = passWord;
	this.department = department;
	this.permission = permission;
    }
    
    
    public Employee(int managerId, String firstName, String lastName, String userName, String passWord,
	    Department department, Permission permission) {
	super();
	this.managerId = managerId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.passWord = passWord;
	this.department = department;
	this.permission = permission;
    }
 
    public Employee(Employee manager, String firstName, String lastName, String userName, String passWord,
	    Department department, Permission permission) {
	super();
	this.managerId = manager.getEmployeeId();
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.passWord = passWord;
	this.department = department;
	this.permission = permission;
    }
    
    public Employee(String firstName, String lastName, String userName) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
    }

    public int getEmployeeId() {
        return employeeId;
    }



    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }



    public int getManagerId() {
        return managerId;
    }


    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    
    public void setManagerId(Employee manager) {
        this.managerId = manager.getEmployeeId();
    }

    
    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getUserName() {
        return userName;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPassWord() {
        return passWord;
    }



    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }



    public Department getDepartment() {
        return department;
    }



    public void setDepartment(Department department) {
        this.department = department;
    }



    public Permission getPermission() {
        return permission;
    }



    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public int hashCode() {
	return Objects.hash(department, employeeId, firstName, lastName, managerId, passWord, userName);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Employee other = (Employee) obj;
	return Objects.equals(department, other.department) && employeeId == other.employeeId
		&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
		&& managerId == other.managerId && Objects.equals(passWord, other.passWord)
		&& Objects.equals(userName, other.userName);
    }

}
