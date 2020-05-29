package com.gnevanov.services;

import com.gnevanov.dao.FilmDAO;
import com.gnevanov.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class FilmServiceImpl implements FilmService {

    private FilmDAO filmDAO;

    @Autowired
    public void setFilmDAO(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    @Transactional
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Override
    @Transactional
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Override
    @Transactional
    public void update(Film film) {
        filmDAO.update(film);
    }

    @Override
    @Transactional
    public List<Film> getAllFilms() {
        return filmDAO.getAllFilms();
    }

    @Override
    @Transactional
    public Film getFilmById(int id) {
        return filmDAO.getFilmById(id);
    }

    @Override
    @Transactional
    public List<Film> getFilmsByName(String name) {
        return filmDAO.getFilmsByName(name);
    }

    @Override
    @Transactional
    public List<Film> getFilmsByYear(int year) {
        return filmDAO.getFilmsByYear(year);
    }

    @Override
    @Transactional
    public List<Film> getFilmsByNameAndYear(String name, int year) {
        return filmDAO.getFilmsByNameAndYear(name, year);
    }
}
