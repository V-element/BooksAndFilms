package com.gnevanov.dao;

import com.gnevanov.models.Film;
import com.gnevanov.utilities.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FilmDAOImpl implements FilmDAO{

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Film film) {
        String sql = "INSERT INTO schema_baf.films(id,name,producer,description,year) VALUES (?,?,?,?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, film.getId(),
                film.getName(),
                film.getProducer(),
                film.getDescription(),
                film.getYear());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM schema_baf.films WHERE id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Film film) {
        String sql = "UPDATE schema_baf.books SET name = ?, author = ?, description = ? WHERE id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql,
                film.getName(),
                film.getDescription(),
                film.getProducer(),
                film.getYear(),
                film.getId());
    }

    @Override
    public List<Film> getAllFilms() {
        String sql = "SELECT * FROM schema_baf.films";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(sql, new FilmMapper());
    }

    @Override
    public Film getFilmById(int id) {
        String sql = "SELECT * FROM schema_baf.films WHERE id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return (Film) jdbcTemplate.queryForObject(sql, new Object[]{id}, new FilmMapper());
    }
    
    @Override
    public List<Film> getFilmsByYear(short year) {
        String sql = "SELECT * FROM schema_baf.films WHERE year = ?";
        return getListOfFilmsByParams(sql, new Object[]{year});
    }

    @Override
    public List<Film> getFilmsByName(String name) {
        String sql = "SELECT * FROM schema_baf.films WHERE name LIKE ?";
        return getListOfFilmsByParams(sql, new Object[]{name});
    }

    @Override
    public List<Film> getFilmsByNameAndYear(String name, short year) {
        String sql = "SELECT * FROM schema_baf.films WHERE name LIKE ? AND year = ?";
        return getListOfFilmsByParams(sql, new Object[]{name, year});
    }

    public List<Film> getListOfFilmsByParams(String sql, Object[] paramsArray) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Film> filmList = new ArrayList<Film>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, paramsArray);
        for (Map row: rows) {
            Film film = new Film();
            film.setId((Integer) row.get("id"));
            film.setName((String)row.get("name"));
            film.setDescription((String)row.get("description"));
            film.setProducer((String)row.get("producer"));
            film.setYear((short)row.get("year"));
            filmList.add(film);
        }

        return filmList;
    }
}
