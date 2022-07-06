-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE todo
(
    id           SERIAL PRIMARY KEY,
    body         VARCHAR(255) NOT NULL,
    is_completed BOOL         NOT NULL DEFAULT false
);
-- rollback DROP TABLE todo;
