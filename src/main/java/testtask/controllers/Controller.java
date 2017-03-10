package testtask.controllers;

import org.hibernate.Session;

public abstract class Controller {
    Session session;

    public Controller(Session session) {
        this.session = session;
    }
}
