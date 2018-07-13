package demo.servlet;

import demo.dao.ApplyDao;
import demo.domain.ApplyEntity;
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
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            ApplyDao dao = new ApplyDao();
            List<ApplyEntity> result;
            String applyStatus=request.getParameter("applyStatus");
            if (applyStatus.equals("0")){//查看所有借款申请
                result = dao.getAll();
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
            while (it.hasNext()) {
                ApplyEntity apply = (ApplyEntity) it.next();
                JSONObject obj = new JSONObject();
                obj.put("a_id",  apply.getAid() + "");
                obj.put("username" , apply.getUsername());
                obj.put("creditor_name" , apply.getCreditorname());
                obj.put("amount" , apply.getAmount() + "");
                obj.put("rate" , apply.getRate() + "");
                obj.put("repaytime" , apply.getRepaytime() + "");
                int status=apply.getStatus();
                if (status==0)
                {
                    obj.put("status" , "notLend");
                }
                else if (status==1){
                    obj.put("status" , "Lent");
                }
                else if (status==2){
                    obj.put("status" , "payOff");
                }
                System.out.println(obj.toString());
                applyJson.add(obj);
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
                out.print(applyJson);
                out.flush();
                out.close();
            }
            else if (applyStatus.equals("1")){//自己的借款申请
                String username=request.getParameter("username");
                result = dao.getByUsername(username);
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ApplyEntity apply = (ApplyEntity) it.next();
                    JSONObject obj = new JSONObject();
                    obj.put("a_id",  apply.getAid() + "");
                    obj.put("username" , apply.getUsername());
                    obj.put("creditor_name" , apply.getCreditorname());
                    obj.put("amount" , apply.getAmount() + "");
                    obj.put("rate" , apply.getRate() + "");
                    obj.put("repaytime" , apply.getRepaytime() + "");
                    int status=apply.getStatus();
                    if (status==0)
                    {
                        obj.put("status" , "notLend");
                    }
                    else if (status==1){
                        obj.put("status" , "Lent");
                    }
                    else if (status==2){
                        obj.put("status" , "payOff");
                    }
                    System.out.println(obj.toString());
                    applyJson.add(obj);
                }
                System.out.println(applyJson);
                out.print(applyJson);
                out.flush();
                out.close();
            }
            else if (applyStatus.equals("2")){//我的债权
                String creditorname=request.getParameter("creditor_name");

                result = dao.getByCreditorName(creditorname);
                System.out.print(result);
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ApplyEntity apply = (ApplyEntity) it.next();
                    JSONObject obj = new JSONObject();
                    obj.put("a_id",  apply.getAid() + "");
                    obj.put("username" , apply.getUsername());
                    obj.put("creditor_name" , apply.getCreditorname());
                    obj.put("amount" , apply.getAmount() + "");
                    obj.put("rate" , apply.getRate() + "");
                    obj.put("repaytime" , apply.getRepaytime() + "");
                    int status=apply.getStatus();
                    if (status==0)
                    {
                        obj.put("status" , "notLend");
                    }
                    else if (status==1){
                        obj.put("status" , "Lent");
                    }
                    else if (status==2){
                        obj.put("status" , "payOff");
                    }
                    System.out.println(obj.toString());
                    applyJson.add(obj);
                }
                System.out.println(applyJson);
                out.print(applyJson);
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

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");

            System.out.println("PostServlet invoke!");

            String applyStatus = request.getParameter("applyStatus");
            //int a_id = parseInt(request.getParameter("a_id"), 11);
            List<ApplyEntity> result;
            ApplyDao dao = new ApplyDao();
            /*Mongo mongo=new Mongo();
            MongoClient mongoClient = new MongoClient("localhost",	27017);
            DB db =	mongoClient.getDB(	"bookinfo"	);
            DBCollection coll =	db.getCollection("books");*/
            if (applyStatus.equals("3")) {//还款
                int a_id = parseInt(request.getParameter("a_id"), 11);
                dao.PayBack(a_id);

                String username=request.getParameter("username");
                result = dao.getByUsername(username);
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ApplyEntity apply =  it.next();
                    JSONObject obj = new JSONObject();
                    obj.put("a_id",  apply.getAid() + "");
                    obj.put("username" , apply.getUsername());
                    obj.put("creditor_name" , apply.getCreditorname());
                    obj.put("amount" , apply.getAmount() + "");
                    obj.put("rate" , apply.getRate() + "");
                    obj.put("repaytime" , apply.getRepaytime() + "");
                    int status=apply.getStatus();
                    if (status==0)
                    {
                        obj.put("status" , "notLend");
                    }
                    else if (status==1){
                        obj.put("status" , "Lent");
                    }
                    else if (status==2){
                        obj.put("status" , "payOff");
                    }
                    System.out.println(obj.toString());
                    applyJson.add(obj);
                }
                System.out.println(applyJson);
                out.print(applyJson);
                out.flush();
                out.close();
            } else if (applyStatus.equals("4")){//出借
                System.out.print(request.getParameter("a_id"));
                int a_id = parseInt(request.getParameter("a_id"), 11);
                String username=request.getParameter("username");
                dao.lend(a_id,username);
                //String username=request.getParameter("username");
                result = dao.getAll();
                Iterator<ApplyEntity> it = result.iterator();
                ArrayList<JSONObject> applyJson = new ArrayList<JSONObject>();
                while (it.hasNext()) {
                    ApplyEntity apply =  it.next();
                    JSONObject obj = new JSONObject();
                    obj.put("a_id",  apply.getAid() + "");
                    obj.put("username" , apply.getUsername());
                    obj.put("creditor_name" , apply.getCreditorname());
                    obj.put("amount" , apply.getAmount() + "");
                    obj.put("rate" , apply.getRate() + "");
                    obj.put("repaytime" , apply.getRepaytime() + "");
                    int status=apply.getStatus();
                    if (status==0)
                    {
                        obj.put("status" , "notLend");
                    }
                    else if (status==1){
                        obj.put("status" , "Lent");
                    }
                    else if (status==2){
                        obj.put("status" , "payOff");
                    }
                    System.out.println(obj.toString());
                    applyJson.add(obj);
                }
                out.print(applyJson);
                out.flush();
                out.close();
            }else {//新建申请
                //String creditorname=request.getParameter("creditorname");
                String username=request.getParameter("username");
                String creditor=request.getParameter("creditor_name");
                Double amount=Double.parseDouble(request.getParameter("amount"));
                Double rate=Double.parseDouble(request.getParameter("rate"));
                int repaytime=Integer.parseInt(request.getParameter("repaytime"));
                System.out.print(repaytime);
                int status=Integer.parseInt(request.getParameter("status"));
                System.out.print(status);
                ApplyEntity applyEntity = new ApplyEntity();
                applyEntity.setUsername(username);
                applyEntity.setAmount(amount);
                applyEntity.setRate(rate);
                applyEntity.setRepaytime(repaytime);
                applyEntity.setCreditorname(creditor);
                applyEntity.setStatus(status);
                dao.add(applyEntity);
                System.out.print(applyEntity);
                out.print("applyAdded");
            }
        } catch (Exception ex) {
            if (ServletException.class.isInstance(ex)) {
                throw (ServletException) ex;
            } else {
                throw new ServletException(ex);
            }
        }
    }
}
