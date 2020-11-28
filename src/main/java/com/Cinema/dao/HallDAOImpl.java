package com.Cinema.dao;

import com.Cinema.models.Hall;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.Cinema.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class HallDAOImpl implements HallDAO {
    @Override
    public Hall findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Hall.class, id);
    }

    @Override
    public void save(Hall hall) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(hall);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Hall hall) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(hall);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Hall hall) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(hall);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Hall> findAll() {
        List<Hall> halls = (List<Hall>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Hall").list();
        return halls;
    }
}
