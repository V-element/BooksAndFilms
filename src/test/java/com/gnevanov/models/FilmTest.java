package com.gnevanov.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class FilmTest {

    List<Film> filmList = new ArrayList<Film>();

    @Before
    public void before() {
        filmList.add(new Film("First film","For test", "Gnevanov", (short) 2020));
        filmList.add(new Film("Second film","For test", "Gnevanov", (short) 2019));
        filmList.add(new Film("Third film","For test", "Gnevanov", (short) 2021));
    }

    @Test
    public void getAllFilms() {
        Assert.assertEquals(filmList.size(),3);
    }

    @Test
    public void getName() {
        Assert.assertEquals(filmList.get(1).getName(),"Second film");
    }

}