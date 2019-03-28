package com.gyz.springboot.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 年龄
     */
    @Column(name = "t_age")
    private Integer age;

    /**
     * 生日
     */
    @Column(name = "t_birthday")
    private String birthday;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
