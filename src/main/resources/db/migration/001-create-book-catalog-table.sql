--liquibase formatted sql
--changeset bharathgoku:1

create table book_catalog(
    id serial primary key not null,
    title text not null,
    isbn varchar(100) not null,
    author varchar(200) not null,
    price float,
    created_at timestamp not null,
    updated_at timestamp not null
);

create index if not exists index_on_book_catalog_isbn_number on book_catalog(isbn);
create index if not exists index_on_book_catalog_title on book_catalog(title);
create index if not exists index_on_book_catalog_author on book_catalog(author);
alter table book_catalog add constraint isbn_unique unique (isbn);

-- rollback drop table book_catalog