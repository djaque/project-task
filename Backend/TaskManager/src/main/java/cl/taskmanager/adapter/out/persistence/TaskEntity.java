package cl.taskmanager.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private String subject;
	
	private Boolean completed;
	
	public Boolean isCompleted() {
		return this.completed;
	}
}
