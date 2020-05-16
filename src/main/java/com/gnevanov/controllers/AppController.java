package com.gnevanov.controllers;

import com.gnevanov.dao.BookDAO;
import com.gnevanov.dao.FilmDAO;
import com.gnevanov.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    BookDAO bookDAO;
    @Autowired
    FilmDAO filmDAO;

    @GetMapping("/books")
    public List<Book> allBooks(Model model) {
        //List<Book> bookList = bookDAO.getAllBooks();
        //model.addAttribute("books", bookDAO.getAllBooks());
        //return "books";
        return bookDAO.getAllBooks();
    }

    @GetMapping("/books/add")
    public String addBook(Model model) {
        return "book-add";
    }

    @PostMapping("/books/add")
    public String addBook(@RequestParam int id, @RequestParam String name, @RequestParam String author, @RequestParam String description, Model model) {
        Book book = new Book(id, name, author, description);
        bookDAO.add(book);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable(value = "id") int id, Model model) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    /*@GetMapping("/books/{id}")
    public String*/


}
