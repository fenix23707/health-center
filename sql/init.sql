DROP TABLE users;

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
    id        serial       NOT NULL,
    name      VARCHAR(255) NOT NULL UNIQUE,
    wage_rate FLOAT        NOT NULL UNIQUE,
    narrow    BOOLEAN      NOT NULL,
    CONSTRAINT specializations_pk PRIMARY KEY ("id")
);

CREATE TABLE doctor
(
    PRIMARY KEY (id),
    id                  serial,
    "employment_date"   DATE    NOT NULL,
    "salary"            FLOAT   NOT NULL,
    "person_id"         integer NOT NULL,
    "specialization_id" integer NOT NULL,
    "branch_id"         integer NOT NULL
);

CREATE TABLE person
(
    PRIMARY KEY (id),
    id           serial,
    "name"       VARCHAR(255) NOT NULL,
    "surname"    VARCHAR(255) NOT NULL,
    "patronymic" VARCHAR(255) NOT NULL,
    "sex"        integer      NOT NULL,
    "dob"        DATE         NOT NULL
);

ALTER TABLE "doctor"
    ADD CONSTRAINT "doctor_fk0" FOREIGN KEY ("person_id") REFERENCES "person" ("id");
ALTER TABLE "doctor"
    ADD CONSTRAINT "doctor_fk1" FOREIGN KEY ("specialization_id") REFERENCES "specializations" ("id");


INSERT INTO users(login, password)
VALUES ('admin', 'admin');
