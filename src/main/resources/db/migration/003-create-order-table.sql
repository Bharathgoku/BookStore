--liquibase formatted sql
--changeset bharathgoku:3

create table book_order(
    id serial primary key not null,
    catalog_id int not null,
    status varchar(100) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);

create index if not exists index_on_order_catalog_id on book_order(catalog_id);

-- rollback drop table book_order;