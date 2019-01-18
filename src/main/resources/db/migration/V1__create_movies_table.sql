CREATE TABLE movies
(
  id           INT          NOT NULL,
  name         VARCHAR(255) NOT NULL,
  genre        VARCHAR(255) NOT NULL,
  duration     VARCHAR(255) NOT NULL,
  release_date DATE         NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name, release_date)
)
  CHARACTER SET = utf8;
