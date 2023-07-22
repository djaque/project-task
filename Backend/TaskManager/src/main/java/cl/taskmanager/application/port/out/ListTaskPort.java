package cl.taskmanager.application.port.out;

import java.util.ArrayList;

import cl.taskmanager.domain.Task;

public interface ListTaskPort {
	
	ArrayList<Task> getTasks();
	
}
