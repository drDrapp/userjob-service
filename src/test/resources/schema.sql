DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS userjob_info;
DROP TABLE IF EXISTS company;

DROP SEQUENCE IF EXISTS users_seq;
DROP SEQUENCE IF EXISTS company_seq;
DROP SEQUENCE IF EXISTS userjob_info_seq;

CREATE TABLE users
(
    id          INT NOT NULL AUTO_INCREMENT,
    family_name VARCHAR(50),
    middle_name VARCHAR(50),
    first_name  VARCHAR(50),
    birthday    DATE,
    gender      VARCHAR(50),
    age         INT,
    description VARCHAR(250),
    is_blocked  BOOLEAN,
    created     TIMESTAMP,
    updated     TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE SEQUENCE users_seq AS LONG START WITH 10000;

CREATE TABLE company
(
    id           INT NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(50),
    description  VARCHAR(250),
    is_activity  BOOLEAN,
    created      TIMESTAMP,
    updated      TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE SEQUENCE company_seq AS LONG START WITH 10000;

CREATE TABLE userjob_info
(
    id          INT NOT NULL AUTO_INCREMENT,
    user_id     INT NOT NULL,
    company_id  INT NOT NULL,
    description VARCHAR(250),
    is_activity BOOLEAN,
    created     TIMESTAMP,
    updated     TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE SEQUENCE userjob_info_seq AS LONG START WITH 10000;