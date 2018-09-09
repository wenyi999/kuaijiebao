package demo.servlet;

import demo.dao.ChangeDao;
import demo.dao.UserDao;
import demo.domain.ChangeEntity;
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

            System.out.println("userServlet invoke!\n");

            String username =  request.getParameter("username");
            System.out.print(username);
            UserDao dao = new UserDao();
            //CardDao dao2 = new CardDao();
            //List<CardEntity> cardEntityList=dao2.getByUsername(username);
            //Iterator<CardEntity> it = cardEntityList.iterator();
            //ArrayList<JSONObject> infoJson = new ArrayList<JSONObject>();

            UserEntity user =dao.getByUsername(username);
            JSONObject obj = new JSONObject();
            obj.put("username" , user.getUsername());
            obj.put("id" , user.getId());
            obj.put("phone" , user.getPhone());
            obj.put("credit_level" , user.getCredit()+"");
            obj.put("credit_limit" , user.getLine()+"");
            //infoJson.add(obj);
            /*while (it.hasNext()) {
                CardEntity cardEntity = it.next();
                JSONObject obj2 = new JSONObject();
                obj2.put("card" , cardEntity.getCredictnumber());
                obj2.put("id" , "911");
                infoJson.add(obj2);
            }*/
            out.print(obj);
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
            String userStatus = request.getParameter("userStatus");
            if (userStatus.equals("1")){
                String username =  request.getParameter("username");
                String phone = request.getParameter("phone");
                ChangeEntity changeEntity=new ChangeEntity();
                changeEntity.setUsername(username);
                changeEntity.setPhone(phone);
                ChangeDao changeDao=new ChangeDao();
                changeDao.add(changeEntity);
                out.print(1);
                out.flush();
                out.close();
            }
            else if (userStatus.equals("2")){
                String username =  request.getParameter("username");
                System.out.println(username+"0");
                ChangeDao changeDao=new ChangeDao();
                changeDao.delete(username);
                List<ChangeEntity> result=changeDao.getAll();
                Iterator<ChangeEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ChangeEntity goodsEntity = it.next();
                    JSONObject obj = new JSONObject();
                    obj.put("username" , goodsEntity.getUsername());
                    obj.put("phone" , goodsEntity.getPhone());
                    applyJson.add(obj);
                }
                out.print(applyJson);
                out.flush();
                out.close();
            }
            else if(userStatus.equals("3")){
                ChangeDao changeDao=new ChangeDao();
                List<ChangeEntity> result=changeDao.getAll();
                Iterator<ChangeEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ChangeEntity goodsEntity = it.next();
                    JSONObject obj = new JSONObject();
                    obj.put("username" , goodsEntity.getUsername());
                    obj.put("phone" , goodsEntity.getPhone());
                    applyJson.add(obj);
                }
                out.print(applyJson);
                out.flush();
                out.close();
            }
            else {
                String username =  request.getParameter("username");
                ChangeDao changeDao=new ChangeDao();
                UserDao userDao=new UserDao();
                UserEntity userEntity=userDao.getByUsername(username);
                ChangeEntity changeEntity=changeDao.getByUsername(username);
                userEntity.setPhone(changeEntity.getPhone());
                userDao.update(userEntity);
                changeDao.delete(username);
                List<ChangeEntity> result=changeDao.getAll();
                Iterator<ChangeEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ChangeEntity goodsEntity = it.next();
                    JSONObject obj = new JSONObject();
                    obj.put("username" , goodsEntity.getUsername());
                    obj.put("phone" , goodsEntity.getPhone());
                    applyJson.add(obj);
                }
                out.print(applyJson);
                out.flush();
                out.close();
            }
            /*String username =  request.getParameter("username");
            String phone = request.getParameter("phone");
            String ID = request.getParameter("id");
            int credit = Integer.parseInt(request.getParameter("credit_level"));
            double line = Double.parseDouble(request.getParameter("credit_limit"));
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
            out.print("userInfoUpdated");
            out.flush();
            out.close();*/
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
