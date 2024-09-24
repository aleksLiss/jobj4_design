create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into people(name) values('Misha');
insert into people(name) values('Grisha');
insert into people(name) values('Nina');

insert into devices(name, price) values('phone', 800.50);
insert into devices(name, price) values('phone', 640.30);
insert into devices(name, price) values('phone', 320.20);
insert into devices(name, price) values('pc', 72300.99);
insert into devices(name, price) values('pc', 18200.99);
insert into devices(name, price) values('pc', 4300.99);
insert into devices(name, price) values('laptop', 65800.50);
insert into devices(name, price) values('laptop', 23200.10);
insert into devices(name, price) values('laptop', 6300.00);

insert into devices_people(device_id, people_id) values(1,1);
insert into devices_people(device_id, people_id) values(9,1);

insert into devices_people(device_id, people_id) values(6,2);
insert into devices_people(device_id, people_id) values(8,2);

insert into devices_people(device_id, people_id) values(1,3);
insert into devices_people(device_id, people_id) values(3,3);

select d.name, avg(d.price)
from devices_people as dp
join devices as d
on dp.device_id = d.id
group by d.name;

select pp.name, avg(dv.price)
from (select name, people_id  from devices_people as dp join people on people.id = dp.people_id) as pp
join (select name, price, people_id from devices join devices_people as dp on dp.device_id = devices.id) as dv
on pp.people_id = dv.people_id
group by pp.name;

select pp.name, avg(dv.price)
from (select name, people_id  from devices_people as dp join people on people.id = dp.people_id) as pp
join (select name, price, people_id from devices join devices_people as dp on dp.device_id = devices.id) as dv
on pp.people_id = dv.people_id
group by pp.name
having avg(dv.price) > 5000;