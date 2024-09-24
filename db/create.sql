create table roles(
	id serial primary key,
	name varchar(50)
);

create table users(
	id serial primary key,
	name varchar(50),
	roles_id int references roles(id)
);

create table rules(
	id serial primary key,
	name varchar(255)
);

create table coments(
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);

create table states(
	id serial primary key,
	name varchar(50)
);

create table categories(
	id serial primary key,
	name varchar(50)
);

create table items(
	id serial primary key,
	name varchar(50),
	user_id int references users(id),
	categories_id int references categories(id),
	state_id int references states(id)
);

create table roles_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);