package demo.servlet;

import demo.dao.CardDao;
import demo.domain.CardEntity;
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

public class CardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            CardDao dao = new CardDao();
            List<CardEntity> result;
            String username=request.getParameter("username");
            result = dao.getByUsername(username);
            Iterator<CardEntity> it = result.iterator();
            ArrayList<JSONObject> goodsJson = new ArrayList<JSONObject>();
            while (it.hasNext()) {
                CardEntity cardEntity = it.next();
                ArrayList<String> obj = new ArrayList<String>();
                obj.add("username:" + username);
                obj.add("credict_number:" + cardEntity.getCredictNumber()+"");
                goodsJson.add(JSONObject.fromString(obj.toString()));
            }
            System.out.println(goodsJson);
            out.println(goodsJson);
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
            CardDao dao = new CardDao();
            String CredictNumber=request.getParameter("credict_number");
            String cardStatus=request.getParameter("card_status");
            String username=request.getParameter("username");
            CardEntity cardEntity = null;
            cardEntity.setCredictNumber(CredictNumber);
            cardEntity.setUsername(username);
            if (cardStatus.equals("0")){
                CardEntity cardEntity2=dao.getByCredictNumber(CredictNumber);
                if (cardEntity2==null){
                    dao.add(cardEntity);
                    out.println("添加成功");
                }
                else out.print("卡号重复");

            }
            else if (cardStatus.equals("1")){
                dao.delete(CredictNumber);
                out.print("删除成功");
            }
            else {
                CardEntity cardEntity2=dao.getByCredictNumber(CredictNumber);
                if (cardEntity2==null){
                    dao.update(cardEntity);
                    out.println("修改成功");
                }
                else out.print("卡号重复");
            }
            out.flush();
            out.close();
        }
        catch (Exception ex) {
            if (ServletException.class.isInstance(ex)) {
                throw (ServletException) ex;
            } else {
                throw new ServletException(ex);
            }
        }
    }
}
