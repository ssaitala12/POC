package net.viralpatel.hibernate;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
	@SequenceGenerator(name="Emp_Gen", sequenceName="Emp_Seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Emp_Gen")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="depat_id",nullable=true )
	private Department department;
		
	@Column(name="firstname")
	@Basic(fetch=FetchType.LAZY)
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name="cell_phone")
	private String cellphone;
	
	
	@Column(name="employee_type")
	private EmployeeType employeeType;

	public Employee() {
		
	}
	
	public Employee(String firstname, String lastname, Date birthdate, String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthdate;
		this.cellphone = phone;
		
	}
	
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Long getId() {
		System.out.println("***In the getId field****");
		return id;
	}

	public String getMy() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setId(Long id) {
		System.out.println("***In the setId field****");
		this.id = id;
	}

	public void setMy(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setBirthDate(Date birthDate) {
		System.out.println("****In the required setget methods****");
		this.birthDate = birthDate;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
}
