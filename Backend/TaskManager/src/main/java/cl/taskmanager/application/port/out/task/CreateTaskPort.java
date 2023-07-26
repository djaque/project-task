package cl.taskmanager.application.port.out.task;

import cl.taskmanager.domain.Task;

public interface CreateTaskPort {
		Task create(Task task);
}
