package cl.taskmanager.adapter.out.persistence;

import java.util.ArrayList;

import cl.taskmanager.application.port.out.task.CreateTaskPort;
import cl.taskmanager.application.port.out.task.DeleteTaskPort;
import cl.taskmanager.application.port.out.task.GetTaskPort;
import cl.taskmanager.application.port.out.task.ListTaskPort;
import cl.taskmanager.application.port.out.task.UpdateTaskPort;
import cl.taskmanager.common.PersistenceAdapter;
import cl.taskmanager.domain.Task;

@PersistenceAdapter
public class TaskPersistenceAdapter implements CreateTaskPort, UpdateTaskPort, DeleteTaskPort, ListTaskPort, GetTaskPort {

	private final SpringTaskRepository taskRepository;

	public TaskPersistenceAdapter(SpringTaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public ArrayList<Task> getTasks() {
		ArrayList<TaskEntity> taskEntities = (ArrayList<TaskEntity>) taskRepository.findAll();
		ArrayList<Task> taskList = new ArrayList<Task>();
		for (TaskEntity taskEntity : taskEntities) {
			taskList.add(TaskMapper.entityToDomain(taskEntity));
		}
		return taskList;
	}
	
	@Override
	public Task getTaskById(Long id) throws RuntimeException {
		TaskEntity taskOld = taskRepository.getReferenceById(id);
		if (taskOld == null) {
			throw new RuntimeException("ID not found");
		}
		return TaskMapper.entityToDomain(taskOld);
	}

	@Override
	public void delete(Task task) {
		taskRepository.deleteById(task.getId());
	}

	@Override
	public Task update(Task task) {
		return TaskMapper.entityToDomain(
			taskRepository.save(
				TaskMapper.domainToEntity(task)
			)
		);
	}

	@Override
	public Task create(Task task) {
		return TaskMapper.entityToDomain(taskRepository.save(TaskMapper.domainToEntity(task)));
	}

}
