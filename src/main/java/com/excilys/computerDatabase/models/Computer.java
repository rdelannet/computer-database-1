package com.excilys.computerDatabase.models;

import java.time.LocalDate;

public class Computer {
	private int id;
	private String name;
	private LocalDate introduced= null;
	private LocalDate discontinued = null;
	private int companyId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) throws IllegalArgumentException{
		if(introduced == null || this.discontinued == null) {
			this.introduced = introduced;
		}
		else if(this.introduced.isBefore(discontinued)) {
			this.introduced = introduced;
		}
		else {
			throw new IllegalArgumentException("discontinued date < introduced date");
		}
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) throws IllegalArgumentException {
		if(this.introduced == null || discontinued==null) {
			this.discontinued = discontinued;
		}
		else if(this.introduced.isBefore(discontinued)) {
			this.discontinued = discontinued;
		}
		else {
			throw new IllegalArgumentException("discontinued date < introduced date");
		}

	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, int companyId) throws IllegalArgumentException {
		this.id = id;
		this.name = name;
		this.companyId = companyId;
		setIntroduced(introduced);
		setDiscontinued(discontinued);
	}
	public Computer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Computer(int id, String name, LocalDate introduced, int companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.companyId = companyId;
	}
	public Computer(int id, String name, LocalDate introduced) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
	}
	public Computer(int id, String name, int companyId) {
		super();
		this.id = id;
		this.name = name;
		this.companyId = companyId;
	}
	public Computer(int id) {
		super();
		this.id = id;
	}
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued)throws IllegalArgumentException {
		super();
		this.id = id;
		this.name = name;
		setIntroduced(introduced);
		setDiscontinued(discontinued);
	}
	public Computer() {
	}
	@Override
	public String toString() {
		String i = getIntroduced()!=null?getIntroduced().toString():"null";
		String d = getDiscontinued()!=null?getDiscontinued().toString():"null";
		return String.format("  %d  %40s  %20s%20s%5d%n",getId(),getName(),i,d,getCompanyId());
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Computer other = (Computer) obj;
		if (companyId!=other.companyId) {
			return false;
		}
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}