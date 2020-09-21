--liquibase formatted sql
--changeset bharathgoku:2

create table book_inventory(
    id serial primary key not null,
    catalog_id int not null,
    status varchar(100) not null,
    count int,
    created_at timestamp not null,
    updated_at timestamp not null
);

create index if not exists index_on_book_inventory_catalog_id on book_inventory(catalog_id);

-- rollback drop table book_inventory
