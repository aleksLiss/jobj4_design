insert into categories(name) values('electronics');
insert into categories(name) values('food');
insert into categories(name) values('sports');
insert into categories(name) values('clothes');

insert into states(name) values('not processed');
insert into states(name) values('processed');

insert into attachs(name) values('check.png');
insert into attachs(name) values('check.pdf');
insert into attachs(name) values('check.txt');

insert into coments(name) values('Deliever my order by 8 p.m.');
insert into coments(name) values('Phone me, when you will be near to my address');

insert into roles(name) values('programmer');
insert into roles(name) values('manager');
insert into roles(name) values('courier');

insert into rules(name) values('add new item');
insert into rules(name) values('update item');
insert into rules(name) values('delete item');
insert into rules(name) values('viewing item');

insert into users(name, roles_id) values('vova', 1);
insert into users(name, roles_id) values('nina', 2);
insert into users(name, roles_id) values('misha', 3);

insert into roles_rules(roles_id, rules_id) values(1, 1);
insert into roles_rules(roles_id, rules_id) values(1, 2);
insert into roles_rules(roles_id, rules_id) values(1, 3);
insert into roles_rules(roles_id, rules_id) values(1, 4);
insert into roles_rules(roles_id, rules_id) values(2, 2);
insert into roles_rules(roles_id, rules_id) values(2, 4);
insert into roles_rules(roles_id, rules_id) values(3, 4);