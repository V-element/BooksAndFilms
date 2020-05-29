package com.gnevanov.services;

import com.gnevanov.models.Film;

import java.util.List;

public interface FilmService {
    void add(Film film);
    void delete(Film film);
    void update(Film film);
    List<Film> getAllFilms();
    Film getFilmById(int id);
    List<Film> getFilmsByName(String name);
    List<Film> getFilmsByYear(int year);
    List<Film> getFilmsByNameAndYear(String name, int year);
}
