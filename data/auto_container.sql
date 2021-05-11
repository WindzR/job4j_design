/* Создание таблиц body engine, gearbox, auto */
CREATE TABLE body (
	id serial primary key,
	model varchar(255),
	manufacturer varchar(255)
);

CREATE TABLE engine (
	id serial primary key,
	model varchar(255),
	manufacturer varchar(255)
);

CREATE TABLE gearbox (
	id serial primary key,
	model varchar(255),
	manufacturer varchar(255)
);

CREATE TABLE auto (
	id serial primary key,
	model varchar(255),
	manufacturer varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	gearbox_id int references gearbox(id)
);

/* Заполнение таблиц body engine, gearbox, auto */
INSERT INTO body (model, manufacturer) VALUES ('model_1', 'Nissan');
INSERT INTO body (model, manufacturer) VALUES ('model_2', 'Volksvagen');
INSERT INTO body (model, manufacturer) VALUES ('model_3', 'Toyota');
INSERT INTO body (model, manufacturer) VALUES ('model_4', 'Lada');
INSERT INTO body (model, manufacturer) VALUES ('model_5', 'BMW');

INSERT INTO engine (model, manufacturer) VALUES ('engine_1', 'Nissan');
INSERT INTO engine (model, manufacturer) VALUES ('engine_2', 'Volksvagen');
INSERT INTO engine (model, manufacturer) VALUES ('engine_3', 'Toyota');
INSERT INTO engine (model, manufacturer) VALUES ('engine_4', 'Lada');
INSERT INTO engine (model, manufacturer) VALUES ('engine_5', 'BMW');

INSERT INTO gearbox (model, manufacturer) VALUES ('gearbox_1', 'Nissan');
INSERT INTO gearbox (model, manufacturer) VALUES ('gearbox_2', 'Volksvagen');
INSERT INTO gearbox (model, manufacturer) VALUES ('gearbox_3', 'Toyota');
INSERT INTO gearbox (model, manufacturer) VALUES ('gearbox_4', 'Lada');
INSERT INTO gearbox (model, manufacturer) VALUES ('gearbox_5', 'BMW');

INSERT INTO auto (model, manufacturer, body_id, engine_id, gearbox_id) VALUES ('Qashkay', 'Nissan', 1 , 1, 1);
INSERT INTO auto (model, manufacturer, body_id, engine_id, gearbox_id) VALUES ('Polo', 'Volksvagen', 2 , 2, 2);
INSERT INTO auto (model, manufacturer, body_id, engine_id, gearbox_id) VALUES ('Camry', 'Toyota', 3 , 3, 3);
INSERT INTO auto (model, manufacturer, body_id, engine_id, gearbox_id) VALUES ('X5', 'BMW', 5 , 5, 5);
INSERT INTO auto (model, manufacturer, body_id, engine_id, gearbox_id) VALUES ('Granta', 'Lada', 1 , 1, 1);
INSERT INTO auto (model, manufacturer, body_id, engine_id, gearbox_id) VALUES ('Vesta Sport', 'Lada', 2 , 2, 2);

/* Вывод всех машин и все привязанные к ним детали */
SELECT a.model, a.manufacturer, b.model AS body, b.manufacturer, e.model AS engine, e.manufacturer, g.model AS gearbox, g.manufacturer
FROM auto a
	LEFT JOIN body b ON a.body_id = b.id
	LEFT JOIN engine e ON a.engine_id = e.id
	LEFT JOIN gearbox g ON a.gearbox_id = g.id;

/* Вывод отдельно кузова, которые не используются в машине */
SELECT b.model AS body, b.manufacturer, a.model AS car FROM body b
	FULL JOIN auto a ON a.body_id = b.id
	WHERE a.body_id IS NULL;

/* Вывод отдельно двигателей, которые не используются в машине */
SELECT e.model AS engine, e.manufacturer, a.model AS car FROM engine e
	FULL JOIN auto a ON a.engine_id = e.id
	WHERE a.engine_id IS NULL;

/* Вывод отдельно коробок передач, которые не используются в машине */
SELECT g.model AS gearbox, g.manufacturer, a.model AS car FROM gearbox g
	FULL JOIN auto a ON a.gearbox_id = g.id
	WHERE a.gearbox_id IS NULL;
