package com.gyz.springboot.service;

import com.gyz.springboot.pojo.Book;

import java.util.List;

public interface BookService {
    /**
     * 获取所有 Book
     */
    List<Book> findAll();

    /**
     * 新增 Book
     *
     * @param book {@link Book}
     */
    void insertByBook(Book book);

    /**
     * 更新 Book
     *
     * @param book {@link Book}
     */
    void update(Book book);

    /**
     * 删除 Book
     *
     * @param id 编号
     */
    void delete(Long id);

    /**
     * 获取 Book
     *
     * @param id 编号
     */
    Book findById(Long id);
}
