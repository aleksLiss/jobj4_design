create table type(
	id serial primary key,
	name varchar(50)
);

create table product(
	id serial primary key,
	name varchar(50),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values('сыр');
insert into type(name) values('молоко');
insert into type(name) values('мясо');
insert into type(name) values('овощи');
insert into type(name) values('мороженое');

insert into product(name, type_id, expired_date, price) values('сыр плавленный', 1, '2024-10-15', 30.3);
insert into product(name, type_id, expired_date, price) values('сыр пармезан', 1, '2024-11-20', 28.1);
insert into product(name, type_id, expired_date, price) values('сыр моцарелла', 1, '2024-09-20', 50.7);

insert into product(name, type_id, expired_date, price) values('молоко сухое', 2, '2025-01-01', 25.3);
insert into product(name, type_id, expired_date, price) values('молоко сгущеное', 2, '2025-10-10', 29.3);
insert into product(name, type_id, expired_date, price) values('молоко цельное', 2, '2025-10-10', 12.3);

insert into product(name, type_id, expired_date, price) values('курица', 3, '2024-10-01', 32.3);
insert into product(name, type_id, expired_date, price) values('свинина', 3, '2024-10-10', 42.3);
insert into product(name, type_id, expired_date, price) values('говядина', 3, '2024-11-01', 50.7);

insert into product(name, type_id, expired_date, price) values('огурец', 4, '2024-09-01', 2.2);
insert into product(name, type_id, expired_date, price) values('помидор', 4, '2024-10-10', 7.3);
insert into product(name, type_id, expired_date, price) values('кабачок', 4, '2024-12-01', 9.4);
insert into product(name, type_id, expired_date, price) values('баклажан', 4, '2024-11-11', 2.0);
insert into product(name, type_id, expired_date, price) values('картофель', 4, '2024-10-11', 3.3);
insert into product(name, type_id, expired_date, price) values('капуста пекинская', 4, '2024-10-10', 1.5);
insert into product(name, type_id, expired_date, price) values('капуста белокочанная', 4, '2024-10-01', 9.4);
insert into product(name, type_id, expired_date, price) values('лук белый', 4, '2024-11-11', 3.3);
insert into product(name, type_id, expired_date, price) values('лук розовый', 4, '2024-11-01', 4.1);
insert into product(name, type_id, expired_date, price) values('чеснок', 4, '2024-12-11', 3.2);


insert into product(name, type_id, expired_date, price) values('мороженое мясо курицы', 5, '2024-10-15', 35.5);
insert into product(name, type_id, expired_date, price) values('мороженое в рожке', 5, '2024-10-15', 6.5);
insert into product(name, type_id, expired_date, price) values('мороженое мясо свинины', 5, '2024-10-15', 45.5);

select pr.name 
from product as pr
join type as tp
on pr.type_id = tp.id
group by pr.name
having pr.name like '%сыр%'

select name 
from product
where name like '%мороженое%'

select name
from product as pr
where pr.expired_date <= current_timestamp

select pr.name, pr.price
from product as pr
where pr.price = (select max(price) from product)

select tp.name, count(pr.name)
from product as pr
join type as tp
on pr.type_id = tp.id
group by tp.name

select pr.name
from product as pr
join type as tp
on pr.type_id = tp.id
where tp.name in ('сыр', 'молоко')

select tp.name, count(pr.name)
from product as pr
join type as tp
on pr.type_id = tp.id
group by tp.name
having count(pr.name) < 10

select pr.name as Наименование_продукта, tp.name as Наименование_типа_товара
from product as pr
join type as tp
on pr.type_id = tp.id