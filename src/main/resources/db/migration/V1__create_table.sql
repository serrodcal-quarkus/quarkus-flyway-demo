CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)  NOT NULL,
    age BIGINT NOT NULL
);

CREATE SEQUENCE persons_seq start with 1 increment by 1;