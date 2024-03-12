CREATE TABLE IF NOT EXISTS loans
(
    id                 VARCHAR(255) NOT NULL PRIMARY KEY,
    phone              VARCHAR(15)  NOT NULL,
    number             BIGINT NOT NULL,
    type               VARCHAR(100) NOT NULL,
    total              BIGINT       NOT NULL DEFAULT 0,
    amount_paid        BIGINT       NOT NULL DEFAULT 0,
    outstanding_amount BIGINT       NOT NULL DEFAULT 0,
    created_at         BIGINT       NOT NULL DEFAULT 0,
    created_by         VARCHAR(20)           DEFAULT NULL,
    updated_at         BIGINT       NOT NULL DEFAULT 0,
    updated_by         VARCHAR(20)           DEFAULT NULL
);
