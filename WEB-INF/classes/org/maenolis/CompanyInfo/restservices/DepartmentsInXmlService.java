package org.maenolis.CompanyInfo.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maenolis.CompanyInfo.dao.Department;
import org.maenolis.CompanyInfo.wrappers.ListWrapper;

@Path("/departmentsInXml")
public class DepartmentsInXmlService {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ListWrapper<Department> getDepartmentsInXml() {
 
		return new ListWrapper<Department>(Department.getDepartments());
	}
}
