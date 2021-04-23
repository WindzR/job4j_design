create table towns(
id serial primary key,
name varchar(255),
population int,
country text
);

insert into towns(name, population, country) values('Moscow', 2000000, 'Russia');

update towns set population = 18000000;

select * from towns;

delete from towns;