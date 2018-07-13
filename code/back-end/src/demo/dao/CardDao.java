package demo.dao;

import demo.domain.CardEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class CardDao {
    public void add(CardEntity cardEntity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction(); // 开启事务
            session.save(cardEntity);
            System.out.print("save success");
            tx.commit(); // 提交事务
        } catch (RuntimeException e) {
            session.getTransaction().rollback(); // 回滚事务
            throw e;
        }
    }
    public void update(CardEntity cardEntity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {


            session.update(cardEntity);// 操作

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public void delete(String credictnumber) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;tx = session.beginTransaction();
        try {


            CardEntity cardEntity  = (CardEntity) session.createQuery("from CardEntity where credictnumber = ? ")
                    .setParameter(0,credictnumber).uniqueResult(); // 要先获取到这个对象
            session.delete(cardEntity); // 删除的是实体对象

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public List<CardEntity> getByUsername(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null; tx = session.beginTransaction();
        try {


            // 方式一：使用HQL语句
            @SuppressWarnings("unchecked")
            List<CardEntity> list = session.createQuery("FROM CardEntity where username=?").setParameter(0,name).list(); // 使用HQL查询

            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public CardEntity getByCredictNumber(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;tx = session.beginTransaction();
        try {


            // 方式一：使用HQL语句
            @SuppressWarnings("unchecked")
            CardEntity cardEntity = (CardEntity) session.createQuery("FROM CardEntity where credictnumber=?").setParameter(0,name).uniqueResult(); // 使用HQL查询

            tx.commit();
            return cardEntity;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
