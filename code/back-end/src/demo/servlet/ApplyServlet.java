package demo.servlet;

import demo.dao.ApplyDao;
import demo.domain.ApplyEntity;
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

import static java.lang.Integer.parseInt;

public class ApplyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyServlet() {
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
            ApplyDao dao = new ApplyDao();
            List<ApplyEntity> result;
            String applyStatus=request.getParameter("applyStatus");
            if (applyStatus.equals("0")){
                result = dao.getAll();
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
            while (it.hasNext()) {
                ApplyEntity apply = (ApplyEntity) it.next();
                ArrayList<String> obj = new ArrayList<String>();
                obj.add("a_id:" + apply.getaId() + "");
                obj.add("username:" + apply.getUsername());
                obj.add("creditor_name:" + apply.getCreditorName());
                obj.add("amount:" + apply.getAmount() + "");
                obj.add("rate:" + apply.getRate() + "");
                obj.add("repaytime:" + apply.getRepaytime() + "");
                obj.add("status:" + apply.getStatus() + "");
                System.out.println(obj.toString());
                applyJson.add(JSONObject.fromString(obj.toString()));
            }
                /*Mongo mongo = new Mongo();
                MongoClient mongoClient = new MongoClient("localhost", 27017);
                DB db = mongoClient.getDB("bookinfo");
                DBCollection coll = db.getCollection("books");
                mongoClient.setWriteConcern(WriteConcern.Journaled());
                BasicDBObject query = new BasicDBObject("id", new BasicDBObject("$gte", 0));
                DBCursor cursor = coll.find(query);
                ArrayList<DBObject> booksDB = new ArrayList<DBObject>();
                while (cursor.hasNext()) {
                    DBObject mongoobj = cursor.next();
                    Object a1 = mongoobj.get("id");
                    String id = a1.toString();
                    String c1 = "1";
                    String c2 = "2";
                    String c3 = "3";
                    System.out.print(id + "\n");
                    if (id.equals(c1)) {
                        GridFS myFS = new GridFS(db);
                        GridFSDBFile imageForOutput = myFS.findOne("flybirds");
                        byte[] imgbase64 = new byte[(int) imageForOutput.getLength()];
                        imageForOutput.getInputStream().read(imgbase64);
                        mongoobj.put("img", imgbase64);
                        System.out.print(imageForOutput.getInputStream());
                    } else if (id.equals(c2)) {
                        GridFS myFS = new GridFS(db);
                        GridFSDBFile imageForOutput = myFS.findOne("Sirius");
                        byte[] imgbase64 = new byte[(int) imageForOutput.getLength()];
                        imageForOutput.getInputStream().read(imgbase64);
                        mongoobj.put("img", imgbase64);
                    } else if (id.equals(c3)) {
                        GridFS myFS = new GridFS(db);
                        GridFSDBFile imageForOutput = myFS.findOne("tangpoem");
                        byte[] imgbase64 = new byte[(int) imageForOutput.getLength()];
                        imageForOutput.getInputStream().read(imgbase64);
                        mongoobj.put("img", imgbase64);
                    } else {
                        mongoobj.put("img", "1");
                    }
                    booksDB.add(mongoobj);

                }
                JSONArray applys = JSONArray.fromArray(booksDB.toArray());*/

                System.out.println(applyJson);
                out.println(applyJson);
                out.flush();
                out.close();
                tx.commit();
            }
            else if (applyStatus.equals("1")){
                String username=request.getParameter("username");
                result = dao.getByUsername(username);
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ApplyEntity apply = (ApplyEntity) it.next();
                    ArrayList<String> obj = new ArrayList<String>();
                    obj.add("a_id:" + apply.getaId() + "");
                    obj.add("username:" + apply.getUsername());
                    obj.add("creditor_name:" + apply.getCreditorName());
                    obj.add("amount:" + apply.getAmount() + "");
                    obj.add("rate:" + apply.getRate() + "");
                    obj.add("repaytime:" + apply.getRepaytime() + "");
                    obj.add("status:" + apply.getStatus() + "");
                    System.out.println(obj.toString());
                    applyJson.add(JSONObject.fromString(obj.toString()));
                }
                System.out.println(applyJson);
                out.println(applyJson);
                out.flush();
                out.close();
                tx.commit();
            }
            else if (applyStatus.equals("2")){
                String creditorname=request.getParameter("creditorname");
                result = dao.getByCreditorName(creditorname);
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ApplyEntity apply = (ApplyEntity) it.next();
                    ArrayList<String> obj = new ArrayList<String>();
                    obj.add("a_id:" + apply.getaId() + "");
                    obj.add("username:" + apply.getUsername());
                    obj.add("creditor_name:" + apply.getCreditorName());
                    obj.add("amount:" + apply.getAmount() + "");
                    obj.add("rate:" + apply.getRate() + "");
                    obj.add("repaytime:" + apply.getRepaytime() + "");
                    obj.add("status:" + apply.getStatus() + "");
                    System.out.println(obj.toString());
                    applyJson.add(JSONObject.fromString(obj.toString()));
                }
                System.out.println(applyJson);
                out.println(applyJson);
                out.flush();
                out.close();
                tx.commit();
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

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");

            System.out.println("PostServlet invoke!");

            String applyStatus = request.getParameter("applyStatus");
            int a_id = parseInt(request.getParameter("a_id"), 11);
            List<ApplyEntity> result;
            ApplyDao dao = new ApplyDao();
            /*Mongo mongo=new Mongo();
            MongoClient mongoClient = new MongoClient("localhost",	27017);
            DB db =	mongoClient.getDB(	"bookinfo"	);
            DBCollection coll =	db.getCollection("books");*/
            if (applyStatus.equals("3")) {
                dao.PayBack(a_id);
                //BasicDBObject query = new BasicDBObject("id", new BasicDBObject("title", title));
                //coll.findAndRemove(new BasicDBObject("id", id));
                String username=request.getParameter("username");
                result = dao.getByUsername(username);
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ApplyEntity apply = (ApplyEntity) it.next();
                    ArrayList<String> obj = new ArrayList<String>();
                    obj.add("a_id:" + apply.getaId() + "");
                    obj.add("username:" + apply.getUsername());
                    obj.add("creditor_name:" + apply.getCreditorName());
                    obj.add("amount:" + apply.getAmount() + "");
                    obj.add("rate:" + apply.getRate() + "");
                    obj.add("repaytime:" + apply.getRepaytime() + "");
                    obj.add("status:" + apply.getStatus() + "");
                    System.out.println(obj.toString());
                    applyJson.add(JSONObject.fromString(obj.toString()));
                }
                System.out.println(applyJson);
                out.println(applyJson);
                out.flush();
                out.close();
                tx.commit();
            } else {
                String username=request.getParameter("username");
                dao.lend(a_id,username);
                String creditorname=request.getParameter("creditorname");
                result = dao.getByCreditorName(creditorname);
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ApplyEntity apply = (ApplyEntity) it.next();
                    ArrayList<String> obj = new ArrayList<String>();
                    obj.add("a_id:" + apply.getaId() + "");
                    obj.add("username:" + apply.getUsername());
                    obj.add("creditor_name:" + apply.getCreditorName());
                    obj.add("amount:" + apply.getAmount() + "");
                    obj.add("rate:" + apply.getRate() + "");
                    obj.add("repaytime:" + apply.getRepaytime() + "");
                    obj.add("status:" + apply.getStatus() + "");
                    System.out.println(obj.toString());
                    applyJson.add(JSONObject.fromString(obj.toString()));
                }
                System.out.println(applyJson);
                out.println(applyJson);
                out.flush();
                out.close();
                tx.commit();
            }
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
