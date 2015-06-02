package org.maenolis.CompanyInfo.filler;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maenolis.CompanyInfo.dao.Department;
import org.maenolis.CompanyInfo.dao.Employee;
import org.maenolis.CompanyInfo.dao.Location;

public class DatabaseFiller {

	private static final String[] locations = { "athens", "paris", "london",
			"rome", "kiev" };
	private static final String[] names = { "alex", "ricky", "kostas", "liza",
		"kiki", "tana", "diana", "paris", "james" };
	private static final String[] surnames = { "A.", "B.", "C.", "D.",
		"E.", "F.", "G.", "H.", "I." };
	private static final int noOfDepts = 50;
	private static final int noOfEmployees = 200;

	public static void main(String[] args) {

		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		// Make locations
		for (String location : locations) {
			session.save(new Location(location));
		}
		tx.commit();
		session.close();

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		List<Location> locs = Location.getLocations();
		Random rand = new Random();
		
		// Make departments
		for (int i = 0; i < noOfDepts; i++) {
			Location location = locs.get(i % locs.size());
			session.save(new Department("dept" + String.valueOf(i + 1),
					location));
		}
		tx.commit();
		session.close();

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		List<Department> depts = Department.getDepartments();
		
		
		// Make employees
		for (int i = 0; i < noOfEmployees; i++) {
			String name = names[rand.nextInt(names.length)];
			String surname = surnames[rand.nextInt(surnames.length)];
			String job = "job" + String.valueOf(Math.abs(rand.nextInt() % 50));
			Date hireDate = new Date(Math.abs(System.currentTimeMillis() - rand.nextLong() % 1000000));
			float sal = rand.nextFloat() * 10000;
			float comm = rand.nextFloat() * 10000;
			Department department = depts.get(rand.nextInt(depts.size()));
			Employee employee = new Employee(name + " " + surname, job, hireDate, sal, comm);
			employee.setDepartment(department);
			session.save(employee);
		}
		tx.commit();
		session.close();
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		List<Employee> employees = Employee.getEmployees();
		
		
		// Set managers
		for (int i = 0; i < employees.size(); i++) {
			Employee employee = employees.get(i);
			employee.setManager(employees.get(rand.nextInt(employees.size())));
			session.update(employee);
		}
		tx.commit();
		session.close();
		System.out.println("End");

	}

}
