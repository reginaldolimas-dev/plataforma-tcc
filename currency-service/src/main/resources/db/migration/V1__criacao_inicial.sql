CREATE TABLE currencies (
                            code        VARCHAR(3)        NOT NULL,
                            value       DOUBLE PRECISION  NOT NULL,
                            created_at  TIMESTAMP         NOT NULL,
                            updated_at  TIMESTAMP         NOT NULL,

                            CONSTRAINT pk_currencies PRIMARY KEY (code)
);