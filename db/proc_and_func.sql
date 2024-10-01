create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products(name, producer, count, price) values('pr_1','prod_1', 2, 12);
insert into products(name, producer, count, price) values('pr_2','prod_2', 13, 233);
insert into products(name, producer, count, price) values('pr_3','prod_3', 22, 122);
insert into products(name, producer, count, price) values('pr_4','prod_4', 4, 423);
insert into products(name, producer, count, price) values('pr_5','prod_5', -2, 12);
insert into products(name, producer, count, price) values('pr_6','prod_6', 0, 4);

create or replace procedure del_products()
LANGUAGE 'plpgsql'
as $$
	BEGIN
		delete from products as p
		where p.count <= 0;
	END;
$$

call del_products();

select * from products

create or replace function f_del_products(d_price integer) returns void
language 'plpgsql'
as $$
	begin
		delete from products as p
		where p.price < d_price;
	end;
$$

select f_del_products(150);

select * from products