CREATE TABLE persons
(
  id         CHAR(36)                NOT NULL,
  version    INT                     NOT NULL DEFAULT 1,
  first_name VARCHAR(255)            NOT NULL,
  last_name  VARCHAR(255)            NOT NULL,
  gender     ENUM ('MALE', 'FEMALE') NOT NULL,
  age        INT                     NOT NULL,
  created    TIMESTAMP                        DEFAULT CURRENT_TIMESTAMP,
  updated    TIMESTAMP                        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  CHARACTER SET = utf8;

CREATE TABLE movies
(
  id           CHAR(36)     NOT NULL,
  version      INT          NOT NULL DEFAULT 1,
  name         VARCHAR(255) NOT NULL,
  genre        VARCHAR(255) NOT NULL,
  duration     VARCHAR(255) NOT NULL,
  release_date DATE         NOT NULL,
  director_id  CHAR(36)     NOT NULL,
  created      TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  updated      TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE (name, release_date),
  FOREIGN KEY (director_id) REFERENCES persons (id)
)
  CHARACTER SET = utf8;
