package com.gyz.springboot.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BookComponent {
    /**
     * 作者
     */
    @Value("gyz")
    private String author;

    /**
     * 书名
     */
    @Value("《springBoot》")
    private String Name;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
