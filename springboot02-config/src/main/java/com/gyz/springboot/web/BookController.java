package com.gyz.springboot.web;

import com.gyz.springboot.pojo.BookComponent;
import com.gyz.springboot.pojo.BookProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @Autowired
    BookComponent bookComponent;

    @Autowired
    BookProperties bookProperties;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @ResponseBody
    public String say() {
        //return bookComponent.getAuthor() + " write " + bookComponent.getName();
        return bookProperties.getAuthor() + " write " + bookProperties.getName();
    }
}
