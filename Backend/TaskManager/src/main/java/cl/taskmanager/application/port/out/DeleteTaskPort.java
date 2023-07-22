package cl.taskmanager.application.port.out;

import cl.taskmanager.domain.Task;

public interface DeleteTaskPort {
	void delete(Task task) throws RuntimeException;
}
