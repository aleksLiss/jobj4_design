create table car_bodies(
	id serial primary key,
	name varchar(30)
);

create table car_engines(
	id serial primary key,
	name varchar(30)
);

create table car_transmissions(
	id serial primary key,
	name varchar(30)
);

create table cars(
	id serial primary key,
	name varchar(30),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('седан');
insert into car_bodies(name) values('купе');
insert into car_bodies(name) values('универсал');
insert into car_bodies(name) values('кроссовер');

insert into car_engines(name) values('бензиновый');
insert into car_engines(name) values('дизельный');
insert into car_engines(name) values('электрический');
insert into car_engines(name) values('газовый');

insert into car_transmissions(name) values('механическая');
insert into car_transmissions(name) values('гидравлическая');
insert into car_transmissions(name) values('редуктор');
insert into car_transmissions(name) values('автоматическая');

insert into cars(name, body_id, engine_id, transmission_id) values('bmw X5', 4, 2, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('audi a5', 2, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Volkswagen ID.7 Tourer', 3, 3, 3);

insert into cars(name, body_id, engine_id, transmission_id) values('Volvo XC90', 4, null, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('lada niva', 4, 1, null);
insert into cars(name, body_id, engine_id, transmission_id) values('bmw e39', null, 1, 1);

select c.id, c.name as car_name, b.name as body_name, e.name as engine_name, t.name as transmisson_name
from cars as c
full join car_bodies as b on c.body_id = b.id
full join car_engines as e on c.body_id = e.id
full join car_transmissions as t on c.body_id = t.id
where c.id is not null;

select cb.id, cb.name
from car_bodies as cb
where cb.id not in (
select b.id
from car_bodies as b
join cars as c
on c.body_id = b.id
group by b.id
);

select ce.id, ce.name
from car_engines as ce
where ce.id not in (
select e.id
from car_engines as e
join cars as c
on c.engine_id = e.id
group by e.id
);

select ct.id, ct.name
from car_transmissions as ct
where ct.id not in (
select t.id
from car_transmissions as t
join cars as c
on c.transmission_id = t.id
group by t.id
)
