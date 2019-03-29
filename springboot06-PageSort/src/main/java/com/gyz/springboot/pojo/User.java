package com.gyz.springboot.pojo;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2,max = 8,message = "姓名长度最短2，最长8")
    private String name;

    /**
     * 年龄
     */
    @Column(name = "t_age")
    @NotNull(message = "年龄不能为空")
    @Max(value = 200,message = "年龄最大200岁")
    @Min(value = 0,message = "年龄最小0岁")
    private Integer age;

    /**
     * 生日
     */
    @Column(name = "t_birthday")
    @NotEmpty(message = "生日不能为空")
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
