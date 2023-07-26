package cl.taskmanager.adapter.in.web;

import cl.taskmanager.application.port.in.TaskManagerPort;
import cl.taskmanager.domain.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ListTaskControllerTest {

	@Mock
	private TaskManagerPort taskManagerPort;

	@InjectMocks
	private ListTaskController listTaskController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getTasks_ShouldReturnAllTasks() {
		// Arrange
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(new Task(1L, "Task 1", false));
		tasks.add(new Task(2L, "Task 2", true));
		when(taskManagerPort.getAll()).thenReturn(tasks);

		// Act
		ArrayList<Task> result = listTaskController.getTasks();
		// Assert
		assertEquals(tasks, result);
	}

	@Test
	void createNewTask_ShouldReturnCreatedTask() {
		// Arrange
		CreationTask create = new CreationTask();
		create.setSubject("New Task");
		Task createdTask = new Task(1L, "New Task", false);
		when(taskManagerPort.create(any(Task.class))).thenReturn(createdTask);

		// Act
		Task result = listTaskController.createNewTask(create);

		// Assert
		assertEquals(createdTask, result);
	}

	@Test
	void updateTask_ShouldReturnUpdatedTask() throws Exception {
		// Arrange
		Long taskId = 1L;
		UpdateTask update = new UpdateTask();
		update.setSubject("Updated Task");
		update.setCompleted(true);
		Task updatedTask = new Task(taskId, "Updated Task", true);
		when(taskManagerPort.update(any(Task.class))).thenReturn(updatedTask);

		// Act
		Task result = listTaskController.updateTask(taskId, update);

		// Assert
		assertEquals(updatedTask, result);
	}

	@Test
	void updateTask_ShouldThrowResponseStatusException_WhenTaskNotFound() throws Exception {
		// Arrange
		Long taskId = 1L;
		UpdateTask update = new UpdateTask();
		update.setSubject("Updated Task");
		update.setCompleted(true);
		when(taskManagerPort.update(any(Task.class))).thenThrow(new RuntimeException("Task not found"));

		// Act & Assert
		assertThrows(ResponseStatusException.class, () -> listTaskController.updateTask(taskId, update));
	}

	@Test
	void deleteTask_ShouldCallDeleteTaskPort() throws Exception {
		// Arrange
		Long taskId = 1L;

		// Act
		listTaskController.deleteTask(taskId);

		// Assert
		verify(taskManagerPort, times(1)).delete(any(Task.class));
	}
/*
	@Test
	void deleteTask_ShouldThrowResponseStatusException_WhenTaskNotFound() {
		// Arrange
		Long taskId = 1L;
		when(taskManagerPort.delete(any(Task.class)))
		.thenThrow(new RuntimeException("Task not found"));

		// Act & Assert
		assertThrows(ResponseStatusException.class, () -> listTaskController.deleteTask(taskId, null));
	}
	*/
}
