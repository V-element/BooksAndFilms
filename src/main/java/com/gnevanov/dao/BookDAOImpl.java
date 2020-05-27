package com.gnevanov.dao;

import com.gnevanov.models.Book;
import com.gnevanov.utilities.BookMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.*;

@Repository
@Transactional
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class BookDAOImpl implements BookDAO{

    private DataSource dataSource;
    private SessionFactory sessionFactory;
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(Book book) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "INSERT INTO schema_baf.books(id,name,author,description) VALUES (?,?,?,?)";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getDescription());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.persist(book);
        }
    }

    public void update(Book book) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "UPDATE schema_baf.books SET name = ?, author = ?, description = ? WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql,
                    book.getName(),
                    book.getAuthor(),
                    book.getDescription(),
                    book.getId());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.update(book);
        }
    }

    public void delete(Book book) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "DELETE FROM schema_baf.books WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, book.getId());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.delete(book);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Book> getAllBooks() {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return jdbcTemplate.query(sql, new BookMapper());
        } else {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Book").list();
        }
    }

    public Book getBookById(int id) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return (Book) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookMapper());
        } else {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Book.class, id);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> getBooksByName(String name) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books WHERE name LIKE ?";
            return getListOfBooksByParams(sql, new Object[]{name});
        } else {
            Session session = sessionFactory.getCurrentSession();
            return (List<Book>) session.get(Book.class, name);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> getBooksByAuthor(String author) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books WHERE author LIKE ?";
            return getListOfBooksByParams(sql, new Object[]{author});
        } else {
            Session session = sessionFactory.getCurrentSession();
            return (List<Book>) session.get(Book.class, author);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> getBooksByNameAndAuthor(String name, String author) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books WHERE name LIKE ? AND author LIKE ?";
            return getListOfBooksByParams(sql, new Object[]{name, author});
        } else {
            Session session = sessionFactory.getCurrentSession();
            return (List<Book>) session.get(Book.class, new Object[]{name, author});
        }
    }

    public List<Book> getListOfBooksByParams(String sql, Object[] paramsArray) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Book> bookList = new ArrayList<Book>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, paramsArray);
        for (Map row: rows) {
            Book book = new Book();
            book.setId((Integer) row.get("id"));
            book.setName((String)row.get("name"));
            book.setAuthor((String)row.get("author"));
            book.setDescription((String)row.get("description"));
            bookList.add(book);
        }

        return bookList;
    }
}
