package testtask.controllers;

import org.hibernate.Session;
import testtask.controllers.entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UtilController extends Controller {
    public UtilController(Session session) {
        super(session);
    }

    /**
     * этот метод вызывается если обратиться к урлу: api/util/addTestData
     */
    public void addTestData() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            ArrayList<User> users = new ArrayList<User>();
            users.add(new User("Шикшин Илья", 25, false, sdf.parse("12.02.2017")));
            users.add(new User("Ким Чонхеп", 38, true, sdf.parse("02.05.2015")));
            users.add(new User("Ли Хек", 44, true, sdf.parse("06.08.2016")));
            users.add(new User("Динерштейн Александр", 24, false, sdf.parse("12.02.2017")));
            users.add(new User("Фионин Григорий", 24, false, sdf.parse("20.02.2017")));
            users.add(new User("Сурин Дмитрий", 24, false, sdf.parse("12.02.2017")));
            users.add(new User("Кульков Андрей", 24, false, sdf.parse("25.12.2016")));
            users.add(new User("Шикшина Светлана", 42, true, sdf.parse("21.08.2016")));
            users.add(new User("Лю Личунь", 24, false, sdf.parse("17.12.2006")));
            users.add(new User("Санкин Тимур", 24, false, sdf.parse("24.02.2017")));
            users.add(new User("Затонских Антон", 27, false, sdf.parse("12.02.2017")));
            users.add(new User("Межов Олег", 40, true, sdf.parse("31.07.2016")));
            users.add(new User("Лазарев Алексей", 26, false, sdf.parse("12.02.2017")));
            users.add(new User("Кашаев Андрей", 24, false, sdf.parse("24.02.2017")));
            users.add(new User("Немлий Игорь", 24, false, sdf.parse("12.02.2017")));
            users.add(new User("Черных Антон", 24, false, sdf.parse("24.02.2017")));
            users.add(new User("Ковалёва Наталья", 24, false, sdf.parse("29.01.2017")));
            users.add(new User("Дмитриев Руслан", 28, false, sdf.parse("25.10.2015")));
            users.add(new User("Дугин Артем", 48, true, sdf.parse("28.12.2014")));
            users.add(new User("Попов Степан", 24, false, sdf.parse("20.02.2017")));
            users.add(new User("Каймин Вячеслав", 24, false, sdf.parse("24.02.2017")));
            users.add(new User("Барыкин Никита", 26, false, sdf.parse("18.09.2016")));
            users.add(new User("Бурнаевский Игорь", 30, false, sdf.parse("11.09.2016")));
            users.add(new User("Вашуров Александр", 32, true, sdf.parse("11.09.2016")));
            users.add(new User("Бурдакова Дина", 32, true, sdf.parse("21.08.2016")));
            users.add(new User("Ежов Дмитрий", 25, false, sdf.parse("25.11.2006")));
            users.add(new User("Сахабутдинов Рустам", 34, true, sdf.parse("17.11.2013")));
            users.add(new User("Хо Ву-Ниль", 60, true, sdf.parse("10.10.2010")));
            users.add(new User("Тычко Игорь", 30, false, sdf.parse("24.02.2017")));
            users.add(new User("Андриенко Василий", 35, true, sdf.parse("12.02.2017")));
            users.add(new User("Чжан Лайонел", 51, true, sdf.parse("21.03.2009")));
            users.add(new User("Чебурахов Андрей", 25, false, sdf.parse("24.02.2017")));
            users.add(new User("Матвеев Евгений", 56, true, sdf.parse("05.08.2016")));
            users.add(new User("Шахов Ким", 28, false, sdf.parse("20.02.2017")));
            users.add(new User("Попов Игорь", 30, false, sdf.parse("12.02.2017")));
            users.add(new User("Трубицин Степан", 26, false, sdf.parse("12.02.2017")));
            users.add(new User("Куликовский Иван", 30, false, sdf.parse("22.09.2013")));
            users.add(new User("Хабазов Никита", 40, true, sdf.parse("19.02.2017")));
            users.add(new User("Дугин Тимур", 25, false, sdf.parse("04.05.2007")));
            users.add(new User("Ким Ю-кьёнг", 63, true, sdf.parse("01.11.2009")));
            users.add(new User("Нилов Георгий", 57, true, sdf.parse("29.03.2015")));
            users.add(new User("Богданов Виктор", 44, true, sdf.parse("06.08.2016")));
            users.add(new User("Киракосян Дмитрий", 44, true, sdf.parse("29.08.2016")));
            users.add(new User("Симонов Алексей", 34, true, sdf.parse("12.02.2017")));
            users.add(new User("Соловьев Юрий", 29, false, sdf.parse("11.12.2016")));
            users.add(new User("Милюткин Дмитрий", 33, true, sdf.parse("08.01.2017")));


            for (User u : users) {
                session.save(u);
            }
            session.getTransaction().commit();
        } catch (ParseException ignore) {
        }
    }
}
