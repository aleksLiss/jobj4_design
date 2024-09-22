create table dealer(
	id serial primary key,
	name varchar(20)
);

create table models(
	id serial primary key,
	name varchar(20),
	dealer_id int references dealer(id)
);

insert into dealer(name) values('Geely-centr');
insert into dealer(name) values('Audi-centr');
insert into dealer(name) values('Honda-centr');

insert into models(name, dealer_id) values('Geely Atlas', 1);
insert into models(name, dealer_id) values('Geely Tugella', 1);
insert into models(name, dealer_id) values('Geely Monjaro', 1);

insert into models(name, dealer_id) values('Audi A80', 2);
insert into models(name, dealer_id) values('Audi A100', 2);
insert into models(name, dealer_id) values('Audi R8', 2);

insert into models(name, dealer_id) values('Honda Civic', 3);
insert into models(name, dealer_id) values('Honda Accord', 3);
insert into models(name, dealer_id) values('Honda Inspire', 3);

