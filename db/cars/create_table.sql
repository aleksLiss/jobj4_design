create table cars(
	id serial primary key,
	name varchar(50),
	speed int,
	price float,
	has_owner boolean,
	country varchar(50)
);