insert into users (name, role_id) values ('Admin', 1);
insert into users (name, role_id) values ('User1', 2);
insert into users (name, role_id) values ('User2', 2);
insert into roles (role) values ('administrator');
insert into roles (role) values ('user');
insert into roles (role) values ('user');
insert into rules (rule) values ('read');
insert into rules (rule) values ('write');
insert into rule_of_roles (role_id, rule_id) values (1, 2);
insert into rule_of_roles (role_id, rule_id) values (2, 1);
insert into items (item, id_user, time, text) values ('item1', 1, '2021-01-01', 'заявка на тест от ковидлы');
insert into items (item, id_user, time, text) values ('item2', 1, '2021-01-02', 'заявка на проверку на вшивость');
insert into comments (time, item_id, text) values ('2021-01-03', 1, 'проверка комментария');
insert into comments (time, item_id, text) values ('2021-01-03', 2, 'хреновый сервис. Ничего не работает');
insert into attachs (path, item_id) values ('https://job4j.ru/edu/tasks?action=tasks&taskId=0873958f779', 1);
insert into category (category, description) values ('срочная', 'нужно полыть полы в кабинете у шефа');
insert into category (category, description) values ('низкий приоритет', 'дать электричество в больницу');
insert into state (status) values ('в работе');
insert into state (status) values ('принято к рассмотрению');
insert into state (status) values ('завершена');
update items set (category_id, state_id) = (1, 1) where id = 1;
select * from items;
