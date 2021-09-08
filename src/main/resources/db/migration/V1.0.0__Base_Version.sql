CREATE SCHEMA IF NOT EXISTS demo;

CREATE TABLE IF NOT EXISTS demo.userDO
(
    id                      bigserial    NOT NULL,
    username                varchar(255) NOT NULL unique,
    password                text         NOT NULL,
    account_non_expired     boolean default true,
    account_non_locked      boolean default true,
    credentials_non_expired boolean default true,
    enabled                 boolean default true,
    CONSTRAINT user_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS= FALSE
    );

insert into demo.userDO(username, password)
values ('admin', '$2a$10$LPiHGgqwJJwHjDlpVzqBL.VCUge.Rin0AFKL0xn2sUY/e17zwiLPG');

CREATE TABLE IF NOT EXISTS demo.roleDO
(
    id      bigserial    NOT NULL,
    name    varchar(255) NOT NULL unique,
    enabled boolean default true,
    CONSTRAINT role_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS= FALSE
    );

insert into demo.roleDO(name)
values ('admin');

CREATE TABLE IF NOT EXISTS demo.user_role
(
    user_id int8 NOT NULL,
    role_id int8 NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id)
)
    WITH (
        OIDS= FALSE
    );

alter table demo.user_role
    add constraint demo_user_role_user_fkey foreign key (user_id) references demo.userDO,
    add constraint demo_user_role_role_fkey foreign key (role_id) references demo.roleDO;

insert into demo.user_role(user_id, role_id)
values (1, 1);

CREATE TABLE IF NOT EXISTS demo.menu
(
    id   bigserial    NOT NULL,
    name varchar(255) NOT NULL unique,
    url  varchar(255) NOT NULL,
    CONSTRAINT menu_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS= FALSE
    );
