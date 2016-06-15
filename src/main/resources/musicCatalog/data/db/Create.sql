CREATE SEQUENCE serial_of_id
INCREMENT 1
START 100
MAXVALUE 100000000;

CREATE TABLE artist
(
  artist_id INT PRIMARY KEY DEFAULT nextval('serial_of_id' :: REGCLASS),
  title     VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE album
(
  album_id  INT PRIMARY KEY DEFAULT nextval('serial_of_id' :: REGCLASS),
  title     VARCHAR(50) NOT NULL UNIQUE,
  genre     VARCHAR(20) NOT NULL,
  artist_id BIGINT      NOT NULL REFERENCES artist (artist_id) ON DELETE CASCADE
);

CREATE TABLE song
(
  song_id  INT PRIMARY KEY DEFAULT nextval('serial_of_id' :: REGCLASS),
  title    VARCHAR(50) NOT NULL,
  album_id BIGINT      NOT NULL REFERENCES album (album_id) ON DELETE CASCADE,
  length   FLOAT       NOT NULL
)