CREATE TABLE students
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255)          NULL,
    age   INT                   NOT NULL,
    email VARCHAR(255)          NULL,
    CONSTRAINT pk_students PRIMARY KEY (id)
);