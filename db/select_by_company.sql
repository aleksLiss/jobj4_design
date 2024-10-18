CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values(1, 'Siemens');
insert into company(id, name) values(2, 'LG');
insert into company(id, name) values(3, 'Yandex');
insert into company(id, name) values(4, 'Google');

insert into person(id, name, company_id) values(1, 'vova', 1);
insert into person(id, name, company_id) values(2, 'nina', 1);
insert into person(id, name, company_id) values(3, 'misha', 2);
insert into person(id, name, company_id) values(4, 'grisha', 2);
insert into person(id, name, company_id) values(5, 'petya', null);
insert into person(id, name, company_id) values(6, 'kolya', 3);
insert into person(id, name, company_id) values(7, 'azamat', 4);
insert into person(id, name, company_id) values(8, 'bogdan', null);
insert into person(id, name, company_id) values(9, 'aleks', null);

select *
from person as p
where p.company_id is null and p.id = 5

select c.name, p.name
from company as c
join person as p
on c.id = p.company_id

select p.company_id, c.name, count(p.company_id)
from person as p
join company as c
on c.id = p.company_id
where p.company_id is not null
group by p.company_id, c.name
order by p.company_id asc
limit (select count(person.company_id)
from person
join company
on person.company_id = company.id
group by company.name
order by count(person.company_id) desc
limit 1
)
