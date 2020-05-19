package com.gnevanov.dao;

import com.gnevanov.models.Film;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

public class FilmDAOImpl implements FilmDAO{

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(Film film) {

    }

    public void delete(Film film) {

    }

    public void edit(Film film) {

    }

    public List<Film> getAllFilms() {
        return null;
    }

    public Film getFilmById(int id) {
        return null;
    }

    public List<Film> getFilmsByYear(short year) {
        return null;
    }
}
