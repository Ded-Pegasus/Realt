create sequence hibernate_sequence start 1 increment 1;

create table listing (
    id int4 not null,
    price varchar(255),
    filename varchar(255),
    filename1 varchar(255),
    filename2 varchar(255),
    tag varchar(255),
    text text,
    title varchar(255),
    user_id int8,
    DELETED boolean,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255));

create table usr (
    id int8 not null,
    mail varchar(255),
    password varchar(255) not null,
    phone_number varchar(255),
    username varchar(255) not null,
    primary key (id));

alter table if exists listing
    add constraint listing_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;