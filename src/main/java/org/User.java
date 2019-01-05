package org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "User")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Type(type="integer")
	private int id;
	
	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "email", length = 45)
	private String email;

	@Column(name = "password", length = 45)
	private String password;
	
	@Column(name = "gender", length = 45)
	private String gender;

	@Column(name = "address", length = 45)
	private String address;
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    } 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
