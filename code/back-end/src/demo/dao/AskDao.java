package demo.dao;

import demo.domain.AskEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class AskDao {
    public AskDao(){}

    public void add(AskEntity user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction(); // 开启事务
            session.save(user);
            System.out.print("save success");
            tx.commit(); // 提交事务
        } catch (RuntimeException e) {
            session.getTransaction().rollback(); // 回滚事务
            throw e;
        }
    }
    public void delete(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        try {


            Object user = (AskEntity) session.createQuery("from AskEntity where username = ? ")
                    .setParameter(0,name).uniqueResult(); // 要先获取到这个对象
            session.delete(user); // 删除的是实体对象

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public AskEntity getByUsername(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            AskEntity user = (AskEntity) session.createQuery("from AskEntity where username = ? ")
                    .setParameter(0,name).uniqueResult();// 操作
            tx.commit();
            return user;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public List<AskEntity> getAll(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            @SuppressWarnings("unchecked")
            List<AskEntity> list = session.createQuery("FROM AskEntity ").list(); // 使用HQL查询
            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
