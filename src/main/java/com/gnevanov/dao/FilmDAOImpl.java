package com.gnevanov.dao;

import com.gnevanov.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class FilmDAOImpl implements FilmDAO{

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

    @Override
    public void add(Film film) {
        String sql = "INSERT INTO films(id,name,producer,description,year) VALUES (?,?,?,?)";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, film.getId(),
                film.getName(),
                film.getProducer(),
                film.getDescription(),
                film.getYear());
    }

    @Override
    public void delete(Film film) {

    }

    @Override
    public void update(Film film) {

    }

    @Override
    public List<Film> getAllFilms() {
        return null;
    }

    @Override
    public Film getFilmById(int id) {
        return null;
    }
    
    @Override
    public List<Film> getFilmsByYear(short year) {
        return null;
    }

    @Override
    public List<Film> getFilmsByName(String name) {
        return null;
    }

    @Override
    public List<Film> getFilmsByNameAndYear(String name, short year) {
        return null;
    }
}
