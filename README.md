# JavaRush_testTask
данные базы данных:
host: localhost
db: test
login: root
pass: root

при инициализации сервлета если нет таблицы users в базе,
то hibernate сам её создаст.

таблица будет такая:

id int(11) pri key aut_increment

name varchar(255) utf8_general_ci

age int(11)

isAdmin bit(1)

createdDate (datetime)


в каталог webapp я положил уже скомпилированное приложение angular2.
исходники доступны тут: https://github.com/Lexasem/JavaRush_testTask_angular2.git

тестирвоал в tomcat 9
