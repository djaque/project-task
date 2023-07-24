package cl.taskmanager.application.port.in;

import java.util.ArrayList;

import cl.taskmanager.domain.Task;

public interface TaskManagerPort {

	ArrayList<Task> getAll();
	Task create(Task task);
	Task update(Task task) throws Exception;
	void delete(Task task) throws Exception;
	Task get(Long id) throws Exception;
	Task complete(Long id) throws Exception;	
}
