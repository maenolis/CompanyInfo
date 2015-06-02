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
@Table(name = "DEPTLOC")
public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOCNO", columnDefinition="int(2)")
	private int locno;

	
	@Column(name = "LOCNAME", columnDefinition="varchar(10)")
	private String locname;

	@OneToMany(mappedBy = "location", fetch=FetchType.EAGER)
	private Set<Department> departments;

	@XmlTransient
	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	@XmlElement
	public int getLocno() {
		return locno;
	}

	public void setLocno(int locno) {
		this.locno = locno;
	}

	@XmlElement
	public String getLocname() {
		return locname;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}

	@SuppressWarnings("unchecked")
	public static List<Location> getLocations() {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Location";
		Query query = session.createQuery(hql);
		List<Location> retList = query.list();

		tx.commit();
		session.close();
		factory.close();

		return retList;
	}
	
	@XmlTransient
	@SuppressWarnings("unchecked")
	public Set<Department> getDepartmentsPerLocation() {

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Department Where locno = " + String.valueOf(this.locno);
		Query query = session.createQuery(hql);
		List<Department> retList = query.list();
		

		tx.commit();
		session.close();
		factory.close();

		return (Set<Department>)new HashSet<>(retList);
	}
	
	
	public static Location getLocation(int locno) {
		
		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx;
		tx = session.beginTransaction();

		String hql = "From Location Where locno = " + String.valueOf(locno);
		Query query = session.createQuery(hql);
		Location retLocation = (Location)query.uniqueResult();
		

		tx.commit();
		session.close();
		factory.close();
		
		return retLocation;
	}

	public Location() {

	}

	public Location(String locname) {

		this.locname = locname;
	}

	@Override
	public String toString() {
		return "Location [locno=" + locno + ", locname=" + locname + "]";
	}

}
