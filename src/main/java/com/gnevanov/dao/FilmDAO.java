package com.gnevanov.dao;

import com.gnevanov.models.Film;

import java.util.List;

public interface FilmDAO {
    void add(Film film);
    void delete(int id);
    void update(Film film);
    List<Film> getAllFilms();
    Film getFilmById(int id);
    List<Film> getFilmsByName(String name);
    List<Film> getFilmsByYear(int year);
    List<Film> getFilmsByNameAndYear(String name, int year);
}
