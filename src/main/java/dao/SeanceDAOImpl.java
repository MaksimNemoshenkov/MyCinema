package dao;

import models.Seance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SeanceDAOImpl implements SeanceDAO {
    @Override
    public Seance findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Seance.class, id);
    }

    @Override
    public void save(Seance seance) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(seance);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Seance seance) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(seance);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Seance seance) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(seance);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Seance> findAll() {
        List<Seance> seances = (List<Seance>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Seance").list();
        return seances;
    }
}