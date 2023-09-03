create table address (id int8 not null, street varchar(255), primary key (id));
alter table client add column address_id int8;
create table phone (id int8 not null, number varchar(255), client_id int8, primary key (id));
alter table client drop constraint if exists UK_d7c4jgrjortusykiwq2728d2g;
alter table client add constraint UK_d7c4jgrjortusykiwq2728d2g unique (address_id);
alter table client add constraint FKb137u2cl2ec0otae32lk5pcl2 foreign key (address_id) references address;
alter table if exists phone add constraint FK3o48ec26lujl3kf01hwqplhn2 foreign key (client_id) references client;