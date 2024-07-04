CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS item(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category_id uuid,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS category(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    title VARCHAR(255) NOT NULL,
    parent_category_id uuid,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS item_category(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    item_id uuid,
    category_id uuid
);