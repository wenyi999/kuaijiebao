package demo.servlet;

import demo.dao.ApplyDao;
import demo.dao.AskDao;
import demo.dao.UserDao;
import demo.domain.ApplyEntity;
import demo.domain.AskEntity;
import demo.domain.UserEntity;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            if (countStatus.equals("0")) {
                //String username = request.getParameter("username");
                ApplyDao dao = new ApplyDao();
                List<ApplyEntity> applyEntityList = dao.getAll();
                Iterator<ApplyEntity> it=applyEntityList.iterator();
                JSONObject obj = new JSONObject();
                int debtsum=0;
                while (it.hasNext()) {
                    ApplyEntity applyEntity= it.next();
                    obj.put("username", applyEntity.getUsername());
                    obj.put("debtsum", debtsum+=applyEntity.getAmount());
                }
                out.println(obj);
                out.flush();
                out.close();
            }
            else if (countStatus.equals("1")){
                AskDao dao=new AskDao();
                List<AskEntity> askEntityList=dao.getAll();
                Iterator<AskEntity> it=askEntityList.iterator();
                ArrayList<JSONObject> infoJson = new ArrayList<JSONObject>();
                while (it.hasNext()){
                    JSONObject obj = new JSONObject();
                    AskEntity user=it.next();
                    obj.put("username" , user.getUsername());
                    obj.put("edubg" , user.getEdubg());
                    obj.put("job" , user.getJob());
                    obj.put("income" , user.getIncome());
                    obj.put("ask" , user.getAsk());
                    infoJson.add(obj);
                }
                out.println(infoJson);
                out.flush();
                out.close();
            }
            else {
                UserDao dao=new UserDao();
                List<UserEntity> userEntityList=dao.getAll();
                Iterator<UserEntity> it=userEntityList.iterator();
                ArrayList<JSONObject> infoJson = new ArrayList<JSONObject>();
                while (it.hasNext()){
                    JSONObject obj = new JSONObject();
                    UserEntity user=it.next();
                    obj.put("username" , user.getUsername());
                    obj.put("id" , user.getId());
                    obj.put("phone" , user.getPhone());
                    obj.put("credit_level" , user.getCredit()+"");
                    obj.put("credit_limit" , user.getLine()+"");
                    infoJson.add(obj);
                }
                out.println(infoJson);
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
