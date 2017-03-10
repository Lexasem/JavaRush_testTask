package testtask;

import org.hibernate.Session;
import org.hibernate.Transaction;
import testtask.controllers.UserController;
import testtask.controllers.UtilController;
import testtask.controllers.entities.Entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

public class Router {
    protected HttpServletRequest req;
    protected HttpServletResponse resp;
    protected Method currentMethod;
    protected PrintWriter out;
    protected BufferedReader in;
    protected Session session;

    protected String[] PramsArr;
    protected String controllerName, methodName;
    protected boolean readyToExec = false;
    protected Object executionResult;

    public Router(HttpServletRequest req, HttpServletResponse resp, Method currentMethod) {
        this.req = req;
        this.resp = resp;
        this.currentMethod = currentMethod;
        initOut();
        initIn();
    }

    protected void execute() throws Exception {
        //util controller
        if ("util".equals(controllerName)) {
            UtilController c = new UtilController(session);
            if ("addtestdata".equals(methodName)) c.addTestData();
        }
        //users controller
        if ("users".equals(controllerName)) {
            UserController c = new UserController(session);
            switch (currentMethod) {
                case GET:
                    if ("page".equals(methodName)) executionResult = c.getPage(Integer.parseInt(PramsArr[2]));
                    if ("pagefind".equals(methodName))
                        executionResult = c.getPage(Integer.parseInt(PramsArr[2]), URLDecoder.decode(PramsArr[3], "UTF-8"));

                    break;
                case DELETE:
                    if ("delete".equals(methodName)) executionResult = c.delete(Integer.parseInt(PramsArr[2]));
                    break;
                case PUT:
                    if ("add".equals(methodName)) executionResult = c.create(readBody());
                    break;
                case POST:
                    if ("update".equals(methodName)) executionResult = c.update(readBody());
                    break;
            }

        }
    }


    protected void initOut() {
        try {
            //заголовки разрешающие обращаться к апи с других доменов
            //(понадобилось т.к. мой ангуляр работает на собственном сервере во время разработки)
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            resp.setHeader("Content-Type", "application/json");
            resp.setCharacterEncoding("UTF-8");

            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void initIn() {
        try {
            in = req.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayResult() {
        try {
            parseRequest();
            if (readyToExec) {
                initSession();
                execute();
                session.close();
            }
            print();

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    protected void print() {
        String res = "{\"data\":";
        if (executionResult != null) {
            if (executionResult instanceof Boolean) {
                res += executionResult;
            } else if (executionResult instanceof List) {
                String arr = "";

                for (Entity e : (List<Entity>) executionResult) {
                    arr += e.toJson() + ",";
                }
                if (res.length() > 0) arr = arr.substring(0, res.length() - 1);

                res += "[" + arr + "]";
            } else if (executionResult instanceof Entity) {
                res += ((Entity) executionResult).toJson();
            } else if (executionResult instanceof Page) {
                res += ((Page) executionResult).toJson();
            }
        } else {
            res += null;
        }
        res += "}";
        out.print(res);
    }

    protected void initSession() {
        session = Api.sf.getCurrentSession();
        Transaction trans = session.getTransaction();
        if (!trans.isActive()) trans.begin();
    }

    protected void parseRequest() {
        int paramsBegining = (req.getContextPath() + req.getServletPath()).length() + 1;
        if (paramsBegining >= req.getRequestURI().length()) return;
        String controllerParams = req.getRequestURI().substring(paramsBegining);
        PramsArr = controllerParams.split("/");

        if (PramsArr == null) return;
        if (PramsArr.length <= 0) return;

        controllerName = PramsArr.length >= 1 ? PramsArr[0].toLowerCase() : "";
        methodName = PramsArr.length >= 2 ? PramsArr[1].toLowerCase() : "";
        readyToExec = true;
    }

    protected String readBody() {
        String res = "";
        try {
            while (in.ready()) {
                res += in.readLine();
            }
        } catch (IOException ignore) {
        }
        return res;
    }
}
