package cl.taskmanager.adapter.out.persistence;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	@Column(name = "updated_at", nullable = true, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updatedAt;
	
	@ManyToOne
    @JoinColumn(name="board_id", nullable=false)
	BoardEntity board;

	public TaskEntity(String subject) {
		this.subject = subject;
		this.completed = false;
	}

	public TaskEntity(String subject, Boolean completed) {
		this.subject = subject;
		this.completed = completed;
	}

	public TaskEntity(Long id, String subject, Boolean completed) {
		this.id = id;
		this.subject = subject;
		this.completed = completed;
	}

	public Boolean isCompleted() {
		return this.completed;
	}

	@PrePersist
	public void preSave() {
		if (this.createdAt == null) {
			this.createdAt = new Date();
		}
		this.updatedAt = new Date();
	}
}
