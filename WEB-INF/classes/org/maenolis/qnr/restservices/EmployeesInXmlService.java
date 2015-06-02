package org.maenolis.CompanyInfo.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maenolis.CompanyInfo.dao.Employee;
import org.maenolis.CompanyInfo.wrappers.ListWrapper;

@Path("/employeesInXml")
public class EmployeesInXmlService {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ListWrapper<Employee> getEmployeesInXml() {
 
		return new ListWrapper<Employee>(Employee.getEmployees());
	}
}
