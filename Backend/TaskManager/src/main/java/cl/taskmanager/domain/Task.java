package cl.taskmanager.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

	private Long id;
	private String subject;
	private Boolean completed;
	private Date createdAt;
	private Date updatedAt;
	
	public Task(String subject) {
		this.subject = subject;
		this.completed = false;
	}

	public Task(String subject, Boolean completed) {
		this.subject = subject;
		this.completed = completed;
	}

	public Task(Long id, String subject, Boolean completed) {
		this.id = id;
		this.subject = subject;
		this.completed = completed;
	}

	public Boolean isCompleted() {
		return this.completed;
	}
}
