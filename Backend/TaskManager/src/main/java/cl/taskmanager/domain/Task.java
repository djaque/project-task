package cl.taskmanager.domain;

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
	
	public Boolean isCompleted() {
		return this.completed;
	}
}
