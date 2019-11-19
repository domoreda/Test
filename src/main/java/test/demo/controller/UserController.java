package test.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.demo.Repository.UserRepository;
import test.demo.entity.User;

import java.util.List;
@RepositoryRestController
@RequestMapping("/user")
public class UserController {
    //  @Autowired  @Resource
    // 使用这个注解把注入容器
    @Autowired  //
    UserRepository userRepository;
    /**
     * 获取user下的所有人员信息
    * */
    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers(){
        // 获取这个User对象对应数据库中的所有信息
        // ==和equals
        List<User > list= userRepository.findAll();
        for(int i = 0;i < list.size() ;i++){
            User user = list.get(i);
            if(user.getId()==2 ){
                list.remove(i);
                break;
            }
        }
        return ResponseEntity.ok(list);
    }
    /**
     * 根据某个Id返回对应的对象信息
     *有三种传参方式:
     * 1.@PathVariable，传参的形式直接跟随在路径后面：/getUserById/1
     * 2.
    * */
    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        User user=userRepository.getOne(id);
        return ResponseEntity.ok(user);
    }

    /**
     * 第二种传参方式:localhost:8080/user/getUserById_2?id=1
    * */
    @GetMapping("/getUserById_2")
    public ResponseEntity getUserById_2(@RequestParam(value = "id") Long id){
        User user=userRepository.getOne(id);
        return ResponseEntity.ok(user);
    }
   /**
   * 往数据库中插入一条数据：json（包括你要插入数据的属性）
    * {"name":"岳沁1","sex":"true"}
  * */
    @PostMapping("/getUserById_3")
    public ResponseEntity getUserById_3(@RequestBody User user){
       User user1= userRepository.saveAndFlush(user);
        return ResponseEntity.ok(user1);
    }


}
//为啥equals不行 我查过 嗯 对   JVM：底层的i一些JAVA虚拟机(存储，垃圾) JRE：运行环境 JDK：依赖
//