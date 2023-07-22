package cl.taskmanager.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringTaskRepository extends JpaRepository<TaskEntity, Long> {

}
