create table message (
    id bigint primary key GENERATED ALWAYS AS IDENTITY,
    chat_id bigint references chat(id) ON DELETE CASCADE ,
    mongo_id varchar(255) not null,
    created_at timestamp
               )