package com.gnevanov.dao;

import com.gnevanov.models.Book;
import javax.sql.DataSource;
import java.util.List;

public interface BookDAO {
    void setDataSource(DataSource dataSource);
    void add(Book book);
    void delete(int id);
    Book getBookById(int id);
    List<Book> getAllBooks();
    List<Book> getBooksByName(String name);
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByNameAndAuthor(String name, String author);
}
