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
        }
    }

    public List<ApplyEntity> getAll(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;tx = session.beginTransaction();
        try {


            // 方式一：使用HQL语句
            @SuppressWarnings("unchecked")
            List<ApplyEntity> list = session.createQuery("FROM ApplyEntity where status=?").setParameter(0,0).list(); // 使用HQL查询

            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public List<ApplyEntity> getPaid(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;tx = session.beginTransaction();
        try {


            // 方式一：使用HQL语句
            @SuppressWarnings("unchecked")
            List<ApplyEntity> list = session.createQuery("FROM ApplyEntity").list(); // 使用HQL查询

            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    public List<ApplyEntity> getByUsername(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {

            List<ApplyEntity> applyEntityList = (List<ApplyEntity>) session.createQuery("from ApplyEntity where username = ?").setParameter(0,name).list();// 操作
            tx.commit();
            return applyEntityList;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public ApplyEntity getByAid(int aid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {

            ApplyEntity applyEntityList = (ApplyEntity) session.createQuery("from ApplyEntity where aid = ?").setParameter(0,aid).uniqueResult();// 操作
            tx.commit();
            return applyEntityList;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    public List<ApplyEntity> getByCreditorName(String name){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx =  session.beginTransaction();
        try {
            System.out.print("getByCreditorName\n");
            List<ApplyEntity> applyEntityList = (List<ApplyEntity>) session.createQuery("from ApplyEntity where creditorname = ?").setParameter(0,name).list();// 操作
            System.out.print("getapplyEntityList");
            tx.commit();
            return applyEntityList;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    public void PayBack(int a_id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {

            ApplyEntity applyEntity = (ApplyEntity) session.createQuery("from ApplyEntity where aid = ?").setParameter(0,a_id).uniqueResult();// 操作
            applyEntity.setStatus(2);
            session.update(applyEntity);
            tx.commit();
            //return;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    public void lend(int a_id,String username){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {

            ApplyEntity applyEntity = (ApplyEntity) session.createQuery("from ApplyEntity where aid = ?").setParameter(0,a_id).uniqueResult();// 操作
            applyEntity.setStatus(1);
            applyEntity.setCreditorname(username);
            System.out.print(applyEntity.getUsername()+"\n");
            session.update(applyEntity);
            tx.commit();
            //return;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
