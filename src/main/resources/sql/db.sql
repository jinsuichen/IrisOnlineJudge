create table tb_permissions
(
    permission_id bigint auto_increment
        primary key,
    name          varchar(50) not null
);

create table tb_user_permissions
(
    user_id       bigint not null,
    permission_id bigint not null,
    primary key (user_id, permission_id)
);

create table tb_users
(
    user_id  bigint auto_increment
        primary key,
    handle   varchar(50) not null,
    nickname varchar(50) not null
);

