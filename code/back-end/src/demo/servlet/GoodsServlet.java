package demo.servlet;

import demo.dao.GoodsDao;
import demo.domain.GoodsEntity;
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


public class GoodsServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsServlet() {
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
            GoodsDao dao = new GoodsDao();
            List<GoodsEntity> result;
                result = dao.getAll();
                Iterator<GoodsEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    GoodsEntity goodsEntity = it.next();
                    JSONObject obj = new JSONObject();
                    obj.put("item_name" , goodsEntity.getItemname());
                    obj.put("price" , goodsEntity.getPrice()+"");
                    obj.put("item_rate" , goodsEntity.getItemrate()+"");
                    System.out.println(obj.toString());
                    applyJson.add(obj);
                }
                System.out.println(applyJson);
                out.println(applyJson);
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
