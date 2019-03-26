DROP TABLE IF EXISTS users;
DROP TABLE cities;
DROP TABLE groups;
DROP TABLE projects;
DROP SEQUENCE IF EXISTS user_seq;
DROP TYPE IF EXISTS user_flag;
DROP TYPE IF EXISTS group_type;

CREATE TYPE user_flag AS ENUM ('active', 'deleted', 'superuser');

CREATE TYPE group_type AS ENUM ('FINISHED', 'CURRENT');

CREATE SEQUENCE user_seq START 100000;

CREATE TABLE projects
(
  name        TEXT PRIMARY KEY NOT NULL,
  description TEXT             NOT NULL,
  CONSTRAINT project_name UNIQUE (name)
);

CREATE TABLE groups
(
  name        TEXT PRIMARY KEY NOT NULL,
  type        group_type       NOT NULL,
  project_ref TEXT             NOT NULL,
  CONSTRAINT group_name UNIQUE (name),
  FOREIGN KEY (project_ref) REFERENCES projects (name) ON DELETE CASCADE
);

CREATE TABLE cities
(
  id   TEXT PRIMARY KEY NOT NULL,
  name TEXT             NOT NULL
);

CREATE TABLE users
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  full_name TEXT      NOT NULL,
  email     TEXT      NOT NULL,
  flag      user_flag NOT NULL,
  city_ref  TEXT      NOT NULL,
  FOREIGN KEY (city_ref) REFERENCES cities (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX email_idx ON users (email);