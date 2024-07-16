CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS item(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    title VARCHAR(50) NOT NULL CHECK (length(trim(title)) >= 3),
    picture_path VARCHAR(100),
    description VARCHAR(1000) NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS category(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    title VARCHAR(50) NOT NULL CHECK (length(trim(title)) >= 3),
    parent_category_id uuid,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS item_category(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    item_id uuid,
    category_id uuid
);