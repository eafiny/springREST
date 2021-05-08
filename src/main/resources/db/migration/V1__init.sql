create table categories (id bigserial primary key, title varchar(255));
insert into categories (title) values ('Food');

create table products (id bigserial primary key, title varchar(255), price int, category_id bigint references categories (id));
insert into products (title, price, category_id) values
('Bread', 42, 1),
('Butter', 112, 1),
('Chocolate', 60, 1),
('Chocolate2', 60, 1),
('Chocolate3', 60, 1),
('Chocolate4', 60, 1),
('Chocolate5', 60, 1),
('Chocolate6', 60, 1),
('Chocolate7', 60, 1),
('Chocolate8', 60, 1),
('Chocolate9', 60, 1),
('Chocolate10', 60, 1),
('Chocolate11', 60, 1),
('Chocolate12', 60, 1),
('Chocolate13', 60, 1),
('Chocolate14', 60, 1),
('Chocolate15', 60, 1),
('Chocolate16', 60, 1),
('Chocolate17', 60, 1),
('Chocolate18', 60, 1),
('Chocolate19', 60, 1),
('Chocolate20', 60, 1),
('Chocolate21', 60, 1),
('Chocolate22', 60, 1),
('Chocolate23', 60, 1),
('Chocolate24', 60, 1);