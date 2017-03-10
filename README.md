# JavaRush_testTask
данные базы данных:
host: localhost
db: test
login: root
pass: root

при инициализации сервлета если нет таблицы users в базе,
то hibernate сам её создаст.

таблица будет такая:

**id** int(11) pri key aut_increment<br>
**name** varchar(255) utf8_general_ci<br>
**age** int(11)<br>
**isAdmin** bit(1)<br>
**createdDate** (datetime)


в каталог webapp я положил уже скомпилированное приложение angular2.
исходники доступны тут: https://github.com/Lexasem/JavaRush_testTask_angular2.git

тестирвоал в tomcat 9
версия java 1.8


процесс установки:
mvn package создает архив testtask-1.0.war, который потом можно загрузить в том кат.
далее сервлет будет доступен по ссылке {server-host}/testtask-1.0/

таблица users будет пустая, чтобы залить в неё тестовые данные нужно нажать на кнопку "добавить тестовые данные"
