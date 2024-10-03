create table cars(
	id serial primary key,
	name varchar(50),
	has_owner boolean,
	price integer, 
	count integer
);

insert into cars(name, has_owner, price, count) values('bmw x5', true, 23400, 2);
insert into cars(name, has_owner, price, count) values('volvo xc 90', false, 33200,4);
insert into cars(name, has_owner, price, count) values('volvo xc 60', true, 26500, 3);
insert into cars(name, has_owner, price, count) values('lada niva', false, 9400, 1);
insert into cars(name, has_owner, price, count) values('subaru impreza', true, 18900,2);
insert into cars(name, has_owner, price, count) values('toyota land cruzer', true, 54300, 1);

start transaction isolation level read uncommitted;
insert into cars(name, has_owner, price, count) values('bmw x3', true, 17300, 3);
update cars set count=10 where name like 'bmw x5';
delete from cars where name like 'volvo xc 90';
rollback;

start transaction isolation level read committed;
insert into cars(name, has_owner, price, count) values('bmw e39', false, 9900, 5);
delete from cars where name like 'lada niva';
update cars set count=10 where name like 'bmw x5';
commit;

start transaction isolation level repeatable read;
select * from cars;
insert into cars(name, has_owner, price, count) values('bmw x1', false, 13300, 5);
delete from cars where name like 'subaru impreza';
update cars set count = 100 where name like 'volvo xc 60';
commit;

start transaction isolation level serializable;
select * from cars;
update cars set count where name like 'volvo xc 60';
commit;
