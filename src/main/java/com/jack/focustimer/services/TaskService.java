package com.jack.focustimer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jack.focustimer.models.Task;
import com.jack.focustimer.repositories.TaskRepository;

@Service
public class TaskService {
	private final TaskRepository taskRepo;

	public TaskService(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}
	
	//methods:
	public List<Task> allTasks(){
		return taskRepo.findAll();
	}
	
	
	public Task findOneTask(Long id) {
		Optional<Task> optionalTask = taskRepo.findById(id);
		if(optionalTask.isPresent()) {
			return optionalTask.get();
		}
		else {
			return null;
		}
	}
	
	public Task createTask(Task task) {
		return taskRepo.save(task);
	}
	
	public Task updateTask(Task oneTask) {
		return taskRepo.save(oneTask);
	}
	
	public void deleteTask(Long id) {
		taskRepo.deleteById(id);
	}
	
}
