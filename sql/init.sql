DROP TABLE users;
DROP TABLE doctors;
DROP TABLE persons;
DROP TABLE specializations;

CREATE TABLE users
(
    PRIMARY KEY (id),
    id       serial,
    login    varchar(255) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    role     smallint     NOT NULL DEFAULT 0
);

CREATE TABLE specializations
(
    PRIMARY KEY (id),
    id        serial,
    name      VARCHAR(255) NOT NULL UNIQUE,
    wage_rate FLOAT        NOT NULL,
    narrow    BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE doctors
(
    PRIMARY KEY (id),
    id                  serial,
    "employment_date"   DATE    NOT NULL,
    "salary"            FLOAT   NOT NULL,
    "person_id"         integer NOT NULL,
    "specialization_id" integer NOT NULL,
    "branch_id"         integer NOT NULL
);

CREATE TABLE persons
(
    PRIMARY KEY (id),
    id           serial,
    "name"       VARCHAR(255) NOT NULL,
    "surname"    VARCHAR(255) NOT NULL,
    "patronymic" VARCHAR(255) NOT NULL,
    "sex"        integer      NOT NULL,
    "dob"        DATE         NOT NULL
);

ALTER TABLE doctors
    ADD CONSTRAINT "doctor_fk0" FOREIGN KEY ("person_id") REFERENCES persons ("id");
ALTER TABLE doctors
    ADD CONSTRAINT "doctor_fk1" FOREIGN KEY ("specialization_id") REFERENCES "specializations" ("id");


INSERT INTO users(login, password)
VALUES ('admin', 'admin');

INSERT INTO specializations(name, wage_rate, narrow)
VALUES ('Хирург', 4000, FALSE),
       ('Фельдшер', 2400, FALSE),
       ('Фармацевт', 2500, FALSE),
       ('Неонатолога', 3230, TRUE);
