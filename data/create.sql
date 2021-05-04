create table users (
id serial primary key,
name varchar(255));
role_id int references roles(id));

create table roles (
id serial primary key,
role varchar(255)
);

create table rules(
id serial primary key,
rule varchar(255));

create table rule_of_roles(
id serial primary key,
role_id int references roles(id),
rule_id int references rules(id));

create table items(
id serial primary key,
item varchar(255),
id_user int references users(id),
time date,	
text text
);

create table comments(
id serial primary key,
time date,
item_id int references items(id),
text text
);

create table attachs(
id serial primary key,
path varchar(255),
item_id int references items(id)
);

create table category(
id serial primary key,
category varchar(255),
description text
);

alter table items add column category_id int references category(id);

create table state(
id serial primary key,
status varchar(255)
);

alter table items add column state_id int references state(id);

