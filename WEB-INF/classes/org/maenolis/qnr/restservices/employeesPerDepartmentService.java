package org.maenolis.CompanyInfo.restservices;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.maenolis.CompanyInfo.dao.Department;
import org.maenolis.CompanyInfo.dao.Employee;

@Path("/employeesPerDepartment")
public class employeesPerDepartmentService {

	@GET
	@Path("{deptno}")
	@Produces(MediaType.TEXT_HTML)
	public Response getUserById(@PathParam("deptno") String deptno, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws URISyntaxException {
 
		
		Department department = Department.getDepartment(Integer.valueOf(deptno));
		Set<Employee> employees = department.getEmployeesPerDepartment();
		URI target = new URI("/CompanyInfo/employeesPerDepartment.jsp");
		request.getSession().setAttribute("employees", employees);
		return Response.seeOther(target).build();
 
	}
}
