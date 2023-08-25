--liquibase formatted sql

--changeset DeevS:1
create table if not exists users
(
    id                   bigserial primary key,
    distinguished_name   varchar(255) not null unique,
    common_name          varchar(255),
    canonical_name       varchar(255),
    guid                 varchar(255) not null unique,
    user_principal_name  varchar(255),
    display_name         varchar(255),
    full_name            varchar(255),
    firstname            varchar(255),
    lastname             varchar(255),
    other_name           varchar(255),
    initials             varchar(255),
    telephone_number     varchar(255),
    home_phone           varchar(255),
    mobile_phone         varchar(255),
    country              varchar(255),
    state                varchar(255),
    city                 varchar(255),
    street               varchar(255),
    postal_code          varchar(255),
    company              varchar(255),
    organization         varchar(255),
    division             varchar(255),
    department           varchar(255),
    office               varchar(255),
    manager              varchar(255),
    employee_id          varchar(255),
    employee_number      varchar(255),
    mail                 varchar(255),
    mail_nickname        varchar(255),
    sam_account_name     varchar(255),
    office_phone         varchar(255),
    ip_phone             varchar(255),
    title                varchar(255),
    enabled              varchar(255),
    is_active            boolean      not null default true,
    sync_date            timestamp    not null default current_timestamp
);
