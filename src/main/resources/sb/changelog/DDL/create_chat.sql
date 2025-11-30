
create table chat (
    id bigint primary key GENERATED ALWAYS AS IDENTITY,
    name varchar(255) not null,
    created_by bigint references users(id) on delete set null
)