package org.maenolis.CompanyInfo.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.maenolis.CompanyInfo.dao.Location;
import org.maenolis.CompanyInfo.wrappers.ListWrapper;

@Path("/locationsInXml")
public class LocationsInXmlService {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ListWrapper<Location> getLocationsInXml() {
 
		return new ListWrapper<Location>(Location.getLocations());
		
		
	}
}
