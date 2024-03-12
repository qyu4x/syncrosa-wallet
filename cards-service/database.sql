CREATE TABLE IF NOT EXISTS cards
(
    id               VARCHAR(255) NOT NULL PRIMARY KEY,
    phone            VARCHAR(15)  NOT NULL,
    number           BIGINT       NOT NULL,
    type             VARCHAR(100) NOT NULL,
    total_limit      BIGINT       NOT NULL DEFAULT 0,
    amount_used      BIGINT       NOT NULL DEFAULT 0,
    available_amount BIGINT       NOT NULL DEFAULT 0,
    created_at       BIGINT       NOT NULL,
    created_by       VARCHAR(20)  NOT NULL,
    updated_at       BIGINT                DEFAULT 0,
    updated_by       VARCHAR(20)           DEFAULT NULL
)