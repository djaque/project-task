package cl.taskmanager.application.port.out.task;

import cl.taskmanager.domain.Task;

public interface DeleteTaskPort {
	void delete(Task task) throws RuntimeException;
}
