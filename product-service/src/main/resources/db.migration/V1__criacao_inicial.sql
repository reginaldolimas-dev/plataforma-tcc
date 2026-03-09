CREATE TABLE product
(
    id bigint PRIMARY KEY,
    name varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    price numeric(10,2) NOT NULL,
    quantity integer NOT NULL,
    created_at timestamp DEFAULT current_timestamp,
    updated_at timestamp DEFAULT current_timestamp
)