package cl.taskmanager.adapter.out.persistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringBoardRepository extends JpaRepository<BoardEntity, Long>{
    
}
