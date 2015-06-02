package org.maenolis.CompanyInfo.restservices;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.maenolis.CompanyInfo.dao.Employee;

@Path("/employeeDetails")
public class EmployeeDetailsService {

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("{empno}")
	public Response getEmployeeByEmpno(@PathParam("empno") String empno, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws URISyntaxException {
 
		
		Employee employee = Employee.getEmployee(Integer.valueOf(empno));
		URI target = new URI("/CompanyInfo/employeeDetails.jsp");
		request.getSession().setAttribute("employee", employee);
		return Response.seeOther(target).build();
 
	}
	
}
