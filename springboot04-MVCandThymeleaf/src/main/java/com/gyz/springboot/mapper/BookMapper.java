package com.gyz.springboot.mapper;

import com.gyz.springboot.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from t_book")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "t_name"),
            @Result(property = "writer",column = "t_writer"),
            @Result(property = "introduction",column = "t_introduction"),
    })
    public List<Book> findAll();

    @Insert("insert into t_book(id,t_name,t_writer,t_introduction) values (#{id},#{name},#{writer},#{introduction})")
    public void addBook(Book book);

    @Update("update t_book set t_name = #{name},t_writer = #{writer},t_introduction = #{introduction} where id = #{id}")
    public void updateBook(Book book);

    @Select("select * from t_book where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "t_name"),
            @Result(property = "writer",column = "t_writer"),
            @Result(property = "introduction",column = "t_introduction"),
    })
    public Book findById(Long id);

    @Delete("delete from t_book where id = #{id}")
    public void deleteBook(Long id);
}
