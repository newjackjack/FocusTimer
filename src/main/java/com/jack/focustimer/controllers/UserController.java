package com.jack.focustimer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jack.focustimer.models.LoginUser;
import com.jack.focustimer.models.User;
import com.jack.focustimer.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// ------------------------------ render Register and Login form ------------------------------//

	@GetMapping("/")
	public String index(Model model) {
		// Login and Reg form needs an empty object newUser to store the input
		// 1. Use Model model to take an empty User class for register form
		model.addAttribute("newUser", new User());
		// 2. Use Model model to take an empty LoginUser class for login form
		model.addAttribute("newLogin", new LoginUser());
		return "logreg.jsp";
	}

	// ------------------------------ render Register and Login form ------------------------------//

	// ------------------------------------- action: register -------------------------------------//
	@PostMapping("/register")
	public String processRegister(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {
		// ToDo: call a register method in the UserService
		User registeredUser = userService.register(newUser, result);
		// Check if there is any error in BindingResult
		// if there is an error -> needs to render the newUser
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "logreg.jsp";
		} else {
			// If no error -> store the registeredUser's id in session
			session.setAttribute("userId", registeredUser.getId());
			session.setAttribute("userName", registeredUser.getUsername());
			return "redirect:/home";
		}
	}

	// ------------------------------------- action: register -------------------------------------//
	
	
	
	
	// --------------------------------------- action: login --------------------------------------//
	@PostMapping("/login")
	public String processLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {
		// To-Do: call a login
		User loggedUser = userService.login(newLogin, result);
		// Check if there is any error in BindingResult
		// if there is an error -> needs to render the newUser
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "logreg.jsp";
		} else {
			// if no error -> store the id in session
			session.setAttribute("userId", loggedUser.getId());
			session.setAttribute("userName", loggedUser.getUsername());
			return "redirect:/home";
		}

	}
	// --------------------------------------- action: login --------------------------------------//

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// clear the session
		session.invalidate();
		return "redirect:/";
	}

}
