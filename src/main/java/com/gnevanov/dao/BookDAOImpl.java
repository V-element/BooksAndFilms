package com.gnevanov.dao;

import com.gnevanov.models.Book;
import com.gnevanov.utilities.BookMapper;
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
        String sql = "SELECT * FROM BOOKS";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM BOOKS WHERE ID = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return (Book) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookMapper());
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
