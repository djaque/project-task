package cl.taskmanager.application.service;

import java.util.ArrayList;

import cl.taskmanager.application.port.in.TaskManagerPort;
import cl.taskmanager.application.port.out.CreateTaskPort;
import cl.taskmanager.application.port.out.ListTaskPort;
import cl.taskmanager.application.port.out.UpdateTaskPort;
import cl.taskmanager.application.port.out.DeleteTaskPort;
import cl.taskmanager.application.port.out.GetTaskPort;
import cl.taskmanager.common.UseCase;
import cl.taskmanager.domain.Task;
import jakarta.transaction.Transactional;

@UseCase
public class TaskManagerService implements TaskManagerPort {

	private final ListTaskPort listTaskPort;
	private final CreateTaskPort createTaskPort;
	private final UpdateTaskPort updateTaskPort;
	private final DeleteTaskPort deleteTaskPort;
	private final GetTaskPort getTaskPort;

	public TaskManagerService(ListTaskPort listTaskPort, CreateTaskPort createTaskPort, UpdateTaskPort updateTaskPort,
			DeleteTaskPort deleteTaskPort, GetTaskPort getTaskPort) {
		this.listTaskPort = listTaskPort;
		this.createTaskPort = createTaskPort;
		this.updateTaskPort = updateTaskPort;
		this.deleteTaskPort = deleteTaskPort;
		this.getTaskPort = getTaskPort;
	}

	@Override
	public ArrayList<Task> getAll() {
		return listTaskPort.getTasks();
	}

	@Transactional
	@Override
	public Task create(Task task) {
		return createTaskPort.create(task);
	}

	@Transactional
	@Override
	public Task update(Task task) {
		Task original = getTaskPort.getTaskById(task.getId());
		original.setSubject(task.getSubject());
		original.setCompleted(task.getCompleted());
		return updateTaskPort.update(original);
	}

	@Transactional
	@Override
	public void delete(Task task){
		Task original = getTaskPort.getTaskById(task.getId());
		deleteTaskPort.delete(original);
	}

	@Override
	public Task get(Long id) throws Exception {
		return getTaskPort.getTaskById(id);
	}

	@Override
	public Task complete(Long id) throws Exception {
		Task original = getTaskPort.getTaskById(id);
		original.setCompleted(true);
		return updateTaskPort.update(original);
	}

}
