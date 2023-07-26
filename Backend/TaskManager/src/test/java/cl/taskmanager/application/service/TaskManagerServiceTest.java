package cl.taskmanager.application.service;

import cl.taskmanager.application.port.out.task.CreateTaskPort;
import cl.taskmanager.application.port.out.task.DeleteTaskPort;
import cl.taskmanager.application.port.out.task.GetTaskPort;
import cl.taskmanager.application.port.out.task.ListTaskPort;
import cl.taskmanager.application.port.out.task.UpdateTaskPort;
import cl.taskmanager.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskManagerServiceTest {

    @Mock
    private ListTaskPort listTaskPort;

    @Mock
    private CreateTaskPort createTaskPort;

    @Mock
    private UpdateTaskPort updateTaskPort;

    @Mock
    private DeleteTaskPort deleteTaskPort;

    @Mock
    private GetTaskPort getTaskPort;

    @InjectMocks
    private TaskManagerService taskManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks_ShouldReturnAllTasks() {
        // Arrange
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", false));
        tasks.add(new Task(2L, "Task 2", true));
        when(listTaskPort.getTasks()).thenReturn(tasks);

        // Act
        ArrayList<Task> result = taskManagerService.getAll();

        // Assert
        assertEquals(tasks, result);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() {
        // Arrange
        Task taskToCreate = new Task(null, "New Task", false);
        Task createdTask = new Task(1L, "New Task", false);
        when(createTaskPort.create(taskToCreate)).thenReturn(createdTask);

        // Act
        Task result = taskManagerService.create(taskToCreate);

        // Assert
        assertEquals(createdTask, result);
    }

    @Test
    void updateTask_ShouldReturnUpdatedTask() {
        // Arrange
        Task originalTask = new Task(1L, "Task 1", false);
        Task updatedTask = new Task(1L, "Updated Task", true);
        when(getTaskPort.getTaskById(1L)).thenReturn(originalTask);
        when(updateTaskPort.update(originalTask)).thenReturn(updatedTask);

        // Act
        Task result = taskManagerService.update(updatedTask);

        // Assert
        assertEquals(updatedTask, result);
        assertEquals("Updated Task", result.getSubject());
        assertEquals(true, result.isCompleted());
    }

    @Test
    void deleteTask_ShouldCallDeleteTaskPort() {
        // Arrange
        Task taskToDelete = new Task(1L, "Task 1", false);
        when(getTaskPort.getTaskById(1L)).thenReturn(taskToDelete);

        // Act
        taskManagerService.delete(taskToDelete);

        // Assert
        verify(deleteTaskPort, times(1)).delete(taskToDelete);
    }
}