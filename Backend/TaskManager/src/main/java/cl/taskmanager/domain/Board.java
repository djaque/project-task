package cl.taskmanager.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Board {
    private Long id;
    private String subject;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    List<Task> tasks;

    public void addTask(Task task) {
        if (this.tasks == null) {
            this.tasks = new java.util.ArrayList<Task>();
        }
        this.tasks.add(task);
    }
}
