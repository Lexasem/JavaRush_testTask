package testtask.controllers;

import org.hibernate.Session;

/**
 * класс Controller от которого наследуются все контроллеры
 */
public abstract class Controller {
    Session session;

    public Controller(Session session) {
        this.session = session;
    }
}
