package org.maenolis.CompanyInfo.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maenolis.CompanyInfo.dao.Employee;

@Path("/employeeDetailsInXml")
public class EmployeeDetailsInXmlService {

	@GET
	@Path("/{empno}")
	@Produces(MediaType.APPLICATION_XML)
	public Employee getEmployeeInXML(@PathParam("empno") String empno) {
 
		Employee employee = Employee.getEmployee(Integer.valueOf(empno));
		return employee;
	}
}
