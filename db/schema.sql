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
);
CREATE TABLE authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);
insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');
insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        (select id from authorities where authority = 'ROLE_ADMIN'));
insert into type(name) values ('Две машины'),('Машина и человек'),('Машина и велосипед');
insert into rule(name) values ('Статья. 1'),('Статья. 2'),('Статья. 3');
insert into accident(name, text, address, type_id) values ('name1', 'desc1', 'address1', 1, 1);
insert into accident_rule (accident_id, rule_id) values (1, 1);