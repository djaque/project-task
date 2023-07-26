package cl.taskmanager.application.port.out.task;

import cl.taskmanager.domain.Task;

public interface UpdateTaskPort {
	Task update(Task task) throws RuntimeException;
}
