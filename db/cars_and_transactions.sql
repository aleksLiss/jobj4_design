create table cars(
	id serial primary key, 
	name varchar(50),
	price integer,
	count integer
);

insert into cars(name, price, count) values('bmw e39', 12300, 5);
insert into cars(name, price, count) values('bmw x5', 23500, 4);
insert into cars(name, price, count) values('volvo xc 90', 34200, 1);
insert into cars(name, price, count) values('volvo xc 60', 28300, 2);
insert into cars(name, price, count) values('subaru impreza', 8900, 4);

start transaction isolation level read committed;
savepoint st_p;
insert into cars(name, price, count) values('bmw x1', 14300, 2);
savepoint ins_p;
update cars set count = -1 where name like 'volvo xc 90';
rollback to ins_p;
delete from cars;
rollback to st_p;
commit;

select * from cars;