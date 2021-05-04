create table auto(
id serial primary key,
producer varchar(255),
model varchar(255)
);

create table specification(
id serial primary key,
auto_id int references auto(id),
date date,
engine_power int
);

insert into auto (producer, model) values ('mercedes', 'sl300');
insert into auto (producer, model) values ('bmw', 'x-5');
insert into auto (producer, model) values ('lada', 'granta');
insert into auto (producer, model) values ('mercedes', 'slk');

insert into specification (auto_id, date, engine_power) values (1, '2015-03-01', 215);
insert into specification (auto_id, date, engine_power) values (2, '2014-05-01', 320);
insert into specification (auto_id, date, engine_power) values (3, '2016-11-01', 95);
insert into specification (auto_id, date, engine_power) values (4, '2017-07-01', 240);

select a.producer, a.model, sp.date from auto as a join specification as sp on a.id = sp.auto_id;

select a.model, sp.date, sp.engine_power from auto as a join specification as sp on a.id = sp.auto_id;

select a.producer, a.model, sp.date, sp.engine_power from auto as a join specification as sp on a.id = sp.auto_id;