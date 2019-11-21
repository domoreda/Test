package test.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import test.demo.Repository.TaskRepository;
import test.demo.Repository.UserRepository;
import test.demo.entity.Task;
import test.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
@RequestMapping("/task")
public class TaskContreller {
    // 使用这个注解把注入容器
    @Autowired
    TaskRepository taskRepository;
    @GetMapping("/getAllTasks")
    public ResponseEntity getAllTasks() {
        //获取Task数据库中所有的信息
        List<Task> list = taskRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if(task.getId() % 2 == 1){
                list.remove(i);
            }
        }
        return ResponseEntity.ok(list);
    }
    /*
    根据某个ID返回对应的对象信息
    有三个传参方式：
    1、@PathVarible.传参的形式直接跟随在路径后面：/getTaskByID/1
     */
    @GetMapping("/getTaskById{id}")
        public ResponseEntity getTaskById(@PathVariable Long id){
        Task task = taskRepository.getOne(id);
        return ResponseEntity.ok(task);

        }
    /**
     * 第二种传参方式:localhost:8080/user/getUserById_2?id=1
     * */
    @GetMapping("/getTaskById_2")
    public ResponseEntity getTaskById_2(@RequestParam(value = "id") Long id){
        Task task=taskRepository.getOne(id);
        return ResponseEntity.ok(task);
    }
    /**
     * 往数据库中插入一条数据：json（包括你要插入数据的属性）
     * {"name":"岳沁1","sex":"true"}
     * */
    @PostMapping("/getTaskById_3")
    public ResponseEntity getUserById_3(@RequestBody Task task){
        Task task1= taskRepository.saveAndFlush(task);
        return ResponseEntity.ok(task1);
    }
  /**
   * 根据Task的id查找他对应用户
  * */
  @Autowired
  UserRepository userRepository;
    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
       //遍历User  当User的task.id=这个id时，就输出user
        List<User> list = userRepository.findAll();
        List<User> list1 = new ArrayList<User>();
        for(int i = 0; i < list.size() ;i++){
            User user = list.get(i);
            if(user.getTask().getId() == id){
                list1.add(user);
            }
        }
        //List<User> list=taskRepository.findUserByTaskId(id);


        return ResponseEntity.ok(list1);

    }


}
