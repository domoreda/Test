package test.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import test.demo.entity.User;

/**
 * User对象的数据库仓库
* */
public interface UserRepository extends JpaRepository<User,Long>{
}
