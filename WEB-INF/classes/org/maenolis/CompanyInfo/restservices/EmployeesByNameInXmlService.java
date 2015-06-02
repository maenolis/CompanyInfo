package org.maenolis.CompanyInfo.restservices;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maenolis.CompanyInfo.dao.Employee;
import org.maenolis.CompanyInfo.wrappers.ListWrapper;

@Path("/employeesByNameInXml")
public class EmployeesByNameInXmlService {

	@GET
	@Path("/{nameStr}")
	@Produces(MediaType.APPLICATION_XML)
	public ListWrapper<Employee> getEmployeeInXML(@PathParam("nameStr") String nameStr) {
 
		return new ListWrapper<Employee>(new ArrayList<Employee>(Employee.getEmployeesByName(nameStr)));
	}
}
