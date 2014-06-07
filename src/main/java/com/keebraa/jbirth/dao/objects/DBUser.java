package com.keebraa.jbirth.dao.objects;

import java.util.List;

public class DBUser {
	private String id;
	private String login;
	private String password;
	private String email;
	private String name;
	private int age;
	private List<String> groups;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
