```CREATE TABLE customer(
id bigserial PRIMARY KEY,
name varchar NOT NULL,
surname varchar NOT NULL,
email varchar NOT NULL,
birth_date date NOT NULL,
created_at timestamp DEFAULT current_timestamp,
updated_at timestamp DEFAULT current_timestamp,
active bool DEFAULT true
)
```