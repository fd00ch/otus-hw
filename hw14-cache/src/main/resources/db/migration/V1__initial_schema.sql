create table test
(
    id   int,
    name varchar(50)
);
create table client
(
    id uuid DEFAULT gen_random_uuid() primary key,
    name varchar(50)
);

