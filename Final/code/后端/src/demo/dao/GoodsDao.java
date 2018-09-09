package demo.dao;

import demo.domain.BuyEntity;
import demo.domain.GoodsEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoodsDao {
    public List<GoodsEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {


            // 方式一：使用HQL语句
            @SuppressWarnings("unchecked")
            List<GoodsEntity> list = session.createQuery("FROM GoodsEntity ").list(); // 使用HQL查询

            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    public List<GoodsEntity> getByUsername(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {


            //System.out.print(session.createQuery("from BuyEntity where username = ?").setParameter(0,name).list());
            List<BuyEntity> BuyEntityList = (List<BuyEntity>) session.createQuery("from BuyEntity where username = ?").setParameter(0, name).list();// 操作
            Iterator iterator = BuyEntityList.iterator();
            ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
            while (iterator.hasNext()) {
                BuyEntity buyEntity = (BuyEntity) iterator.next();
                String itemname = buyEntity.getItemname();
                GoodsEntity goodsEntity = (GoodsEntity) session.createQuery("from GoodsEntity where itemname=?").setParameter(0, itemname).uniqueResult();
                goodsEntityList.add(goodsEntity);
            }
            tx.commit();
            return goodsEntityList;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}

