create table departments(
	id serial primary key,
	name varchar(30)
);

create table employees(
	id serial primary key,
	name varchar(20),
	department_id int references departments(id)
)

insert into departments(name) values('Energy');
insert into departments(name) values('Defence');
insert into departments(name) values('Transportation');
insert into departments(name) values('Education');

insert into employees(name, department_id) values('John', 1);
insert into employees(name, department_id) values('Alex', 1);

insert into employees(name, department_id) values('Jack', 2);
insert into employees(name, department_id) values('Oliver', 2);

insert into employees(name, department_id) values('Lucas', 3);
insert into employees(name, department_id) values('James', 3);

insert into employees(name, department_id) values('Jimmi', null);
insert into employees(name, department_id) values('Henry', null);

select dp.name, es.name
from departments as dp
join employees as es 
on dp.id = es.department_id

select es.name, dp.name
from employees as es
join departments as dp 
on dp.id = es.department_id

select dp.name, es.name
from departments as dp
full join employees as es
on dp.id = es.department_id

select dp.name, es.name
from departments as dp
cross join employees as es

select dp.name
from departments as dp
join employees as es
on dp.id not in (
select e.department_id
from employees as e
where e.department_id is not null
group by e.department_id)
group by dp.name

select d.name, e.name
from departments as d
join employees as e
on d.id = e.department_id

select d.name, e.name
from employees as e
join departments as d
on d.id = e.department_id

create table teens(
	id serial primary key,
	name varchar(10),
	gender varchar(10)
)

insert into teens(name, gender) values('Vova', 'male');
insert into teens(name, gender) values('Misha', 'male');

insert into teens(name, gender) values('Nina', 'female');
insert into teens(name, gender) values('Masha', 'female');

select t1.name, t2.name
from teens as t1
cross join teens as t2
where t1.name < t2.name