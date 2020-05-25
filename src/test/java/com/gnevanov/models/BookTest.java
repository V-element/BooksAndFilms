package com.gnevanov.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class BookTest {

    List<Book> bookList = new ArrayList<Book>();

    @Before
    public void before() {
        bookList.add(new Book("FirstBook", "Gnevanov", "For test"));
        bookList.add(new Book("SecondBook", "Gnevanov E.", "For test"));
        bookList.add(new Book("ThirdBook", "Gnevanov Egor", "For test"));
    }

    @Test
    public void getAllBooks() {
        Assert.assertEquals(bookList.size(),3);
    }

    @Test
    public void getName() {
        Assert.assertEquals(bookList.get(0).getName(),"FirstBook");
    }
}