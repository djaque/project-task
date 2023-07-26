package cl.taskmanager.application.service;

import java.util.ArrayList;

import cl.taskmanager.application.port.in.TaskManagerPort;
import cl.taskmanager.application.port.out.board.GetBoardPort;
import cl.taskmanager.application.port.out.task.CreateTaskPort;
import cl.taskmanager.application.port.out.task.DeleteTaskPort;
import cl.taskmanager.application.port.out.task.GetTaskPort;
import cl.taskmanager.application.port.out.task.ListTaskPort;
import cl.taskmanager.application.port.out.task.UpdateTaskPort;
import cl.taskmanager.common.UseCase;
import cl.taskmanager.domain.Board;
import cl.taskmanager.domain.Task;
import jakarta.transaction.Transactional;

@UseCase
public class TaskManagerService implements TaskManagerPort {

	private final ListTaskPort listTaskPort;
	private final CreateTaskPort createTaskPort;
	private final UpdateTaskPort updateTaskPort;
	private final DeleteTaskPort deleteTaskPort;
	private final GetTaskPort getTaskPort;
	private final GetBoardPort getBoardPort;

	public TaskManagerService(ListTaskPort listTaskPort, CreateTaskPort createTaskPort, UpdateTaskPort updateTaskPort,
			DeleteTaskPort deleteTaskPort, GetTaskPort getTaskPort, GetBoardPort getBoardPort) {
		this.listTaskPort = listTaskPort;
		this.createTaskPort = createTaskPort;
		this.updateTaskPort = updateTaskPort;
		this.deleteTaskPort = deleteTaskPort;
		this.getTaskPort = getTaskPort;
		this.getBoardPort = getBoardPort;
	}

	@Override
	public ArrayList<Task> getAll() {
		return listTaskPort.getTasks();
	}

	@Transactional
	@Override
	public Task create(Task task) {
		if (task.getBoardId() != null) {
			Board board = getBoardPort.getBoardById(task.getBoardId());
			if (board == null) {
				throw new RuntimeException("Board not found");
			}
			task.setBoard(board);
		}
		return createTaskPort.create(task);
	}

	@Transactional
	@Override
	public Task update(Task task) {
		Task original = getTaskPort.getTaskById(task.getId());
		original.setSubject(task.getSubject());
		original.setCompleted(task.getCompleted());
		if (task.getBoardId() != null) {
			Board board = getBoardPort.getBoardById(task.getBoardId());
			if (board == null) {
				throw new RuntimeException("Board not found");
			}
			original.setBoard(board);
		}
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
