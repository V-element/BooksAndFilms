package com.gnevanov.services;

import com.gnevanov.dao.BookDAO;
import com.gnevanov.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public void add(Book book) {
        bookDAO.add(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        bookDAO.delete(book);
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    @Transactional
    public List<Book> getBooksByName(String name) {
        return bookDAO.getBooksByName(name);
    }

    @Override
    @Transactional
    public List<Book> getBooksByAuthor(String author) {
        return bookDAO.getBooksByAuthor(author);
    }

    @Override
    @Transactional
    public List<Book> getBooksByNameAndAuthor(String name, String author) {
        return bookDAO.getBooksByNameAndAuthor(name, author);
    }
}
