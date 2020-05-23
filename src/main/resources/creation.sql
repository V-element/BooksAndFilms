create schema if not exists schema_baf;

use schema_baf;

create table if not exists books(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    author      VARCHAR(255),
    description TEXT
);

create table if not exists films(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    producer    VARCHAR(255),
    description TEXT,
    year        INT
);