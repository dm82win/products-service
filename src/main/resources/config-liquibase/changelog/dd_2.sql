--liquibase formatted sql

--changeset Dmitry.Chekunov:dd_2
--comment Создание таблицы product

create table if not exists product
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    description     VARCHAR(255),
    price           DECIMAL(8,2) NOT NULL,
    image           VARCHAR(500),
    category_id     INT NOT NULL,
    in_stock        BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

