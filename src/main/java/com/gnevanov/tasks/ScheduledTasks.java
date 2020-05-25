package com.gnevanov.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gnevanov.dao.BookDAO;
import com.gnevanov.dao.FilmDAO;
import com.gnevanov.models.Book;
import com.gnevanov.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component("scheduledTasks")
public class ScheduledTasks {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private FilmDAO filmDAO;

    @Scheduled(fixedRate = 6000, initialDelay = 1000)
    public void exportDataToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> bookList = bookDAO.getAllBooks();
        List<Film> filmList = filmDAO.getAllFilms();
        try {
            Files.writeString(Paths.get("C:/temp/books.json"), objectMapper.writeValueAsString(bookList));
            Files.writeString(Paths.get("C:/temp/films.json"), objectMapper.writeValueAsString(filmList));
        } catch (IOException e) {

        }
    }

    @Scheduled(fixedRate = 10000, initialDelay = 2000)
    public void exportDataToXML() {
        List<Book> bookList = bookDAO.getAllBooks();
        writeListToXML("books", bookList);
        List<Film> filmList = filmDAO.getAllFilms();
        writeListToXML("films", filmList);

        /*File xmlOutput = new File("C:/temp/books.xml");
        try (FileWriter fileWriter = new FileWriter(xmlOutput)) {
            for (Book book: bookList) {
                fileWriter.write(xmlMapper.writeValueAsString(book));
            }
        } catch (IOException e) {

        }*/

    }

    private void writeListToXML(String fileName, List list) {

        XmlMapper xmlMapper = new XmlMapper();
        File xmlOutput = new File("C:/temp/" + fileName + ".xml");
        try (FileWriter fileWriter = new FileWriter(xmlOutput)) {
            for (Object object: list) {
                if (fileName.equals("books")) {
                    fileWriter.write(xmlMapper.writeValueAsString((Book) object));
                }

            }
        } catch (IOException e) {

        }
    }
}
