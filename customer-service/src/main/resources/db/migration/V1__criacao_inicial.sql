CREATE TABLE customer
(
    id         UUID                                    NOT NULL,
    name       VARCHAR(255)                            NOT NULL,
    surname    VARCHAR(255)                            NOT NULL,
    email      VARCHAR(255)                            NOT NULL,
    birth_date date                                    NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    active     BOOLEAN,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);