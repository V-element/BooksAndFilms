package com.gnevanov.controllers;

import com.gnevanov.dao.BookDAO;
import com.gnevanov.dao.FilmDAO;
import com.gnevanov.models.Book;
import com.gnevanov.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private FilmDAO filmDAO;

    @GetMapping("/books")
    public String allBooks(Model model) {
        List<Book> bookList = bookDAO.getAllBooks();
        model.addAttribute("title", "Books");
        model.addAttribute("books", bookList);
        model.addAttribute("booksCount", bookList.size());
        return "books";
    }

    @GetMapping("/books/add")
    public String addBook(Model model) {
        model.addAttribute("title", "Adding a new book");
        return "book-add-edit";
    }

    @PostMapping("/books/add")
    public String addBook(@RequestParam String name, @RequestParam String author, @RequestParam String description, Model model) {
        Book book = new Book(name, author, description);
        bookDAO.add(book);
        return "redirect:/books";
    }

    @GetMapping(value = "/books/edit")
    public ModelAndView editBook(@RequestParam("id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("book-add-edit");
        modelAndView.addObject(bookDAO.getBookById(id));
        return modelAndView;
    }

    @PostMapping(value = "/books/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookDAO.update(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam(value = "id") int id, Model model) {
        Book book = new Book();
        book.setId(id);
        bookDAO.delete(book);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchBooksByParams(@RequestParam(value = "name") String name, @RequestParam(value = "author") String author, Model model) {
        List<Book> bookList;
        if (name.isEmpty() & author.isEmpty()) {
            bookList = bookDAO.getAllBooks();
        } else if (!name.isEmpty() & author.isEmpty()) {
            bookList = bookDAO.getBooksByName("%" + name + "%");
        } else if (name.isEmpty()) {
            bookList = bookDAO.getBooksByAuthor("%" + author + "%");
        } else {
            bookList = bookDAO.getBooksByNameAndAuthor("%" + name + "%", "%" + author + "%");
        }
        model.addAttribute("title", "Books");
        model.addAttribute("name", name);
        model.addAttribute("author", author);
        model.addAttribute("books", bookList);
        model.addAttribute("booksCount", bookList.size());
        return "books";
    }

    @GetMapping("/films")
    public String allFilms(Model model) {
        List<Film> filmList = filmDAO.getAllFilms();
        model.addAttribute("title", "Films");
        model.addAttribute("films", filmList);
        model.addAttribute("filmsCount", filmList.size());
        return "films";
    }

    @GetMapping("/films/add")
    public String addFilm(Model model) {
        model.addAttribute("title", "Adding a new film");
        return "film-add-edit";
    }

    @PostMapping("/films/add")
    public String addFilm(@RequestParam String name,
                          @RequestParam String description,
                          @RequestParam String producer,
                          @RequestParam int year,
                          Model model) {
        Film film = new Film(name,description, producer, year);
        filmDAO.add(film);
        return "redirect:/films";
    }

    @GetMapping(value = "/films/edit")
    public ModelAndView editFilm(@RequestParam("id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("film-add-edit");
        modelAndView.addObject(filmDAO.getFilmById(id));
        return modelAndView;
    }

    @PostMapping(value = "/films/edit")
    public String editFilm(@ModelAttribute("film") Film film) {
        filmDAO.update(film);
        return "redirect:/films";
    }

    @GetMapping("/films/delete")
    public String deleteFilm(@RequestParam(value = "id") int id, Model model) {
        Film film = new Film();
        film.setId(id);
        filmDAO.delete(film);
        return "redirect:/films";
    }

    @GetMapping("/films/search")
    public String searchFilmsByParams(@RequestParam(value = "name") String name, @RequestParam(value = "year") String yearString, Model model) {
        List<Film> filmList;
        int year = yearString.equals("") ? 0 : Integer.parseInt(yearString);
        if (name.isEmpty() & (year == 0)) {
            filmList = filmDAO.getAllFilms();
        } else if (!name.isEmpty() & year == 0) {
            filmList = filmDAO.getFilmsByName("%" + name + "%");
        } else if (name.isEmpty()) {
            filmList = filmDAO.getFilmsByYear(year);
        }   else {
            filmList = filmDAO.getFilmsByNameAndYear("%" + name + "%", year);
        }
        model.addAttribute("title", "Films");
        model.addAttribute("name", name);
        model.addAttribute("year", yearString);
        model.addAttribute("films", filmList);
        model.addAttribute("filmsCount", filmList.size());
        return "films";
    }
}
