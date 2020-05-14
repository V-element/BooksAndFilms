package com.gnevanov.dao;

import com.gnevanov.models.Film;

import java.util.List;

public interface FilmDAO {
    void add(Film film);
    void delete(Film film);
    void edit(Film film);
    List<Film> getAllFilms();
    Film getFilmById(int id);
    List<Film> getFilmsByYear(short year);
}
