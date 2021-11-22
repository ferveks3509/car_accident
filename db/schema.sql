create table rule (
    id serial primary key,
    name varchar(200)
);
create table type (
    id serial primary key,
    name varchar(200)
);
create table accident (
    id serial primary key,
    name varchar(200),
    text varchar(200),
    address varchar(200),
    type_id int references type(id),
);
create table accident_rule(
    id serial primary key,
    accident_id int references accident(id),
    rule_id int references rule(id),
)

insert into type(name) values ('Две машины'),('Машина и человек'),('Машина и велосипед');
insert into rule(name) values ('Статья. 1'),('Статья. 2'),('Статья. 3');
insert into accident(name, text, address, type_id) values ('name1', 'desc1', 'address1', 1, 1);
insert into accident_rule (accident_id, rule_id) values (1, 1);