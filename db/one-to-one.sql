create table phones(
	id serial primary key,
	number int
);

create table persons(
	id serial primary key,
	name varchar(30),
	phones_id int references phones(id) unique
);

insert into phones(number) values(12345);
insert into phones(number) values(54321);
insert into phones(number) values(98767);

insert into persons(name, phones_id) values('Vova', 1);
insert into persons(name, phones_id) values('Nina', 2);
insert into persons(name, phones_id) values('Misha', 3);

