package com.gnevanov.dao;

import com.gnevanov.models.Book;
import com.gnevanov.utilities.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Book book) {
        String sql = "INSERT INTO books(id,name,author,description) VALUES (?,?,?,?)";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, book.getId(),
                                    book.getName(),
                                    book.getAuthor(),
                                    book.getDescription());
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE books SET name = ?, author = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                book.getName(),
                book.getAuthor(),
                book.getDescription(),
                book.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, id);
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return (Book) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookMapper());
    }

    public List<Book> getBooksByName(String name) {
        String sql = "SELECT * FROM books WHERE name LIKE ?";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List list = jdbcTemplate.queryForList(sql, new Object[]{name}, new BookMapper());
        List<Book> bookList = new ArrayList<Book>();
        for (Object obj: list) {
            bookList.add((Book) obj);
        }
        return bookList;
    }

    public List<Book> getBooksByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author LIKE ?";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List list = jdbcTemplate.queryForList(sql, new Object[]{author}, new BookMapper());
        List<Book> bookList = new ArrayList<Book>();
        for (Object obj: list) {
            bookList.add((Book) obj);
        }
        return bookList;
    }

    public List<Book> getBooksByNameAndAuthor(String name, String author) {
        String sql = "SELECT * FROM books WHERE name LIKE ? AND author LIKE ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List list = jdbcTemplate.queryForList(sql, new Object[]{name, author}, new BookMapper());
        List<Book> bookList = new ArrayList<Book>();
        for (Object obj: list) {
            bookList.add((Book) obj);
        }
        return bookList;
    }
}
