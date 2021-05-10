/*Создание таблиц*/
create table type (
id serial primary key,
name varchar(255)
);

create table product(
id serial primary key,
name varchar(255),
type_id int references type(id),
expired_date date,
price float
);

/*Вставка данных*/
insert into type (name) values ('Сыр');
insert into type (name) values ('Молоко');
insert into type (name) values ('Хлебобулочные изделия');
insert into type (name) values ('Напитки');

insert into product (name, type_id, expired_date, price) values ('Российский', 1, '08-06-21', 550);
insert into product (name, type_id, expired_date, price) values ('Эдам', 1, '06-15-21', 760);
insert into product (name, type_id, expired_date, price) values ('Маасдам', 1, '12-08-21', 980);
insert into product (name, type_id, expired_date, price) values ('Эстонский', 1, '07-15-21', 650);
insert into product (name, type_id, expired_date, price) values ('Обезжиренное', 2, '05-06-21', 48);
insert into product (name, type_id, expired_date, price) values ('Отборное 4%', 2, '05-11-21', 85);
insert into product (name, type_id, expired_date, price) values ('Белорусское 3%', 2, '02-06-21', 62);
insert into product (name, type_id, expired_date, price) values ('Молоко 2.5%', 2, '05-24-21', 57);
insert into product (name, type_id, expired_date, price) values ('Бородинский', 3, '05-22-21', 61);
insert into product (name, type_id, expired_date, price) values ('Столичный', 3, '12-05-21', 42);
insert into product (name, type_id, expired_date, price) values ('Отрубной', 3, '05-12-21', 39);
insert into product (name, type_id, expired_date, price) values ('Коломенский', 3, '05-11-21', 35);
insert into product (name, type_id, expired_date, price) values ('Ессентуки', 4, '01-07-21', 30);
insert into product (name, type_id, expired_date, price) values ('Боржоми', 4, '05-08-21', 60);
insert into product (name, type_id, expired_date, price) values ('Ред булл', 4, '10-11-21', 78);
insert into product (name, type_id, expired_date, price) values ('Дюшес', 4, '08-03-22', 45);

/* Запрос на получение всех продуктов с типом "СЫР"*/
select * from product as p
inner join type as t on p.type_id = t.id
where t.name = 'Сыр';

/* Запрос на получения всех продуктов, у кого в имени есть слово "мороженное"*/
select * from product where name like '%мороженное%';

/* Запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце*/
select * from product where expired_date between current_date and
current_date + interval '1 month';

/* Запрос, который выводит самый дорогой продукт.*/
select * from product where price = (select max(price) from product);

/* Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих*/
select t.name, count(p.type_id) from type as t
inner join product as p on p.type_id = t.id
group by t.name;

/* Запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"*/
select * from product as p
inner join type as t on p.type_id = t.id
where t.name = 'Сыр' or t.name = 'Молоко';

/* Запрос, который выводит тип продуктов, которых осталось меньше 10 штук.*/
select t.name, count(p.type_id) from type as t
inner join product as p on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

/* Вывести все продукты и их тип*/
select p.name, p.expired_date, p.price, t.name from product as p
join type as t 
on p.type_id = t.id;