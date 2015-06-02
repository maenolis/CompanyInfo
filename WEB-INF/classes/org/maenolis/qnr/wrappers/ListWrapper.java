package org.maenolis.CompanyInfo.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.maenolis.CompanyInfo.dao.Department;
import org.maenolis.CompanyInfo.dao.Employee;
import org.maenolis.CompanyInfo.dao.Location;


@XmlSeeAlso({Location.class, Department.class, Employee.class})

@XmlRootElement
public class ListWrapper<T> {

	private List<T> items;
	
    public ListWrapper() {
        items = new ArrayList<T>();
    }
 
    public ListWrapper(List<T> items) {
        this.items = items;
    }
 
    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return items;
    }
}
