begin;

drop table if exists "product" cascade;
create table product
(
    id bigserial not null,
    title character(128) not null,
    price bigint
);
create unique index product_id_uindex
    on product (id);
create index product_title_index
    on product (title);
alter table product
    add constraint product_pk
        primary key (id);

drop table if exists "customer" cascade;
create table customer
(
    id bigserial not null,
    name character (128) not null
);
create index customer_id_name_index
    on customer (id, name);
create unique index customer_id_uindex
    on customer (id);
alter table customer
    add constraint customer_pk
        primary key (id);

drop table if exists "order" cascade;
create table "order"
(
    id          bigserial not null
        constraint order_pk
            primary key,
    date        timestamp not null,
    product_id  integer   not null
        constraint order_product_id_fk
            references product
            on update cascade,
    customer_id integer   not null
        constraint order_customer_id_fk
            references customer
            on delete cascade
);

alter table "order"
    owner to postgres;
create index order_customer_id_index
    on "order" (customer_id);
create index order_date_index
    on "order" (date);
create unique index order_id_uindex
    on "order" (id);
create index order_product_id_index
    on "order" (product_id);

commit;
