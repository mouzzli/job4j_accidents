CREATE TABLE IF NOT EXISTS accident_type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS rule
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE accident
(
    id      SERIAL PRIMARY KEY,
    number   VARCHAR NOT NULL,
    text    VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    type_id INT REFERENCES accident_type (id)
);

CREATE TABLE accident_rule
(
    id          SERIAL PRIMARY KEY,
    rule_id     INT REFERENCES rule (id),
    accident_id INT REFERENCES accident (id)
);