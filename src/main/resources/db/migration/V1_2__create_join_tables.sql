CREATE TABLE actor_positions
(
  id        CHAR(36) NOT NULL,
  movie_id  CHAR(36) NOT NULL,
  person_id CHAR(36) NOT NULL,
  created   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (movie_id) REFERENCES movies (id),
  FOREIGN KEY (person_id) REFERENCES persons (id)
)
  CHARACTER SET = utf8;

CREATE TABLE writers_positions
(
  id        CHAR(36) NOT NULL,
  movie_id  CHAR(36) NOT NULL,
  person_id CHAR(36) NOT NULL,
  created   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (movie_id) REFERENCES movies (id),
  FOREIGN KEY (person_id) REFERENCES persons (id)
)
  CHARACTER SET = utf8;
