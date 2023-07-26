package cl.taskmanager.adapter.in.web;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardCreation {

    @NotBlank(message = "Subject must have something inside")
    private String subject;
    @NotBlank(message = "Description must have something inside")
    private String description;
}
