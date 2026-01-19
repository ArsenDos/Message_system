CREATE TABLE refresh_tokens(
    id bigint primary key GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL references users(id),
    token_value BIGINT NOT NULL,
    expiry_date TIMESTAMP NOT NULL
)