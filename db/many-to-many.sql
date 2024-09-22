create table students(
	id serial primary key,
	name varchar(20)
);

create table lessons(
	id serial primary key,
	name varchar(20)
);

create table students_lessons(
	id serial primary key,
	student_id int references students(id),
	lesson_id int references lessons(id)
);

insert into students(name) values('Nina');
insert into students(name) values('Vova');
insert into students(name) values('Vasya');

insert into lessons(name) values('Math');
insert into lessons(name) values('History');
insert into lessons(name) values('Phisic');

insert into students_lessons(student_id, lesson_id) values(1, 1);
insert into students_lessons(student_id, lesson_id) values(1, 2);
insert into students_lessons(student_id, lesson_id) values(2, 2);
insert into students_lessons(student_id, lesson_id) values(2, 3);
insert into students_lessons(student_id, lesson_id) values(3, 1);
insert into students_lessons(student_id, lesson_id) values(3, 2);
insert into students_lessons(student_id, lesson_id) values(3, 3);