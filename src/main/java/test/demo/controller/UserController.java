package test.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.demo.Repository.UserRepository;
import test.demo.entity.User;

import java.util.*;

@RepositoryRestController
@RequestMapping("/user")
public class UserController {
    //  @Autowired  @Resource
    // 使用这个注解把注入容器
    @Autowired
    UserRepository userRepository;

    /**
     * 获取user下的所有人员信息
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers() {
        // 获取这个User对象对应数据库中的所有信息
        // ==和equals
        List<User> list = userRepository.findAll();
        Map<User, User> map = new HashMap<User, User>();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getId() == 2) {
                list.remove(i);
                break;
            }
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 根据某个Id返回对应的对象信息
     * 有三种传参方式:
     * 1.@PathVariable，传参的形式直接跟随在路径后面：/getUserById/1
     * 2.
     */
    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        User user = userRepository.getOne(id);
        return ResponseEntity.ok(user);
    }

    /**
     * 第二种传参方式:localhost:8080/user/getUserById_2?id=1
     */
    @GetMapping("/getUserById_2")
    public ResponseEntity getUserById_2(@RequestParam(value = "id") Long id) {
        User user = userRepository.getOne(id);
        return ResponseEntity.ok(user);
    }

    /**
     * 往数据库中插入一条数据：json（包括你要插入数据的属性）
     * {"name":"岳沁1","sex":"true"}
     */
    @PostMapping("/getUserById_3")
    public ResponseEntity getUserById_3(@RequestBody User user) {
        User user1 = userRepository.saveAndFlush(user);

        return ResponseEntity.ok(user1);
    }


    @GetMapping("/getUserIdAndName")
    public ResponseEntity getUserIdAndName() {
        // 获取这个User对象对应数据库中的所有信息
        List<User> list = userRepository.findAll();
        Map<Long, String> map = new HashMap<Long, String>();
        for (int i = 0; i < list.size(); i++) {
            //然后我想得到ID和name，放到map里面
            User user = list.get(i);
            Long ID = user.getId();
            String name = user.getName();
            map.put(ID, name);
        }

        return ResponseEntity.ok(map);
    }

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity getUserByName(@PathVariable String name) {

        List<User> list = userRepository.findAll();
        List<User> list1 = new ArrayList<User>();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String a = user.getName();
            if (a.contains(name)) {
                list1.add(user);
            }
        }
        //List<User> list=taskRepository.findUserByTaskId(id);


        return ResponseEntity.ok(list1);

    }

    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser() {
        //实现一个排序功能，根据User表中的age属性对User表中的数据进行从大到小排序
        List<User> list = userRepository.findAll();
        List<User> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            list1.add(user);
        }
        System.out.println(list1);
        Comparator<User> a = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                User user1= (User) o1;
                User user2= (User) o2;

                if (user1.getAge() <= user2.getAge())
                    return 1;
                else
                    return -1;
            }
        };
/*        Collections.sort(list1, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getAge() >= o2.getAge())
                    return 1;
                else
                    return -1;
            }
        });*/
        Collections.sort(list1,a);
        System.out.println(list1);
        return ResponseEntity.ok(list1);
    }
    @GetMapping("testSql")
    public ResponseEntity testSql(@RequestParam(value = "name")String name){
        List<User> list=userRepository.findUserByUserName(name);
        return ResponseEntity.ok(list);
    }
}

        /* for(int i = 0;i < list.size()-1 ;i++){
            for(int j = i+1;j < list.size();j++){
                User user = list.get(i);
                User user1 = list.get(j) ;
                int a = user.getAge();
                int b = user1.getAge() ;
                User temp;
                if(a < b){
                    temp = user;
                    user = user1;
                    user1 = temp;
                }
            }
        }
        return ResponseEntity.ok(list);
    }


}*/
//为啥equals不行 我查过 嗯 对   JVM：底层的i一些JAVA虚拟机(存储，垃圾) JRE：运行环境 JDK：依赖
