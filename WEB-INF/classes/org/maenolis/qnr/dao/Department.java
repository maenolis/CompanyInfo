package org.maenolis.CompanyInfo.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@XmlRootElement
@Entity
@Table(name = "DEPT")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPTNO", columnDefinition = "int(2)")
	private int deptno;

	@Column(name = "DNAME", columnDefinition = "varchar(14)")
	private String dname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOCNO")
	private Location location;

	@XmlElement
	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	@XmlElement
	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	@XmlTransient
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	private Set<Employee> employees;

	@XmlTransient
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@SuppressWarnings("unchecked")
	public static List<Department> getDepartments() {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Department";
		Query query = session.createQuery(hql);
		List<Department> retList = query.list();

		tx.commit();
		session.close();
		factory.close();

		return retList;
	}

	@SuppressWarnings("unchecked")
	public Set<Employee> getEmployeesPerDepartment() {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Employee Where deptno = "
				+ String.valueOf(this.deptno);
		Query query = session.createQuery(hql);
		List<Employee> retList = query.list();

		tx.commit();
		session.close();
		factory.close();

		return (Set<Employee>) new HashSet<>(retList);
	}

	public static Department getDepartment(int deptno) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Department Where deptno = " + String.valueOf(deptno);
		Query query = session.createQuery(hql);
		Department retDepartment = (Department) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retDepartment;
	}

	public String getLocationName() {
		return location.getLocname();
	}

	public Department(String dname, Location location) {

		this.dname = dname;
		this.location = location;
	}

	public Department() {

	}

	@Override
	public String toString() {
		return "Department [deptno=" + deptno + ", dname=" + dname + "]";
	}

}
