package test.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{
}
