package net.viralpatel.hibernate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTMENT")
public class Department {
	@Id
	private int department_id;
	
	private String departmentName;
	
	private String departmentDescription;
	
	@OneToMany(mappedBy="department", cascade=CascadeType.ALL)
	private List<Employee> employeeLst;

	
	
	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public List<Employee> getEmployeeLst() {
		return employeeLst;
	}

	public void setEmployeeLst(List<Employee> employeeLst) {
		this.employeeLst = employeeLst;
	}
	
	
	
	
}
