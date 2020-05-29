package com.gnevanov.BooksAndFilmsBoot.dao;

import com.gnevanov.BooksAndFilmsBoot.models.Film;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FilmDAOImpl implements FilmDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Film film) {

    }

    @Override
    public void delete(Film film) {

    }

    @Override
    public void update(Film film) {

    }

    @Override
    public List<Film> getAllFilms() {
        return null;
    }

    @Override
    public Film getFilmById(int id) {
        return null;
    }

    @Override
    public List<Film> getFilmsByName(String name) {
        return null;
    }

    @Override
    public List<Film> getFilmsByYear(int year) {
        return null;
    }

    @Override
    public List<Film> getFilmsByNameAndYear(String name, int year) {
        return null;
    }
}
