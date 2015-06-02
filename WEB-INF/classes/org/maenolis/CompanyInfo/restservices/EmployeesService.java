package org.maenolis.CompanyInfo.restservices;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.maenolis.CompanyInfo.dao.Employee;

@Path("/employees")
public class EmployeesService {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response employees(@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws URISyntaxException {
		
		List<Employee> employees = Employee.getEmployees();
		URI target = new URI("/CompanyInfo/employees.jsp");
		request.getSession().setAttribute("employees", employees);
		return Response.seeOther(target).build();
	}
	
}
