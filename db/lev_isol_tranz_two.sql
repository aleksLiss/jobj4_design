start transaction isolation level read uncommitted;
select * from cars;
commit;

start transaction isolation level read committed;
select * from cars;

select * from cars;
commit;

start transaction isolation level repeatable read;
select * from cars;
update cars set count = 200 where name like 'volvo xc 60';
commit;

start transaction isolation level serializable;
select * from cars;
update cars set count = 20 where name like 'toyota land cruzer';
commit;