DROP TABLE IF EXISTS users_batis;

CREATE TABLE users_batis (
    id   bigserial primary key,
    name varchar not null,
    age  varchar,
    username varchar(50) not null unique,
    password varchar     not null
);


insert into users_batis(name, age, username, password)
values ('Asqar', '23','asqar','123'),
       ('Uchqun', '28', 'uchqun','123'),
       ('Baxodir', '18', 'baxodir','123'),
       ('Doston', '22', 'doston','123'),
       ('Nodir', '27', 'nodir','123'),
       ('Aziz', '16', 'aziz','123'),
       ('Mirkomil', '24', 'mirkomil','123'),
       ('Eliboy', '25', 'eliboy','123');