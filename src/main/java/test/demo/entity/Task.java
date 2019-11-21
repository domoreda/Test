package test.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    // 你把String类型定义为递增，咋递增啊
    private String taskName;

    private Date taskTime;

    private String paster;

    private Boolean difficulty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public String getPaster() {
        return paster;
    }

    public void setPaster(String paster) {
        this.paster = paster;
    }

    public Boolean getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Boolean difficulty) {
        this.difficulty = difficulty;
    }

    // 构造函数
    public Task(){
//1
    }
}
