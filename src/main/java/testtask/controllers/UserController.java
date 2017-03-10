package testtask.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import testtask.Page;
import testtask.controllers.entities.Entity;
import testtask.controllers.entities.User;

import java.io.IOException;

public class UserController extends Controller {
    public UserController(Session session) {
        super(session);
    }

    public boolean create(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User u = mapper.readValue(json, User.class);
            return this.create(u);
        } catch (IOException ignore) {
        }
        return false;
    }

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

    private boolean create(Entity user) {
        return update(user);
    }

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