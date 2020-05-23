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
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchByParams(@RequestParam(value = "name") String name, @RequestParam(value = "author") String author, Model model) {
        List<Book> bookList;
        if (name.isEmpty() & author.isEmpty()) {
            bookList = bookDAO.getAllBooks();
        } else if (!name.isEmpty() & author.isEmpty()) {
            bookList = bookDAO.getBooksByName("%" + name + "%");
        } else if (name.isEmpty() & !author.isEmpty()) {
            bookList = bookDAO.getBooksByAuthor("%" + author + "%");
        } else {
            bookList = bookDAO.getBooksByNameAndAuthor("%" + name + "%", "%" + author + "%");
        }
        //List<Book> bookList = bookDAO.getBooksByName(name);
        model.addAttribute("title", "Books");
        model.addAttribute("name", name);
        model.addAttribute("author", author);
        model.addAttribute("books", bookList);
        model.addAttribute("booksCount", bookList.size());
        return "books";
    }

   /* @GetMapping("/books/search")
    public String searchByName(@RequestParam(value = "name") String name, Model model) {
        List<Book> bookList = bookDAO.getBooksByName(name);
        model.addAttribute("title", "Books");
        model.addAttribute("books", bookList);
        model.addAttribute("booksCount", bookList.size());
        return "books";
    }*/

    /*@GetMapping("/books/search?author={author}")
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
    }*/


}
