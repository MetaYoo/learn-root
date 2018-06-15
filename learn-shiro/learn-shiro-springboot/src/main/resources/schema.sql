drop table if EXISTS  auth_user;
CREATE TABLE auth_user (
  `id` char(50) not null primary key,
  `name` char(100),
  `password` char(256),
  `email` char(50),
  `salt` char (50)
);

drop table if EXISTS  auth_token;
CREATE TABLE auth_token (
  `id` char(50) not null primary key,
  `token` char(100),
  `expire_time` TIMESTAMP,
  `update_time` TIMESTAMP
);
