package cl.taskmanager.adapter.out.persistence;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;


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

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	
	public Boolean isCompleted() {
		return this.completed;
	}

	@PrePersist
	public void createdAt() {
		this.createdAt = new Date();
	}
}
