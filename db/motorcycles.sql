create table colors (
	id serial primary key,
	name varchar(10)
);

create table motorcycles(
	id serial primary key,
	producer varchar(20),
	name varchar(20),
	has_owner boolean,
	color_id int references colors(id),
	age int
);

insert into colors(name) values('black');
insert into colors(name) values('red');
insert into colors(name) values('blue');
insert into colors(name) values('green');
insert into colors(name) values('white');

insert into motorcycles(producer, name, has_owner, color_id, age)
values('yamaha', 'yamaha r1', true, 1, 2015);
insert into motorcycles(producer, name, has_owner, color_id, age)
values('honda', 'honda CB300F', false, 2, 2010);
insert into motorcycles(producer, name, has_owner, color_id, age)
values('kawasaki', 'kawasaki ninja 400', true, 4, 2001);
insert into motorcycles(producer, name, has_owner, color_id, age)
values('harley davidson', 'hd street 750', true, 1, 1995);

create view show_motorcycles_which_has_owner
as
select m.producer, m.name, m.age
from motorcycles as m
join colors as c
on c.id = m.color_id
where m.has_owner = true
order by age desc

select * from show_motorcycles_which_has_owner;

alter view show_motorcycles_which_has_owner rename to show_motorcycles;

drop view show_motorcycles;
