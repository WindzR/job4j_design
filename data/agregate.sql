--Вставка данных в таблицы
insert into devices (name, price) values ('iphone 12', 70000);
insert into devices (name, price) values ('samsung galaxy', 39000);
insert into devices (name, price) values ('sony experia', 19900);
insert into devices (name, price) values ('huawei p20', 4900);
insert into devices (name, price) values ('huawei p30', 48000);
insert into devices (name, price) values ('xiaomi redmi 9', 16900);

insert into people (name) values ('Дмитрий');
insert into people (name) values ('Алексей');
insert into people (name) values ('Владимир');
insert into people (name) values ('Олег');
insert into people (name) values ('Яна');
insert into people (name) values ('Елена');

insert into devices_people (device_id, people_id) values (3, 1);
insert into devices_people (device_id, people_id) values (4, 1);
insert into devices_people (device_id, people_id) values (5, 1);
insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (6, 2);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (1, 4);
insert into devices_people (device_id, people_id) values (3, 4);
insert into devices_people (device_id, people_id) values (5, 4);
insert into devices_people (device_id, people_id) values (6, 4);
insert into devices_people (device_id, people_id) values (1, 5);
insert into devices_people (device_id, people_id) values (6, 5);
insert into devices_people (device_id, people_id) values (1, 6);
insert into devices_people (device_id, people_id) values (4, 6);

/*Вывод средней цены устройств*/
select avg(price) from devices;

/*Используя группировку, выводим для каждого человека среднюю цену его устройств*/
select p.name, avg(d.price) from people as p
join devices_people as dp on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name;

/*Выводим для каждого человека среднюю цену его устройств,
 *при условии, что она больше 5000*/
select p.name, avg(d.price) from people as p
join devices_people as dp on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;