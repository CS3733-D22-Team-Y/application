package edu.wpi.cs3733.d22.teamY.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
public class Employee implements StringArrayConv{
	@Id private String IDNumber;
	//add other fields here john its setup otherwise for hibernate now

	@Override
	public String[] toStringArray() {
		return new String[0];
	}

	@Override
	public void fromStringArray(String[] args) {

	}
}
