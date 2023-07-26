package cl.taskmanager.adapter.out.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "board")
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String subject;
    private String description;

    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "board", targetEntity = TaskEntity.class)
    List<TaskEntity> tasks;

    public BoardEntity(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }

    public BoardEntity(Long id, String subject, String description) {
        this.id = id;
        this.subject = subject;
        this.description = description;
    }

    @PrePersist
    public void preSave() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        this.updatedAt = new Date();
    }

    public void addTask(TaskEntity taskEntity) {
        if (this.tasks == null) {
            this.tasks = new ArrayList<TaskEntity>();
        }
        this.tasks.add(taskEntity);
    }
}
