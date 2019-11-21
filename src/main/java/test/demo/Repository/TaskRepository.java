package test.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.demo.entity.Task;
import test.demo.entity.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long>{

   /* @Query(value = "select * from user where task_id=?1",nativeQuery=true)
    List<User> findUserByTaskId(Long id);*/

}
