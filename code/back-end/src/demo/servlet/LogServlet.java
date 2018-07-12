package demo.servlet;


import demo.dao.UserDao;
import demo.domain.UserEntity;
import demo.util.HibernateUtil;
import org.hibernate.Session;

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
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            String username = (String) request.getParameter("username");
            System.out.print(username+"\n");
            String password = (String) request.getParameter("password");
            String name = (String) request.getParameter("name");
            System.out.print(name+"\n");
            String phone = request.getParameter("phone");
            System.out.print(phone+"\n");
            String ID = request.getParameter("ID");
            System.out.print(ID+"\n");
            int credit = Integer.parseInt(request.getParameter("credit"));
            System.out.print(credit+"\n");
            int line = Integer.parseInt(request.getParameter("line_of_credit"));
            System.out.print(line+"\n");
            UserDao dao = new UserDao();
            System.out.print("daoInited"+"\n");
            UserEntity user = dao.getByUsername(username);
            System.out.print("usergot\n");
            if (user != null){  /*user存在*/
                System.out.print("USERERROR");
                out.print("USERERROR");
            }else {
                UserEntity newuser = new UserEntity();
                System.out.print("newuser\n");
                newuser.setUsername(username);
                newuser.setPassword(password);
                newuser.setId(ID);
                newuser.setName(name);
                newuser.setPhone(phone);
                newuser.setCredit(credit);
                newuser.setLineOfCredit(line);
                System.out.print("usersettled");
                dao.add(newuser);
                System.out.print("ADDUSER");
                out.print("ADDUSER");

            }            out.flush();
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
