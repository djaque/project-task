package cl.taskmanager.application.port.out;

import cl.taskmanager.domain.Task;

public interface UpdateTaskPort {
	Task update(Task task) throws RuntimeException;
}
