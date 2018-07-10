package demo.dao;

import demo.domain.ApplyEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class ApplyDao {
    public ApplyDao(){}

    public void add(ApplyEntity applyEntity){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction(); // 开启事务
            session.save(applyEntity);
            System.out.print("save success");
            tx.commit(); // 提交事务
        } catch (RuntimeException e) {
            session.getTransaction().rollback(); // 回滚事务
            throw e;
        } finally {
            session.close(); // 关闭session
        }
    }

    public List<ApplyEntity> getAll(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // 方式一：使用HQL语句
            @SuppressWarnings("unchecked")
            List<ApplyEntity> list = session.createQuery("FROM ApplyEntity ").list(); // 使用HQL查询

            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<ApplyEntity> getByUsername(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<ApplyEntity> applyEntityList = (List<ApplyEntity>) session.createQuery("from ApplyEntity where username = ?").setParameter(0,name).list();// 操作
            tx.commit();
            return applyEntityList;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<ApplyEntity> getByCreditorName(String name){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<ApplyEntity> applyEntityList = (List<ApplyEntity>) session.createQuery("from ApplyEntity where creditorName = ?").setParameter(0,name).list();// 操作
            tx.commit();
            return applyEntityList;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void PayBack(int a_id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ApplyEntity applyEntity = (ApplyEntity) session.createQuery("from ApplyEntity where aId = ?").setParameter(0,a_id);// 操作
            applyEntity.setStatus(2);
            tx.commit();
            return;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void lend(int a_id,String username){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ApplyEntity applyEntity = (ApplyEntity) session.createQuery("from ApplyEntity where aId = ?").setParameter(0,a_id);// 操作
            applyEntity.setStatus(1);
            applyEntity.setCreditorName(username);
            tx.commit();
            return;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
