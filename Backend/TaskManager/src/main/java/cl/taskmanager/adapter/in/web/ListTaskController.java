package cl.taskmanager.adapter.in.web;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cl.taskmanager.application.port.in.TaskManagerPort;
import cl.taskmanager.domain.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class ListTaskController {

	private final TaskManagerPort taskManagerPort;
	
	public ListTaskController(TaskManagerPort taskManagerPort) {
		this.taskManagerPort = taskManagerPort;
	}
	@Operation(summary = "Get all tasks")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Tasks found", 
	    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)) }),
	  }
	)
	@GetMapping(path = "/task")
	public ArrayList<Task> getTasks() {
		return this.taskManagerPort.getAll();
	}
	
	@Operation(summary = "Create new task")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Task created", 
	    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)) }),
	  @ApiResponse(responseCode = "400", description = "Bad request, invalid subject validation", content = @Content), 
	 }
	)
	@PostMapping(path = "/task")
	public Task createNewTask(@RequestBody @Validated CreationTask create) {
		Task task = new Task();
		task.setSubject(create.getSubject());
		task.setCompleted(false);
		return this.taskManagerPort.create(task);
	}
	
	@Operation(summary = "Edit task by id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Task edited", 
	    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)) }),
	  @ApiResponse(responseCode = "400", description = "Bad request, invalid subject validation", content = @Content), 
	  @ApiResponse(responseCode = "404", description = "Task not found", content = @Content) 
	 }
	)
	
	@PutMapping(path = "/task/{id}")
	public Task updateTask(@PathVariable  Long id,
			@RequestBody @Validated UpdateTask update) {
		Task task = new Task();
		task.setId(id);
		task.setSubject(update.getSubject());
		task.setCompleted(update.getCompleted());
		try {
			return this.taskManagerPort.update(task);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Not Found", exc);
		}
	}
	
	@Operation(summary = "Delete task by id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Task edited", 
	    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)) }),
	  @ApiResponse(responseCode = "404", description = "Task not found", content = @Content) 
	 }
	)
	@DeleteMapping(path = "task/{id}")
	public void deleteTask(@PathVariable Long id, HttpServletResponse response) {
		Task task = new Task();
		task.setId(id);
		try {
			this.taskManagerPort.delete(task);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Not Found", exc);
		}
	
	}
	
}
