package org.maenolis.CompanyInfo.restservices;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.maenolis.CompanyInfo.dao.Department;
import org.maenolis.CompanyInfo.dao.Location;
import org.maenolis.CompanyInfo.wrappers.ListWrapper;

@Path("/departmentsPerLocationInXml")
public class DepartmentsPerLocationInXmlService {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("{locno}")
	public ListWrapper<Department> getDepartmentsPerLocation(
			@PathParam("locno") String locno) {

		List<Department> list = new ArrayList<Department>(Location.getLocation(
				Integer.valueOf(locno)).getDepartmentsPerLocation());
		return new ListWrapper<Department>(list);

	}

}