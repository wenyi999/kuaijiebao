package demo.dao;

import demo.domain.BuyEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class BuyDao {
    public BuyDao(){}

    public void add(BuyEntity buyEntity){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction(); // 开启事务
            session.save(buyEntity);
            System.out.print("save success");
            tx.commit(); // 提交事务
        } catch (RuntimeException e) {
            session.getTransaction().rollback(); // 回滚事务
            throw e;
        }
    }

    public List<BuyEntity> getByUsername(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {

            List<BuyEntity> buyEntityList =  (List<BuyEntity>) session.createQuery("from BuyEntity where username = ?").setParameter(0,name).list();// 操作
            tx.commit();
            return  buyEntityList;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public List<BuyEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        try {
            @SuppressWarnings("unchecked")
            List<BuyEntity> list = session.createQuery("FROM BuyEntity ").list();
            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
