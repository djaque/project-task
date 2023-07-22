package cl.taskmanager.adapter.out.persistence;

import cl.taskmanager.domain.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    void entityToDomain_ShouldMapCorrectly() {
        // Arrange
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1L);
        taskEntity.setSubject("Test Task");
        taskEntity.setCompleted(true);

        // Act
        Task task = TaskMapper.entityToDomain(taskEntity);

        // Assert
        assertEquals(1L, task.getId());
        assertEquals("Test Task", task.getSubject());
        assertEquals(true, task.isCompleted());
    }

    @Test
    void domainToEntity_ShouldMapCorrectly() {
        // Arrange
        Task task = new Task(2L, "Another Task", false);

        // Act
        TaskEntity taskEntity = TaskMapper.domainToEntity(task);

        // Assert
        assertEquals(2L, taskEntity.getId());
        assertEquals("Another Task", taskEntity.getSubject());
        assertEquals(false, taskEntity.isCompleted());
    }
}