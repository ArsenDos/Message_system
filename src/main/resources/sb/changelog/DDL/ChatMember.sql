create table chat_member (
        id bigint primary key GENERATED ALWAYS AS IDENTITY,
        chat_id bigint references chat(id) ON DELETE CASCADE,
        user_id bigint references users(id) ON DELETE CASCADE ,
        joined_at timestamp
)