package org.maenolis.CompanyInfo.client;

import java.net.MalformedURLException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.maenolis.CompanyInfo.dao.Department;
import org.maenolis.CompanyInfo.dao.Employee;
import org.maenolis.CompanyInfo.dao.Location;
import org.maenolis.CompanyInfo.wrappers.ListWrapper;

public class WsClient {

	private Client client;
	private WebTarget target;
	

	public void showLocations() throws JAXBException, MalformedURLException {

		@SuppressWarnings("unchecked")
		ListWrapper<Location> locations = (ListWrapper<Location>) target
				.path("/locationsInXml/").request(MediaType.APPLICATION_XML)
				.get().readEntity(ListWrapper.class);

		System.out.println("\n\t\tshowLocations\n");
		for (Location location : locations.getItems()) {
			System.out.println(location.toString());
		}
		System.out.println("\n");
	}

	public void showDepartments() throws JAXBException, MalformedURLException {

		@SuppressWarnings("unchecked")
		ListWrapper<Department> departments = (ListWrapper<Department>) target
				.path("departmentsInXml/").request(MediaType.APPLICATION_XML)
				.get().readEntity(ListWrapper.class);
		
		System.out.println("\n\t\tshowDepartments\n");
		for (Department department : departments.getItems()) {
			System.out.println(department.toString());
		}
		System.out.println("\n");
	}

	public void showDepartmentsAtLocation(int locno) throws JAXBException,
			MalformedURLException {

		@SuppressWarnings("unchecked")
		ListWrapper<Department> departments = (ListWrapper<Department>) target
				.path("departmentsPerLocationInXml/" + String.valueOf(locno))
				.request(MediaType.APPLICATION_XML).get()
				.readEntity(ListWrapper.class);

		System.out.println("\n\t\tshowDepartmentsAtLocation location : "
				+ Location.getLocation(locno).getLocname() + "\n");
		for (Department department : departments.getItems()) {
			System.out.println(department.toString());
		}
		System.out.println("\n");
	}

	@SuppressWarnings("unchecked")
	public void showEmployeesAtDepartment(int deptno) throws JAXBException,
			MalformedURLException {

		ListWrapper<Employee> employees = target
				.path("employeesPerDepartmentInXml/" + String.valueOf(deptno))
				.request(MediaType.APPLICATION_XML).get()
				.readEntity(ListWrapper.class);

		System.out.println("\t\tshowEmployeesAtDepartment department : "
				+ Department.getDepartment(deptno).getDname() + "\n");
		for (Employee employee : employees.getItems()) {
			System.out.println(employee.toString());
		}
		System.out.println("\n");
	}

	public void showEmployee(int empno) throws JAXBException,
			MalformedURLException {

		Employee employee = target
				.path("employeeDetailsInXml/" + String.valueOf(empno))
				.request(MediaType.APPLICATION_XML).get()
				.readEntity(Employee.class);

		System.out.println("\t\tshowEmployee employee : "
				+ Employee.getEmployee(empno).getEname() + "\n");
		System.out.println(employee.toString());
		System.out.println("\n");
	}

	public void showEmployeesByName(String nameStr) throws JAXBException,
			MalformedURLException {

		@SuppressWarnings("unchecked")
		ListWrapper<Employee> employees = target
				.path("employeesByNameInXml/" + nameStr)
				.request(MediaType.APPLICATION_XML).get()
				.readEntity(ListWrapper.class);

		System.out.println("\t\tshowEmployeesByName + query string : "
				+ nameStr + "\n");
		for (Employee employee : employees.getItems()) {
			System.out.println(employee.toString());
		}
		System.out.println("\n");
	}

	public WsClient(String servicesBase) {
		client = ClientBuilder.newClient();
		client.register(Employee.class).register(Department.class)
				.register(Location.class).register(ListWrapper.class);
		target = client.target(servicesBase);
	}

	public static void main(String[] args) throws JAXBException,
			MalformedURLException {
		
		// Client usage
		WsClient wc = new WsClient("http://localhost:8080/CompanyInfo/rest/");
		wc.showEmployee(12);
		wc.showLocations();
		wc.showDepartments();
		wc.showDepartmentsAtLocation(3);
		wc.showEmployeesAtDepartment(29);
		wc.showEmployeesByName("tania");

		System.out.println("END");

	}

}
