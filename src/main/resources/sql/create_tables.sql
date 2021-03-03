begin;

drop table if exists "product" cascade;
create table product
(
    id    bigserial      not null,
    title character(128) not null,
    price integer
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
    id   bigserial      not null,
    name character(128) not null
);
create index customer_id_name_index
    on customer (id, name);
create unique index customer_id_uindex
    on customer (id);
alter table customer
    add constraint customer_pk
        primary key (id);

drop table if exists "custorder" cascade;
create table "custorder"
(
    id          bigserial not null
        constraint custorder_pk
            primary key,
    date        timestamp not null,
    amount      integer    not null,
    product_id  integer    not null
        constraint custorder_product_id_fk
            references product
            on update cascade,
    customer_id integer    not null
        constraint custorder_customer_id_fk
            references customer
            on delete cascade
);

alter table "custorder"
    owner to postgres;
create index custorder_customer_id_index
    on "custorder" (customer_id);
create index custorder_date_index
    on "custorder" (date);
create unique index custorder_id_uindex
    on "custorder" (id);
create index custorder_product_id_index
    on "custorder" (product_id);

commit;
