package test.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User实体类，和数据库的字段是相对应的  依赖添加完成要点更新，就是那个圆圈，或者刚才左下角的
* */
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class User {
    /**
     * create table user (id bigint not null auto_increment, age integer not null, name varchar(255),
     * password varchar(255), sex bit not null, primary key (id))
    * */

    /*
    指定id为主键，并设置为自增长
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String name;

    private String password;

    private int age;

    private boolean sex;
    //alter table user add column nick_name varchar(255)
    private String nickName;

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

//我的谷歌 老是  网页报错 加载不出来  如果是String  就不影响对吧  刚如果是我写 我也不会写equals