package demo.servlet;

import demo.dao.AskDao;
import demo.dao.UserDao;
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


public class CreditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreditServlet() {
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
            String creditStatus=request.getParameter("creditStatus");
            if (creditStatus.equals("0")) {
                String username = request.getParameter("username");
                UserDao dao = new UserDao();
                UserEntity user = dao.getByUsername(username);
                JSONObject obj = new JSONObject();
                obj.put("username", user.getUsername());
                obj.put("id", user.getId());
                obj.put("phone", user.getPhone());
                obj.put("credit_level", user.getCredit() + "");
                obj.put("credit_limit", user.getLine() + "");
                out.println(obj);
                out.flush();
                out.close();
            }
            else if (creditStatus.equals("1")){
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            String creditStatus=request.getParameter("creditStatus");
            if(creditStatus.equals("3")){
                String username =  request.getParameter("username");
                String line=request.getParameter("line");
                UserDao dao = new UserDao();
                UserEntity olduser=dao.getByUsername(username);
                olduser.setLine(Integer.parseInt(line));
                dao.update(olduser);
                out.println("creditUpdated");
                out.flush();
                out.close();
            }
            if (creditStatus.equals("4")){
                String username =  request.getParameter("username");
                String edubg=request.getParameter("edubg");
                String job=request.getParameter("job");
                String income=request.getParameter("income");
                String ask=request.getParameter("ask");
                AskDao dao = new AskDao();
                if (dao.getByUsername(username)!=null){
                    out.print("has asked");
                    out.flush();
                    out.close();
                }
                else {
                    AskEntity askEntity = new AskEntity();
                    askEntity.setUsername(username);
                    askEntity.setEdubg(edubg);
                    askEntity.setJob(job);
                    askEntity.setIncome(income);
                    askEntity.setAsk(ask);
                    dao.add(askEntity);
                    out.println("asking");
                    out.flush();
                    out.close();
                }
            }
            else {
                String username =  request.getParameter("username");
                String ask=request.getParameter("ask");
                UserDao dao=new UserDao();
                AskDao dao1=new AskDao();
                UserEntity userEntity=dao.getByUsername(username);
                userEntity.setLine(Integer.parseInt(ask));
                dao.update(userEntity);
                dao1.delete(username);
                out.println("askAgreed");
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
