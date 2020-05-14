package com.gnevanov.dao;

import com.gnevanov.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO{

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(Book book) {
        String sql = "INSERT INTO BOOKS(ID,NAME,AUTHOR,DESCRIPTION) VALUES (?,?,?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, new Object[]{book.getId(),
                                                book.getName(),
                                                book.getAuthor(),
                                                book.getDescription()});
    }

    public void delete(Book book) {
        String sql = "DELETE FROM BOOKS WHERE ID = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, book.getId());
    }

    public List<Book> getAllBooks() {
        return null;//jdbcTemplate.query("SELECT * FROM BOOKS",);
    }

    public Book getBookById(int id) {
        return null;
    }

    public List<Book> getBooksByName(String name) {
        return null;
    }

    public List<Book> getBooksByAuthor(String author) {
        return null;
    }

    public List<Book> getBooksByNameAndAuthor(String name, String author) {
        return null;
    }
}
