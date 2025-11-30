
create table users (
                      id bigint primary key GENERATED ALWAYS AS IDENTITY,
                      username varchar(255) not null,
                      email varchar(255) not null unique,
                      password varchar(255) not null,
                      created_at timestamp
)