package demo.servlet;

import demo.dao.ApplyDao;
import demo.dao.BuyDao;
import demo.domain.ApplyEntity;
import demo.domain.BuyEntity;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            System.out.println("creditServlet invoke!");
            String countStatus=request.getParameter("countStatus");
            if (countStatus.equals("0")) {//借款统计
                //String username = request.getParameter("username");
                ApplyDao dao = new ApplyDao();
                List<ApplyEntity> applyEntityList = dao.getAll();
                Iterator<ApplyEntity> it=applyEntityList.iterator();

                Map<String,String> debtlist=new HashMap<>();
                while (it.hasNext()) {
                    ApplyEntity applyEntity= it.next();
                    String username=applyEntity.getUsername();
                    if (debtlist.get(username)==null) {
                        debtlist.put(username, applyEntity.getAmount()+"");
                    }
                    else {
                        debtlist.put(username,Integer.parseInt(debtlist.get(username))+applyEntity.getAmount()+"");
                    }
                }
                JSONObject obj = new JSONObject(debtlist);
                out.println(obj);
                out.flush();
                out.close();
            }
            else if (countStatus.equals("1")){//出借统计
                //String username = request.getParameter("username");
                ApplyDao dao = new ApplyDao();
                List<ApplyEntity> applyEntityList = dao.getAll();
                Iterator<ApplyEntity> it=applyEntityList.iterator();

                Map<String,String> debtlist=new HashMap<>();
                while (it.hasNext()) {
                    ApplyEntity applyEntity= it.next();
                    String username=applyEntity.getCreditorname();
                    if (debtlist.get(username)==null) {
                        debtlist.put(username, applyEntity.getAmount()+"");
                    }
                    else {
                        debtlist.put(username,Integer.parseInt(debtlist.get(username))+applyEntity.getAmount()+"");
                    }
                }
                JSONObject obj = new JSONObject(debtlist);
                out.println(obj);
                out.flush();
                out.close();
            }
            else {//理财产品统计
                //String username = request.getParameter("username");
                BuyDao dao = new BuyDao();
                List<BuyEntity> applyEntityList = dao.getAll();
                Iterator<BuyEntity> it=applyEntityList.iterator();

                Map<String,String> debtlist=new HashMap<>();
                while (it.hasNext()) {
                    BuyEntity applyEntity= it.next();
                    String username=applyEntity.getUsername();
                    if (debtlist.get(username)==null) {
                        debtlist.put(username, applyEntity.getAmount()+"");
                    }
                    else {
                        debtlist.put(username,Integer.parseInt(debtlist.get(username))+applyEntity.getAmount()+"");
                    }
                }
                JSONObject obj = new JSONObject(debtlist);
                out.println(obj);
                out.flush();
                out.close();
            }
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
}
