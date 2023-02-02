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

insert into company
values(1,'Google'), (2, 'BMW'), (3, 'LADA'), (4, 'LG'), (5, 'VAG');
insert into person
values (1, 'Anton', 1), (2, 'Bob', 1), (3, 'Bill', 2), (4, 'Rob', 2), (5, 'Vas', 3), (6, 'Aleks', 4);

--1
select name from person
where id = 5 and company_id is null;

--2
select person.name, company.name from person
join company on company_id = company.id;

--3
select c.name, count(p.name) as Количество
from company c
join person p on p.company_id = c.id
group by c.name
having count(company_id) = (
	select max(maximum)
	from (select company_id, count(company_id) as maximum
	from person
	group by company_id) as q1);

