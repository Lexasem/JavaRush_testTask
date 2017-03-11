package testtask;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** главный класс сервлета
 *  при деплое инициализирует класс SessionFactory для обеспечения связи с БД
 *  принимает ХТТП запросы от фронтэнда и создаёт объект Router
 */
public class Api extends HttpServlet {

    public static SessionFactory sf;

    @Override
    public void init() throws ServletException {

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry).buildMetadata();

        sf = metadata.getSessionFactoryBuilder()
                //  .applyBeanManager( getBeanManager() )
                .build();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Router r = new Router(req, resp, Method.GET);
        r.displayResult();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Router r = new Router(req, resp, Method.POST);
        r.displayResult();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Router r = new Router(req, resp, Method.PUT);
        r.displayResult();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Router r = new Router(req, resp, Method.DELETE);
        r.displayResult();
    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {

        new Router(req, resp, Method.OPTIONS);
        //r.displayResult() ненужно, т.к. в конструкторе инициализируются правильные заголовки


    }

}
