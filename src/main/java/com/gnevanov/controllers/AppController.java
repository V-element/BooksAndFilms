package com.gnevanov.controllers;

import com.gnevanov.models.Book;
import com.gnevanov.models.Film;
import com.gnevanov.services.BookService;
import com.gnevanov.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    private BookService bookService;
    private FilmService filmService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/books")
    public String allBooks(Model model) {
        List<Book> bookList = bookService.getAllBooks();
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
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping(value = "/books/edit")
    public ModelAndView editBook(@RequestParam("id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("book-add-edit");
        modelAndView.addObject(bookService.getBookById(id));
        return modelAndView;
    }

    @PostMapping(value = "/books/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam(value = "id") int id, Model model) {
        Book book = new Book();
        book.setId(id);
        bookService.delete(book);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchBooksByParams(@RequestParam(value = "name") String name, @RequestParam(value = "author") String author, Model model) {
        List<Book> bookList;
        if (name.isEmpty() & author.isEmpty()) {
            bookList = bookService.getAllBooks();
        } else if (!name.isEmpty() & author.isEmpty()) {
            bookList = bookService.getBooksByName("%" + name + "%");
        } else if (name.isEmpty()) {
            bookList = bookService.getBooksByAuthor("%" + author + "%");
        } else {
            bookList = bookService.getBooksByNameAndAuthor("%" + name + "%", "%" + author + "%");
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
        List<Film> filmList = filmService.getAllFilms();
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
        filmService.add(film);
        return "redirect:/films";
    }

    @GetMapping(value = "/films/edit")
    public ModelAndView editFilm(@RequestParam("id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("film-add-edit");
        modelAndView.addObject(filmService.getFilmById(id));
        return modelAndView;
    }

    @PostMapping(value = "/films/edit")
    public String editFilm(@ModelAttribute("film") Film film) {
        filmService.update(film);
        return "redirect:/films";
    }

    @GetMapping("/films/delete")
    public String deleteFilm(@RequestParam(value = "id") int id, Model model) {
        Film film = new Film();
        film.setId(id);
        filmService.delete(film);
        return "redirect:/films";
    }

    @GetMapping("/films/search")
    public String searchFilmsByParams(@RequestParam(value = "name") String name, @RequestParam(value = "year") String yearString, Model model) {
        List<Film> filmList;
        int year = yearString.equals("") ? 0 : Integer.parseInt(yearString);
        if (name.isEmpty() & (year == 0)) {
            filmList = filmService.getAllFilms();
        } else if (!name.isEmpty() & year == 0) {
            filmList = filmService.getFilmsByName("%" + name + "%");
        } else if (name.isEmpty()) {
            filmList = filmService.getFilmsByYear(year);
        }   else {
            filmList = filmService.getFilmsByNameAndYear("%" + name + "%", year);
        }
        model.addAttribute("title", "Films");
        model.addAttribute("name", name);
        model.addAttribute("year", yearString);
        model.addAttribute("films", filmList);
        model.addAttribute("filmsCount", filmList.size());
        return "films";
    }
}
