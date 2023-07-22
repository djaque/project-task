package cl.taskmanager.application.port.out;

import cl.taskmanager.domain.Task;

public interface GetTaskPort {
	Task getTaskById(Long id) throws RuntimeException;
}
