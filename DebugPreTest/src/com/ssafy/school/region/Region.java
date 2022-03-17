package com.ssafy.school.region;

public class Region {
	
	private String name;
	private int studentCount;

	public Region() {}

	public Region(String name, int studentCount) {
		setName(name);
		setStudentCount(studentCount);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Region)) {
			return false;
		}

		Region other = (Region) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Region [name=" + name + ", studentCount=" + studentCount + "]";
	}
}