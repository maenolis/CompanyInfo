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
import org.maenolis.CompanyInfo.dao.Location;
 
@Path("/departmentsPerLocation")
public class DepartmentsPerLocationService {
 
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("{locno}")
	public Response getUserById(@PathParam("locno") String locno, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws URISyntaxException {
 
		
		Location location = Location.getLocation(Integer.valueOf(locno));
		Set<Department> departments = location.getDepartmentsPerLocation();
		URI target = new URI("/CompanyInfo/departmentsPerLocation.jsp");
		request.getSession().setAttribute("departments", departments);
		return Response.seeOther(target).build();
 
	}
 
}