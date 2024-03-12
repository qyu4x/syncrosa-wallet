SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'public';

CREATE TABLE IF NOT EXISTS customers
(
    id         VARCHAR(255) NOT NULL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    phone      VARCHAR(100) NOT NULL,
    created_at BIGINT       NOT NULL,
    created_by VARCHAR(20)  NOT NULL,
    updated_at BIGINT      DEFAULT 0,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    number         BIGINT       NOT NULL PRIMARY KEY,
    customer_id    VARCHAR(255) NOT NULL UNIQUE,
    type           VARCHAR(100) NOT NULL,
    branch_address VARCHAR(200) NOT NULL,
    created_at     BIGINT       NOT NULL,
    created_by     VARCHAR(20)  NOT NULL,
    updated_at     BIGINT      DEFAULT 0,
    updated_by     VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);
