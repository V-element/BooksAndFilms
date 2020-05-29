package com.gnevanov.services;

import com.gnevanov.models.Book;

import java.util.List;

public interface BookService {
    void add(Book book);
    void update(Book book);
    void delete(Book book);
    Book getBookById(int id);
    List<Book> getAllBooks();
    List<Book> getBooksByName(String name);
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByNameAndAuthor(String name, String author);
}
