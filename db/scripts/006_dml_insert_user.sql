insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$gWzYzP8RWPpafKoiFX2S0eiokZ4dcPbTquO7mU8kv/EkTaZovSQsG',
        (select id from authorities where authority = 'ROLE_ADMIN'))