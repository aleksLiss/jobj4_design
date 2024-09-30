create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create or replace function get_tax() 
returns trigger as 
$$
	BEGIN
	update products
	set price = price + (price * 0.2);
	RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
after insert on products
for each statement
execute procedure get_tax();

create trigger tax_before_trigger
before insert on products
for each row
execute procedure get_tax();

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function add_to_table()
returns trigger as 
$$
	BEGIN
		insert into history_of_price(name, price, date)
		values(NEW.name, NEW.price, current_timestamp);
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger add_to_history_of_price
after insert on products
for each row
execute procedure add_to_table();
