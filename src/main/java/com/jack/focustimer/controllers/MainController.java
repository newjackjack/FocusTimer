package com.jack.focustimer.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jack.focustimer.models.Task;
import com.jack.focustimer.services.TaskService;
import com.jack.focustimer.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	private final UserService userService;
	private final TaskService taskService;
	
	
	public MainController(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}


	@GetMapping("/home")
	public String renderHomePage(HttpSession session, Model model) {
		// if userId is not in session -> logout route to protect the route
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		List<Task> taskList = taskService.allTasks();
		model.addAttribute("taskList", taskList);
		Task newTask = new Task();
//		newTask.setIsDone(true);
		model.addAttribute("newTask",newTask);
		return "home.jsp";
	}
	
	
	@PostMapping("/tasks/new")
	public String processCreateTask(
			HttpSession session, @Valid@ModelAttribute("newTask") Task newTask, BindingResult result) {
		if(result.hasErrors()) {
			return "home.jsp";
		}
		else {
			taskService.createTask(newTask);
			return "redirect:/home";
		}
	}
	@GetMapping("/tasks/{id}/edit")
	public String renderEditTaskPage(
			HttpSession session, @PathVariable("id") Long id,Model model
			) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Task oneTask = taskService.findOneTask(id);
		model.addAttribute("oneTask", oneTask);
		return "editTask.jsp";
	}
	
	@PutMapping("/tasks/{id}/edit")
	public String processEdit(
			@Valid @ModelAttribute("oneTask") Task oneTask, BindingResult result, Model model, HttpSession session
			) {
		if(result.hasErrors()) {
			return  "editTask.jsp";
		}
		else {
			taskService.updateTask(oneTask);
			return "redirect:/home";
		}
	}
	
	@DeleteMapping("/tasks/{id}/delete")
	public String processDelete(@PathVariable("id") Long id,HttpSession session) {
		if (session.getAttribute("userId") == null ) {
			return "redirect:/logout";
		}
		else {
			taskService.deleteTask(id);
			return "redirect:/home";
		}
	}
	
}
