package demo.servlet;


import demo.dao.UserDao;
import demo.domain.UserEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class LogServlet
 */

public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            
            System.out.println("logServlet invoke!");

            String username =  request.getParameter("username");
            String password =  request.getParameter("password");
            System.out.println("username；"+username+" password: "+password);
            UserDao dao = new UserDao();
            UserEntity user =dao.getByUsername(username);
            System.out.println(user);
            String resp="USER";
            if (user != null){
                if(user.getPassword().equals(password)){
                    System.out.println("correct pwd!");
                    //if(user.getRole()==0){ resp = "USER"; }
                    //else{ resp = "ADMIN"; }
                }
                else{resp = "WRONGPWD";}
            }else{                  /*user不存在*/
                resp = "NULL";
            }

            out.print(resp);

            out.flush();
            out.close();
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            tx.commit();
            session.close();
        }
        catch (Exception ex) {
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
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
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            String username = (String) request.getParameter("username");
            System.out.print(username);
            String password = (String) request.getParameter("password");
            String name = (String) request.getParameter("name");
            int phone = Integer.parseInt(request.getParameter("phone"));
            int ID = Integer.parseInt(request.getParameter("ID"));
            int credit = Integer.parseInt(request.getParameter("credit"));
            int line = Integer.parseInt(request.getParameter("line-of-credit"));
            UserDao dao = new UserDao();
            UserEntity user = dao.getByUsername(username);
            if (user != null){  /*user存在*/
                out.print("USERERROR");
            }else {
                UserEntity newuser = new UserEntity();
                newuser.setUsername(username);
                newuser.setPassword(password);
                newuser.setId(ID);
                newuser.setName(name);
                newuser.setPhone(phone);
                newuser.setCredit(credit);
                newuser.setLineOfCredit(line);
                dao.add(newuser);
                out.print("ADDUSER");
            }
            tx.commit();
            session.close();
            out.flush();
            out.close();
        }
        catch (Exception ex) {
            //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if ( ServletException.class.isInstance( ex ) ) {
                throw (ServletException) ex;
            }
            else {
                throw new ServletException( ex );
            }
        }
    }
}
