package com.gyz.springboot.service.impl;

import com.gyz.springboot.mapper.BookMapper;
import com.gyz.springboot.pojo.Book;
import com.gyz.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public void insertByBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public void update(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void delete(Long id) {
        bookMapper.deleteBook(id);
    }

    @Override
    public Book findById(Long id) {
        return bookMapper.findById(id);
    }
}
