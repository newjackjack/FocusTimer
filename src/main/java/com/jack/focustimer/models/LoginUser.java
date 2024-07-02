package com.jack.focustimer.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginUser {
	// email
		@NotEmpty(message = "Email cannot be empty")
		@Email(message = "This is not a valid email.")
		private String email;
			
		// password

		@NotEmpty(message = "Password cannot be empty")
		@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.")
		private String password;
			
			
		//-------------------------------------- Constructor --------------------------------------//
		public LoginUser() {
				
		}
		//-------------------------------------- Constructor --------------------------------------//
		//----------------------------------- getter and setter -----------------------------------//


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
}
