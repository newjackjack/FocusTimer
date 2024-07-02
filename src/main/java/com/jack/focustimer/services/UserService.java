package com.jack.focustimer.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jack.focustimer.models.LoginUser;
import com.jack.focustimer.models.User;
import com.jack.focustimer.repositories.UserRepository;

@Service
public class UserService {
	// --------------------------------- Import dependency from repository ---------------------------------//
	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	// --------------------------------- Import dependency from repository ---------------------------------//
	// -------------------------------------- register(newUser, result) ------------------------------------//
		// Define a register method to handle:
		// 1. Check if the email is already in the db(has been registered)
		// If the email is present -> reject
		// 2. Check if the password matches the confirmPassword. If not -> reject
		// 4. If results has error -> return null to the caller
		// 5. All conditionals pass -> hashed password and save to db
		public User register(User newUser, BindingResult result) {
			// 1. findByEmail(string email)
			// email is within User class -> needs to use getter to get the email from
			// newUser
			Optional<User> optionalNewUser = userRepo.findByEmail(newUser.getEmail());
			// If this optionalNewUser's password is present in the DB -> REJECT
			if (optionalNewUser.isPresent()) {
				// reject this attempt
				result.rejectValue("email", "unique", "Email has already been used.");
			}
			// If the password and confirmPassword do not match
			if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "match", "The password does not match.");
			}

			// if result has any error -> REJECT
			if (result.hasErrors()) {
				return null;
			}

			// All the conditionals pass -> hased and save the password
			String hashedPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());

			// set this pw to newUser
			newUser.setPassword(hashedPW);

			// After pw is hashed -> userRepo.save()
			User registeredUser = userRepo.save(newUser);

			// return registeredUser back to the caller
			return registeredUser;

		}
		// ----------------------------------- register(newUser, result) ------------------------------------//

		// ------------------------------------- login(newUser, result) -------------------------------------//
		// Define a login method to handle:
		// 1. Find email in DB
		// If the email is not present -> reject
		// 2. Check if the password matches the password from DB. If not -> reject
		// 4. If results has error -> return null to the caller
		// 5. All conditionals pass -> hashed password and save to db
				

		public User login(LoginUser newLogin, BindingResult result) {
			// 1. Find one user by id -> use findByEmail
			
			Optional<User> optionalLoginUser = userRepo.findByEmail(newLogin.getEmail());
			//If the email is not present -> reject
			if(!optionalLoginUser.isPresent()) {
				result.rejectValue("email", "match", "Email is not registered.");
				return null;
			}
			//If the email is found in DB -> grab the user
			User loggedUser = optionalLoginUser.get();
			
			//Check if the password matches the password from DB. If not -> reject
			if(!BCrypt.checkpw(newLogin.getPassword(), loggedUser.getPassword())) {
				result.rejectValue("password", "match", "Invalid password.");
			}
			//if there is any error in result -> REJECT
			if(result.hasErrors()) {
				return null;
			}
			//If all the tests pass -> return the loggedUser to the caller
			return loggedUser;
		}
}
