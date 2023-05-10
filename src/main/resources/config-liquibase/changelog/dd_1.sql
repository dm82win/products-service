--liquibase formatted sql

--changeset Dmitry.Chekunov:dd_1
--comment Создание таблицы category

create table if not exists category
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(100) NOT NULL
);