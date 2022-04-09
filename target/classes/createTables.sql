create table users
(
    id        serial,
    name varchar(255) not null,
    lastName  varchar(255) not null,
    age    integer,
    primary key (id)

);
