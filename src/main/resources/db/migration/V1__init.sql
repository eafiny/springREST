create table products (id bigserial primary key, title varchar(255), price int);
insert into products (title, price) values
('Bread', 42),
('Butter', 112),
('Chocolate', 60);