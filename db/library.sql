create table authors(
	id serial primary key,
	name varchar(50)
);

create table books(
	id serial primary key,
	name varchar(50),
	price int,
	author_id int references authors(id)
);

insert into authors(name) values('Сергей Лукьяненко');
insert into authors(name) values('Джоан Роулинг');
insert into authors(name) values('Лев Толстой');
insert into authors(name) values('Джордж Оруэлл');

insert into books(name, price, author_id) values('Гарри Поттер и Тайная комната', 220, 2);
insert into books(name, price, author_id) values('Война и мир', 80, 3);
insert into books(name, price, author_id) values('1984', 90, 4);
insert into books(name, price, author_id) values('Сумеречный дозор', 125, 1);

select * from authors as ars
join books as b
on ars.id = b.id;

select * from books as b
join authors as ar
on ar.id = b.id;

select * from books as b
join authors as ar
on ar.id = b.id
where b.price < 200;