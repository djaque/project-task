package cl.taskmanager.adapter.out.persistence;

import cl.taskmanager.domain.Task;

public class TaskMapper {

	public static Task entityToDomain(TaskEntity taskEntity) {
		Task task = new Task();
		task.setId(taskEntity.getId());
		task.setSubject(taskEntity.getSubject());
		task.setCompleted(taskEntity.getCompleted());
		task.setCreatedAt(taskEntity.getCreatedAt());
		task.setUpdatedAt(taskEntity.getUpdatedAt());
		return task;
	}

	public static TaskEntity domainToEntity(Task task) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(task.getId());
		taskEntity.setSubject(task.getSubject());
		taskEntity.setCompleted(task.getCompleted());
		taskEntity.setCreatedAt(task.getCreatedAt());
		taskEntity.setUpdatedAt(task.getUpdatedAt());
		if (task.getBoard() != null) {
			taskEntity.setBoard(
				BoardMapper.domainToEntity(
					task.getBoard()
				)
			);
		}
		return taskEntity;
	}
}
