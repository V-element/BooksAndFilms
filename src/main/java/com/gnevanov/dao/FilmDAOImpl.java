package com.gnevanov.dao;

import com.gnevanov.models.Book;
import com.gnevanov.models.Film;
import com.gnevanov.utilities.FilmMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class FilmDAOImpl implements FilmDAO {

    private DataSource dataSource;
    private SessionFactory sessionFactory;
    private Environment environment;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void add(Film film) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "INSERT INTO schema_baf.films(id,name,producer,description,year) VALUES (?,?,?,?,?)";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, film.getId(),
                    film.getName(),
                    film.getProducer(),
                    film.getDescription(),
                    film.getYear());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.persist(film);
        }
    }

    @Transactional
    public void delete(Film film) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "DELETE FROM schema_baf.films WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, film.getId());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.delete(film);
        }
    }

    @Transactional
    public void update(Film film) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "UPDATE schema_baf.books SET name = ?, author = ?, description = ? WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql,
                    film.getName(),
                    film.getDescription(),
                    film.getProducer(),
                    film.getYear(),
                    film.getId());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.update(film);
        }
    }

    @Transactional
    public List<Film> getAllFilms() {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.films";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return jdbcTemplate.query(sql, new FilmMapper());
        } else {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Film").list();
        }
    }

    @Transactional
    public Film getFilmById(int id) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.films WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return (Film) jdbcTemplate.queryForObject(sql, new Object[]{id}, new FilmMapper());
        } else {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Film.class, id);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Film> getFilmsByYear(int year) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.films WHERE year = ?";
            return getListOfFilmsByParams(sql, new Object[]{year});
        } else {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Film WHERE year = :year");
            query.setParameter("year", year);
            return (List<Film>) query.list();
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Film> getFilmsByName(String name) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.films WHERE name LIKE ?";
            return getListOfFilmsByParams(sql, new Object[]{name});
        } else {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Film WHERE name LIKE :name");
            query.setParameter("name", name);
            return (List<Film>) query.list();
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Film> getFilmsByNameAndYear(String name, int year) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.films WHERE name LIKE ? AND year = ?";
            return getListOfFilmsByParams(sql, new Object[]{name, year});
        } else {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Film WHERE name LIKE :name AND year = :year");
            query.setParameter("name", name);
            query.setParameter("year", year);
            return (List<Film>) query.list();
        }
    }

    public List<Film> getListOfFilmsByParams(String sql, Object[] paramsArray) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Film> filmList = new ArrayList<Film>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, paramsArray);
        for (Map row : rows) {
            Film film = new Film();
            film.setId((Integer) row.get("id"));
            film.setName((String) row.get("name"));
            film.setDescription((String) row.get("description"));
            film.setProducer((String) row.get("producer"));
            film.setYear((int) row.get("year"));
            filmList.add(film);
        }

        return filmList;
    }
}
