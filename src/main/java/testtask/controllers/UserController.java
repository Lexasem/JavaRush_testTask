package testtask.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import testtask.Page;
import testtask.controllers.entities.Entity;
import testtask.controllers.entities.User;

import java.io.IOException;

/**
 * UserController класс который выполняет действия с обхектами User (получает из базы, сохраняет в базу и т.д.)
 */
public class UserController extends Controller {
    public UserController(Session session) {
        super(session);
    }

    /**
     *
     * @param json принимает объект User в json формате
     * @return true если объект успешно создан
     */
    public boolean create(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User u = mapper.readValue(json, User.class);
            return this.create(u);
        } catch (IOException ignore) {
        }
        return false;
    }

    /**
     *
     * @param pageNum номер страницы, которую нужно получить
     * @return возвращает объект Page
     */
    public Page getPage(int pageNum) {
        Page res = new Page();
        res.curentPage = pageNum;
        long entitiesCount;
        Query q = session.createQuery("select count(user.id) from User as user");
        entitiesCount = ((Long) q.getSingleResult()).longValue();

        q = session.createQuery("from User as user");
        q.setFirstResult((pageNum - 1) * Page.itemsPerPage);
        q.setMaxResults(Page.itemsPerPage);

        res.entities = q.list();

        res.countPages(entitiesCount);

        return res;
    }

    /**
     *
     * @param pageNum номер страницы
     * @param search поисковая строка
     * @return возвращает объект Page
     */
    public Page getPage(int pageNum, String search) {
        Page res = new Page();
        res.curentPage = pageNum;
        long entitiesCount;
        Query q = session.createQuery("select count(user.id) from User as user where user.name like :name");
        q.setParameter("name", "%" + search + "%");
        entitiesCount = ((Long) q.getSingleResult()).longValue();

        q = session.createQuery("from User as user where user.name like :name");
        q.setParameter("name", "%" + search + "%");
        q.setFirstResult((pageNum - 1) * Page.itemsPerPage);
        q.setMaxResults(Page.itemsPerPage);

        res.entities = q.list();
        res.countPages(entitiesCount);

        return res;
    }

    /**
     * удаялет пользователя из БД по id
     * @param id id пользователя, которого необходимо удалить
     * @return true если пользователь удален успешно
     */

    public boolean delete(int id) {
        User u = getUser(id);

        if (u == null) {
            return false; //нечего удалять
        } else {
            session.remove(u);
            session.getTransaction().commit();
            return true; //пользователь удален
        }
    }

    /**
     * обновляет пользователя в БД
     *
     * @param json принимает объект User в json формате (отредактирванную копию)
     * @return true если отредактирован успешно
     */
    public boolean update(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User u = mapper.readValue(json, User.class);
            User userToUpdate = this.getUser(u.getId());
            if (userToUpdate != null) {
                userToUpdate.setName(u.getName());
                userToUpdate.setAge(u.getAge());
                userToUpdate.setIsAdmin(u.getIsAdmin());

                return this.update(userToUpdate);
            }
        } catch (IOException ignore) {
        }
        return false;
    }

    //***** private funcs ******

    private boolean create(Entity user) {
        return update(user);
    }

    private boolean update(Entity user) {
        try {
            session.save(user);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    private User getUser(int userId) {
        return session.get(User.class, userId);
    }

}