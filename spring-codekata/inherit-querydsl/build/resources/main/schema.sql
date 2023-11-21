CREATE TABLE score
(
    student_name VARCHAR(255),
    subject_name VARCHAR(255),
    point        INT,
    student_id   BIGINT NOT NULL,
    subject_id   BIGINT NOT NULL,
    score_id     BIGINT NOT NULL,
    CONSTRAINT pk_score PRIMARY KEY (student_id, subject_id, score_id)
);

CREATE TABLE student
(
    student_name       VARCHAR(255),
    subject_name       VARCHAR(255),
    grade              VARCHAR(255),
    student_id         BIGINT NOT NULL,
    subject_id         BIGINT NOT NULL,
    student_subject_id BIGINT NOT NULL,
    CONSTRAINT pk_student PRIMARY KEY (student_id, subject_id, student_subject_id)
);

CREATE TABLE subject
(
    student_name       VARCHAR(255),
    subject_name       VARCHAR(255),
    description        VARCHAR(255),
    student_id         BIGINT NOT NULL,
    subject_id         BIGINT NOT NULL,
    unique_subject_key BIGINT NOT NULL,
    CONSTRAINT pk_subject PRIMARY KEY (student_id, subject_id, unique_subject_key)
);