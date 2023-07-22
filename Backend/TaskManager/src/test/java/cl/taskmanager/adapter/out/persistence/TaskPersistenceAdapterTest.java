package cl.taskmanager.adapter.out.persistence;

import cl.taskmanager.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskPersistenceAdapterTest {

    @Mock
    private SpringTaskRepository taskRepository;

    @InjectMocks
    private TaskPersistenceAdapter taskPersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTasks_ShouldReturnListOfTasks() {
        // Arrange
        List<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(new TaskEntity(1L, "Task 1", true));
        taskEntities.add(new TaskEntity(2L, "Task 2", false));

        when(taskRepository.findAll()).thenReturn(taskEntities);

        // Act
        ArrayList<Task> tasks = taskPersistenceAdapter.getTasks();

        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getSubject());
        assertTrue(tasks.get(0).isCompleted());
        assertEquals("Task 2", tasks.get(1).getSubject());
        assertFalse(tasks.get(1).isCompleted());
    }

    @Test
    void getTaskById_ExistingId_ShouldReturnTask() {
        // Arrange
        TaskEntity taskEntity = new TaskEntity(1L, "Test Task", true);
        when(taskRepository.getReferenceById(1L)).thenReturn(taskEntity);

        // Act
        Task task = taskPersistenceAdapter.getTaskById(1L);

        // Assert
        assertNotNull(task);
        assertEquals(1L, task.getId());
        assertEquals("Test Task", task.getSubject());
        assertTrue(task.isCompleted());
    }

    @Test
    void getTaskById_NonExistingId_ShouldThrowException() {
        // Arrange
        when(taskRepository.getReferenceById(1L)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> taskPersistenceAdapter.getTaskById(1L));
    }

    @Test
    void delete_ShouldDeleteTask() {
        // Arrange
        Task task = new Task(1L, "Test Task", true);

        // Act
        taskPersistenceAdapter.delete(task);

        // Assert
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void update_ShouldUpdateTask() {
        // Arrange
        Task task = new Task(1L, "Test Task", true);
        TaskEntity updatedTaskEntity = new TaskEntity(1L, "Updated Task", false);

        when(taskRepository.save(any(TaskEntity.class))).thenReturn(updatedTaskEntity);

        // Act
        Task updatedTask = taskPersistenceAdapter.update(task);

        // Assert
        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getSubject());
        assertFalse(updatedTask.isCompleted());
    }

    @Test
    void create_ShouldCreateTask() {
        // Arrange
        Task task = new Task(null, "New Task", false);
        TaskEntity savedTaskEntity = new TaskEntity(1L, "New Task", false);

        when(taskRepository.save(any(TaskEntity.class))).thenReturn(savedTaskEntity);

        // Act
        Task createdTask = taskPersistenceAdapter.create(task);

        // Assert
        assertNotNull(createdTask);
        assertEquals(1L, createdTask.getId());
        assertEquals("New Task", createdTask.getSubject());
        assertFalse(createdTask.isCompleted());
    }
}
