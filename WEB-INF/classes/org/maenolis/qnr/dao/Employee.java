package org.maenolis.CompanyInfo.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@XmlRootElement
@Entity
@Table(name = "EMP")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPNO", columnDefinition="int(2)")
	private int empno;

	@Size(max=10)
	@Column(name = "ENAME", columnDefinition="varchar(10)")
	private String ename;

	@Column(name = "JOB", columnDefinition="varchar(10)")
	private String job;

	
	@OneToOne(fetch=FetchType.EAGER)
	private Employee manager;

	@Column(name = "HIREDATE")
	private Date hireDate;

	@Column(name = "SAL", columnDefinition="decimal(7, 2)")
	private float sal;

	@Column(name = "COMM", columnDefinition="decimal(7, 2)")
	private float comm;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "DEPTNO")
	private Department department;

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@XmlTransient
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}

	public float getComm() {
		return comm;
	}

	public void setComm(float comm) {
		this.comm = comm;
	}

	@XmlTransient
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@SuppressWarnings("unchecked")
	public static List<Employee> getEmployees() {
		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Employee";
		Query query = session.createQuery(hql);
		List<Employee> retList = query.list();

		tx.commit();
		session.close();
		factory.close();

		return retList;
	}

	public static Employee getEmployee(int empno) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Employee Where empno = " + String.valueOf(empno);
		Query query = session.createQuery(hql);
		Employee retEmployee = (Employee) query.uniqueResult();

		tx.commit();
		session.close();
		factory.close();

		return retEmployee;
	}
	
	public static List<Employee> getEmployeesByName(String nameStr) {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Employee Where ename like '%" + nameStr + "%'";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Employee> retList = query.list();

		tx.commit();
		session.close();
		factory.close();

		return retList;
	}

	public Employee() {

	}

	public Employee(String ename, String job, Date hireDate, float sal,
			float comm) {

		this.ename = ename;
		this.job = job;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", job=" + job
				+ ", hireDate=" + hireDate
				+ ", sal=" + sal + ", comm=" + comm + "]";
	}

}
