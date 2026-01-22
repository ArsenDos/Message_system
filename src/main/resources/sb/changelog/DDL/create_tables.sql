DROP TABLE IF EXISTS roles_authorities;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS jwt_token;
DROP TABLE IF EXISTS chat_member;
DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS users;
-- =======================================================
-- Table: users
-- =======================================================
CREATE TABLE roles(
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(100) UNIQUE NOT NULL
);
-- =======================================================
-- Table: chat
-- =======================================================
create table users (
                       id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       username VARCHAR(64) NOT NULL UNIQUE,
                       email VARCHAR(128) NOT NULL unique,
                       password VARCHAR(2048) NOT NULL ,
                       enabled BOOLEAN DEFAULT FALSE,
                       role_id BIGINT REFERENCES roles(id) ON DELETE SET NULL
);
-- =======================================================
-- Table: member_chat
-- =======================================================
create table chat (
                      id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      name VARCHAR(255) NOT NULL,
                      created_by BIGINT REFERENCES users(id) ON DELETE SET NULL
);
-- =======================================================
-- Table: jwt_tokens
-- =======================================================
create table chat_member (
                             id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                             chat_id BIGINT REFERENCES chat(id) ON DELETE CASCADE,
                             user_id BIGINT REFERENCES users(id) ON DELETE CASCADE ,
                             joined_at TIMESTAMP
);
-- =======================================================
-- Table: authorities
-- =======================================================
create TABLE jwt_token (
                           id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                           user_id BIGINT REFERENCES users(id),
                           token_value VARCHAR(512) NOT NULL,
                           expired_date TIMESTAMP NOT NULL,
                           created_at TIMESTAMP
);

-- =======================================================
-- Table: roles_authorities
-- =======================================================
CREATE TABLE authorities (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(100) UNIQUE NOT NULL
);
-- =======================================================
-- Table: roles
-- =======================================================
CREATE TABLE roles_authorities (
                                   role_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
                                   authority_id BIGINT NOT NULL REFERENCES authorities(id) ON DELETE CASCADE,
                                   PRIMARY KEY (role_id, authority_id)
);

