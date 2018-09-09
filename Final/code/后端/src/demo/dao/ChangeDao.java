package demo.dao;

import demo.domain.ChangeEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Boyi on 2018/9/8.
 */
public class ChangeDao {
    public ChangeDao(){}

    /*
     * 保存
     */
    public void add(ChangeEntity user) {
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
    * 删除
    */
    public void delete(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        try {


            Object user = (ChangeEntity) session.createQuery("from ChangeEntity where username = ? ")
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
    public ChangeEntity getByUsername(String name) {
        System.out.print("in");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //System.out.print("getsesson");
        Transaction tx = session.beginTransaction();
        //System.out.print("begintransaction");
        try {
            //System.out.print("try");
            ChangeEntity user = (ChangeEntity) session.createQuery("from ChangeEntity where username = ? ")
                    .setParameter(0,name).uniqueResult();// 操作
            //System.out.print("user");
            tx.commit();
            //System.out.print("cm");
            return user;
        } catch (RuntimeException e) {
            tx.rollback();
            //System.out.print("exception");
            throw e;
        }
    }

    /*
    * 查询所有
    */
    public List<ChangeEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        try {
            // 方式一：使用HQL语句
            @SuppressWarnings("unchecked")
            List<ChangeEntity> list = session.createQuery("FROM ChangeEntity ").list(); // 使用HQL查询

            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
