package com.gnevanov.controllers;

import com.gnevanov.dao.BookDAO;
import com.gnevanov.models.Book;
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
    //@Autowired
    //private FilmDAO filmDAO;

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
    public String addBook(@RequestParam int id, @RequestParam String name, @RequestParam String author, @RequestParam String description, Model model) {
        Book book = new Book(id, name, author, description);
        bookDAO.add(book);
        return "redirect:/books";
    }

    @GetMapping(value = "/books/edit?id={id}")
    public String editPage(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getBookById(id);
        model.addAttribute(book);
        return "book-add-edit";
    }

    @PostMapping(value = "/books/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookDAO.update(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete?={id}")
    public String deleteBook(@PathVariable(value = "id") int id, Model model) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/books/search?name={name}")
    public String searchByName(@PathVariable(value = "name") String name, Model model) {
        List<Book> bookList = bookDAO.getBooksByName(name);
        model.addAttribute("title", "Books");
        model.addAttribute("books", bookList);
        model.addAttribute("booksCount", bookList.size());
        return "books";
    }

    @GetMapping("/books/search?author={author}")
    public String searchByAuthor(@PathVariable(value = "author") String author, Model model) {
        List<Book> bookList = bookDAO.getBooksByAuthor(author);
        model.addAttribute("title", "Books");
        model.addAttribute("books", bookList);
        model.addAttribute("booksCount", bookList.size());
        return "books";
    }

    @GetMapping("/books/search?name={name}&author={author}")
    public String searchByNameAndAuthor(@PathVariable(value = "name") String name, @PathVariable(value = "author") String author, Model model) {
        List<Book> bookList = bookDAO.getBooksByNameAndAuthor(name, author);
        model.addAttribute("title", "Books");
        model.addAttribute("books", bookList);
        model.addAttribute("booksCount", bookList.size());
        return "books";
    }


}
