package demo.dao;

import demo.domain.UserEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;



public class UserDao {
    public UserDao(){}

    /*
     * 保存
     */
    public void add(UserEntity user) {
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

    /*
     * 更新
     */
    public void update(UserEntity user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;tx = session.beginTransaction();
        try {


            session.update(user);// 操作

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    /*
     * 删除
     */
    public void delete(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        try {


            Object user = (UserEntity) session.createQuery("from UserEntity where username = ? ")
                    .setParameter(0,name).uniqueResult(); // 要先获取到这个对象
            session.delete(user); // 删除的是实体对象

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }


    /*
     * 根据userName查询一个User数据
     */
    public UserEntity getByUsername(String name) {
        System.out.print("in");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.print("getsesson");
        Transaction tx = session.beginTransaction();
        System.out.print("begintransaction");
        try {
            System.out.print("try");
            UserEntity user = (UserEntity) session.createQuery("from UserEntity where username = ? ")
                    .setParameter(0,name).uniqueResult();// 操作
            System.out.print("user");
            tx.commit();
            System.out.print("cm");
            return user;
        } catch (RuntimeException e) {
            tx.rollback();
            System.out.print("exception");
            throw e;
        }
    }

    /*
     * 查询所有
     */
    public List<UserEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        try {
            // 方式一：使用HQL语句
            @SuppressWarnings("unchecked")
            List<UserEntity> list = session.createQuery("FROM UserEntity ").list(); // 使用HQL查询

            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }


}
