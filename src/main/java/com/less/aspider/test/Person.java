package com.less.aspider.test;

import com.less.aspider.dao.Column;
import com.less.aspider.dao.Table;

@Table
public class Person {

	@Column(value = "person_id", columnDefinition = "int primary key auto_increment")
	private Long id;
	@Column
	private String name;
	@Column
	private String age;

	public Person(){

	}

	public Person(String name, String age) {
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
