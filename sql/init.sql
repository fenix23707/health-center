
DROP TABLE users;

CREATE TABLE users
(
    PRIMARY KEY (id),
    id       serial,
    login    varchar(255) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    role     smallint     NOT NULL DEFAULT 0,
    status   smallint     NOT NULL DEFAULT 0
);

SELECT * FROM users;

INSERT INTO users(login, password)
VALUES ('admin', 'admin');
