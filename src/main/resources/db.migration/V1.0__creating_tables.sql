CREATE EXTENSION IF NOT EXIST "uuid-ossp";

CREATE TABLE item(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    title VARCHAR(255) NOT NULL,
    category_id uuid,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE category(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    title VARCHAR(255) NOT NULL,
    parent_category_id uuid,
    created_at TIMESTAMP NOT NULL
);