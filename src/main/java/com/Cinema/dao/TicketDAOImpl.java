package com.Cinema.dao;

import com.Cinema.models.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.Cinema.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public Ticket findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Ticket.class, id);
    }

    @Override
    public void save(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(ticket);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(ticket);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(ticket);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = (List<Ticket>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Ticket").list();
        return tickets;
    }
}
