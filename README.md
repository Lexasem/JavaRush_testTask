# JavaRush_testTask
данные базы данных:
host: localhost
db: test
login: root
pass: root

при инициализации сервлета если нет таблицы users в базе, то hibernate сам её создаст.

таблица будет такая:

mysql> show full columns from users;
+-------------+--------------+-----------------+------+-----+---------+----------------+---------------------------------+---------+
| Field       | Type         | Collation       | Null | Key | Default | Extra          | Privileges                      | Comment |
+-------------+--------------+-----------------+------+-----+---------+----------------+---------------------------------+---------+
| id          | int(11)      | NULL            | NO   | PRI | NULL    | auto_increment | select,insert,update,references |         |
| name        | varchar(255) | utf8_general_ci | YES  |     | NULL    |                | select,insert,update,references |         |
| age         | int(11)      | NULL            | YES  |     | NULL    |                | select,insert,update,references |         |
| isAdmin     | bit(1)       | NULL            | YES  |     | NULL    |                | select,insert,update,references |         |
| createdDate | datetime     | NULL            | YES  |     | NULL    |                | select,insert,update,references |         |
+-------------+--------------+-----------------+------+-----+---------+----------------+---------------------------------+---------+
5 rows in set (0.04 sec)


в каталог webapp я положил уже скомпилированное приложение angular2.
исходники доступны тут: https://github.com/Lexasem/JavaRush_testTask_angular2.git

тестирвоал в tomcat 9