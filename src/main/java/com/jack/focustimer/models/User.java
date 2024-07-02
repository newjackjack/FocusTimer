package com.jack.focustimer.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id // to specify the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment for the Id
	private Long id;

	// username
	@NotEmpty(message = "Username cannot be empty")
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
	private String username;

	// email
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "This is not a valid email.")
	private String email;

	// password

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.")
	private String password;

	// confirm password -> @Transient will not store in the database
	@Transient
	@NotEmpty(message = "Confirm Password cannot be empty")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.")
	private String confirmPassword;
	
//	// one-to-many,task
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<Task> ownedTask;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Task> getOwnedTask() {
		return ownedTask;
	}

	public void setOwnedTask(List<Task> ownedTask) {
		this.ownedTask = ownedTask;
	}


	
}
