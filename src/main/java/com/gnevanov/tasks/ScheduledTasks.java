package com.gnevanov.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gnevanov.dao.BookDAO;
import com.gnevanov.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component("scheduledTasks")
public class ScheduledTasks {

    @Autowired
    private BookDAO bookDAO;

    @Scheduled(fixedRate = 6000, initialDelay = 1000)
    public void exportDataToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> bookList = bookDAO.getAllBooks();
        try {
            String jsonText = objectMapper.writeValueAsString(bookList);
            Files.writeString(Paths.get("C:/temp/books.json"), jsonText);
        } catch (IOException e) {

        }
        /*for (Book book: bookList) {
            try {
                objectMapper.writeValue(file, book);
            } catch (IOException e) {
            }
        }*/
    }

    @Scheduled(fixedRate = 10000, initialDelay = 2000)
    public void exportDataToXML() {
        XmlMapper xmlMapper = new XmlMapper();
        List<Book> bookList = bookDAO.getAllBooks();
        try {
            //String xmlText = xmlMapper.writeValueAsString(bookList);
            FileOutputStream fos = new FileOutputStream("C:/temp/books.xml");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            for (Book book: bookList) {
                oos.writeObject(xmlMapper.writeValueAsString(book));
                //oos.flush();
            }
            //oos.writeObject(ts);
            oos.flush();
            oos.close();
            //Files.writeString(Paths.get("C:/temp/books.xml"), xmlText);
        } catch (IOException e) {

        }
        /*for (Book book: bookList) {
            try {
                xmlMapper.writeValue(file, book);
            } catch (IOException e) {
            }
        }*/

    }
}
