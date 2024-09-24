create table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Lion', 4380, '1758-01-01');
insert into fauna(name, avg_age, discovery_date) values('cat', 5840, '1758-01-01');
insert into fauna(name, avg_age, discovery_date) values('beluga whale', 14600, '1776-01-01');
insert into fauna(name, avg_age, discovery_date) values('catfish', 14600, '1758-01-01');
insert into fauna(name, avg_age, discovery_date) values('bear', 9125, '1758-01-01');
insert into fauna(name, avg_age, discovery_date) values('fox', 1277, '1758-01-01');
insert into fauna(name, avg_age, discovery_date) values('turtle', 18250, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';