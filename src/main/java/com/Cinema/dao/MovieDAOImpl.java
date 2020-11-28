package com.Cinema.dao;

import com.Cinema.models.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.Cinema.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    @Override
    public Movie findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Movie.class, id);
    }

    @Override
    public void save(Movie movie) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(movie);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Movie movie) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(movie);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Movie movie) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(movie);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = (List<Movie>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Movie").list();
        return movies;
    }
}
