package demo.servlet;

import demo.dao.BuyDao;
import demo.dao.GoodsDao;
import demo.domain.BuyEntity;
import demo.domain.GoodsEntity;
import demo.util.HibernateUtil;
import net.sf.json.JSONObject;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class BuyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            GoodsDao dao1 = new GoodsDao();
            BuyDao dao2 = new BuyDao();
            List<GoodsEntity> result1;
            List<BuyEntity>result2;


                String username=request.getParameter("username");
                result1 = dao1.getByUsername(username);
                result2=dao2.getByUsername(username);
                Iterator<GoodsEntity> it1 = result1.iterator();
                Iterator<BuyEntity> it2=result2.iterator();
                ArrayList<JSONObject> goodsJson = new ArrayList<JSONObject>();
                while (it2.hasNext()) {
                    GoodsEntity goods = (GoodsEntity) it1.next();
                    BuyEntity buyEntity=(BuyEntity) it2.next();
                    ArrayList<String> obj = new ArrayList<String>();
                    obj.add("item_name:" + goods.getItemName() + "");
                    obj.add("username:" + username);
                    obj.add("price:" + goods.getPrice());
                    obj.add("amount:" + buyEntity.getAmount() + "");
                    obj.add("item_rate:" + goods.getItemRate() + "");
                    System.out.println(obj.toString());
                    goodsJson.add(JSONObject.fromString(obj.toString()));
                }
                System.out.println(goodsJson);
                out.println(goodsJson);
                out.flush();
                out.close();
                tx.commit();

        }
        catch (Exception ex) {
            if ( ServletException.class.isInstance( ex ) ) {
                throw (ServletException) ex;
            }
            else {
                throw new ServletException( ex );
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            BuyDao dao = new BuyDao();
            String item_name=request.getParameter("item_name");
            String amount=request.getParameter("amount");
            String username=request.getParameter("username");
            BuyEntity buyEntity = null;
            int amountInt=Integer.parseInt(amount);
            buyEntity.setAmount(amountInt);
            buyEntity.setItemName(item_name);
            buyEntity.setUsername(username);
            dao.add(buyEntity);
            out.println("购买成功");
            out.flush();
            out.close();
            tx.commit();
        } catch (Exception ex) {
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if (ServletException.class.isInstance(ex)) {
                throw (ServletException) ex;
            } else {
                throw new ServletException(ex);
            }
        }
    }
}
