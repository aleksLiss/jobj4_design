CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers(first_name, last_name, age, country) values('vova', 'vovkin', 23, 'Russia');
insert into customers(first_name, last_name, age, country) values('nina', 'pavlova', 18, 'Belarus');
insert into customers(first_name, last_name, age, country) values('petya', 'pupkin', 18, 'USA');
insert into customers(first_name, last_name, age, country) values('grisha', 'grishin', 32, 'Canada');
insert into customers(first_name, last_name, age, country) values('misha', 'mishin', 34, 'Australia');

select *
from customers as c
where c.age = (select min(age)
				from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders(amount, customer_id) values(4, 1);
insert into orders(amount, customer_id) values(3, 2);
insert into orders(amount, customer_id) values(5, 3);
insert into orders(amount, customer_id) values(6, 4);

select * 
from customers as c
where c.id not in (select customer_id
							from orders);
							