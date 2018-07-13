package demo.servlet;

import demo.dao.CardDao;
import demo.dao.UserDao;
import demo.domain.CardEntity;
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


public class UserServlet extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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

            System.out.println("userServlet invoke!");

            String username =  request.getParameter("username");
            UserDao dao = new UserDao();
            CardDao dao2 = new CardDao();
            List<CardEntity> cardEntityList=dao2.getByUsername(username);
            Iterator<CardEntity> it = cardEntityList.iterator();
            ArrayList<JSONObject> infoJson = new ArrayList<JSONObject>();

            UserEntity user =dao.getByUsername(username);
            JSONObject obj = new JSONObject();
            obj.put("username" , user.getUsername());
            obj.put("id" , user.getId());
            obj.put("phone" , user.getPhone());
            obj.put("credit_level" , user.getCredit()+"");
            obj.put("credit_limit" , user.getLine()+"");
            infoJson.add(obj);
            while (it.hasNext()) {
                CardEntity cardEntity = it.next();
                JSONObject obj2 = new JSONObject();
                obj2.put("card" , cardEntity.getCredictnumber());
                obj2.put("id" , "911");
                infoJson.add(obj2);
            }
            out.println(infoJson);
            out.flush();
            out.close();
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
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            String username =  request.getParameter("username");
            String phone = request.getParameter("phone");
            String ID = request.getParameter("id");
            int credit = Integer.parseInt(request.getParameter("credit_level"));
            int line = Integer.parseInt(request.getParameter("credit_limit"));
            UserDao dao = new UserDao();
            UserEntity olduser=dao.getByUsername(username);
            UserEntity newuser = new UserEntity();
            System.out.print("newuser\n");
            newuser.setUid(olduser.getUid());
            newuser.setUsername(username);
            newuser.setPassword(olduser.getPassword());
            newuser.setId(ID);
            newuser.setName(olduser.getName());
            newuser.setPhone(phone);
            newuser.setCredit(credit);
            newuser.setLine(line);
            dao.update(newuser);
            out.println("userInfoUpdated");
            out.flush();
            out.close();
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
