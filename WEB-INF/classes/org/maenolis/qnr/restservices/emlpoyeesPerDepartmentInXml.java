package org.maenolis.CompanyInfo.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maenolis.CompanyInfo.dao.Department;
import org.maenolis.CompanyInfo.dao.Employee;
import org.maenolis.CompanyInfo.wrappers.ListWrapper;

@Path("/employeesPerDepartmentInXml")
public class emlpoyeesPerDepartmentInXml {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("{deptno}")
	public ListWrapper<Employee> getemployeesPerDepartment(
			@PathParam("deptno") String deptno) {

		List<Employee> list = new ArrayList<Employee>(Department.getDepartment(
				Integer.valueOf(deptno)).getEmployeesPerDepartment());
		return new ListWrapper<Employee>(list);
	}
}
