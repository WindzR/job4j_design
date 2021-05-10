/* Создание таблиц departments и emploees */
CREATE TABLE departments(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE emploees(
	id serial primary key,
	name varchar(255),
	department_id int REFERENCES departments(id)
);

/* Заполнение таблиц departments и emploees */
INSERT INTO departments(name) VALUES ('Особый');
INSERT INTO departments(name) VALUES ('Технический контроль');
INSERT INTO departments(name) VALUES ('По работе с персоналом');
INSERT INTO departments(name) VALUES ('Разработки схем');
INSERT INTO departments(name) VALUES ('Изготовления продукции');

INSERT INTO emploees(name, department_id) VALUES ('Иван Иванов', 2);
INSERT INTO emploees(name, department_id) VALUES ('Петр Петров', 2);
INSERT INTO emploees(name) VALUES ('Гараж Поджигалов');
INSERT INTO emploees(name, department_id) VALUES ('Баклан Самолетов', 3);
INSERT INTO emploees(name, department_id) VALUES ('Эдуард Суровый', 3);
INSERT INTO emploees(name) VALUES ('Александр Палкин');
INSERT INTO emploees(name, department_id) VALUES ('Гадя Хренова', 4);
INSERT INTO emploees(name, department_id) VALUES ('Владимир Непиздит', 4);
INSERT INTO emploees(name, department_id) VALUES ('Угнал Самокатов', 4);
INSERT INTO emploees(name, department_id) VALUES ('Валентина Стакан', 5);
INSERT INTO emploees(name, department_id) VALUES ('Саша ДваПроцента', 5);

/* Выполнение запросов с left, rigth, full, cross соединениями */
SELECT * FROM departments d
LEFT JOIN emploees e
ON e.department_id = d.id;

SELECT * FROM departments d
RIGHT JOIN emploees e
ON e.department_id = d.id;

SELECT * FROM departments d
FULL JOIN emploees e
ON e.department_id = d.id;

SELECT * FROM departments d
CROSS JOIN emploees e;

/* Используя left join найти департаменты, у которых нет работников */
SELECT * FROM departments d
LEFT JOIN emploees e
ON e.department_id = d.id
WHERE e.department_id IS NULL;

/* Используя left и right join написать запросы, которые давали бы одинаковый результат. */
--Вариант 1
SELECT * FROM departments d
LEFT JOIN emploees e
ON e.department_id = d.id;

SELECT * FROM emploees e
RIGHT JOIN departments d
ON e.department_id = d.id;

--Вариант 2
SELECT * FROM emploees e
LEFT JOIN departments d
ON d.id = e.department_id;

SELECT * FROM departments d
RIGHT JOIN emploees e
ON d.id = e.department_id;

/*  Создание таблицы teens с атрибутами name, gender и заполнение ее */
CREATE TABLE teens (
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

INSERT INTO teens (name, gender) VALUES ('Ольга', 'жен.');
INSERT INTO teens (name, gender) VALUES ('Борис', 'муж.');
INSERT INTO teens (name, gender) VALUES ('Елена', 'жен.');
INSERT INTO teens (name, gender) VALUES ('Олег', 'муж.');
INSERT INTO teens (name, gender) VALUES ('Валерий', 'муж.');
INSERT INTO teens (name, gender) VALUES ('Валентина', 'жен.');
INSERT INTO teens (name, gender) VALUES ('Евгения', 'жен.');
INSERT INTO teens (name, gender) VALUES ('Дмитрий', 'муж.');
INSERT INTO teens (name, gender) VALUES ('Александра', 'жен.');
INSERT INTO teens (name, gender) VALUES ('Анна', 'жен.');

/*  Используя cross join составить все возможные разнополые пары */
SELECT m.name AS man,
f.name AS woman
FROM teens m CROSS JOIN teens f
WHERE m.gender != f.gender AND m.gender LIKE 'муж.';
