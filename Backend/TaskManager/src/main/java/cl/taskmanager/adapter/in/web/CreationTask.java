package cl.taskmanager.adapter.in.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreationTask {

	@NotBlank(message = "Subject must have something inside")
	@Size(min = 2, max = 30, message = "Subject must have between 2 and 30 chars")
	private String subject;
}
