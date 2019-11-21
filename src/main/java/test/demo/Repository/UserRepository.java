package test.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import test.demo.entity.User;

import java.util.List;

/**
 * User对象的数据库仓库
* */
public interface UserRepository extends JpaRepository<User,Long>{

    @Query(value = "select * from user where name=?1" ,nativeQuery=true)
    List<User> findUserByUserName(String name);
    /**
     * 模糊查询sql写法  ykk ykk113 123ykk123
    * */
    @Query(value = "select * from user where like name '?1%'",nativeQuery = true)
    List<User> findUserLikeName(String name);
    /**
     * 排序Sql写法：order by
    * */
    // @Query(value = "select * from user  order by age",nativeQuery = true)
}
