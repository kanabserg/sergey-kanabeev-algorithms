CREATE TABLE tenant (
  num_passport  INT PRIMARY KEY,
  FIO           VARCHAR(40),
  address       VARCHAR(40),
  square        INT CHECK (square > 10),
  num_residents INT
);

CREATE TABLE services (
  type_service VARCHAR(20) PRIMARY KEY,
  name         VARCHAR(200) UNIQUE
);

CREATE TABLE receipt (
  code_receipt INT PRIMARY KEY,
  num_passport INT REFERENCES tenant (num_passport),
  date         DATE,
  payment      INT
);

CREATE TABLE rate (
  code_rate    INT,
  type_service VARCHAR(20) REFERENCES services (type_service),
  year         INT,
  month        INT,
  price        MONEY,
  CONSTRAINT fk_rate_serv PRIMARY KEY (code_rate, type_service)
);

CREATE TABLE record (
  code_receipt INT REFERENCES receipt (code_receipt),
  type_service VARCHAR(20),
  code_rate    INT,
  sizes        INT,
  payment      MONEY,
  CONSTRAINT fk_rec_rate FOREIGN KEY (code_rate, type_service) REFERENCES rate (code_rate, type_service),
  CONSTRAINT pk_rec PRIMARY KEY (code_receipt, type_service, code_rate)
);


INSERT INTO tenant
VALUES (526543, 'Pypkin', 'Promishlennosti 14-55', 50, 3),
  (867534, 'Arsenyev', 'Lenina 1-139', 76, 2),
  (175623, 'Gerasimov', 'Solnechnaya 143-13', 39, 1),
  (667834, 'Pinokio', 'Velikolepnaya 142-54', 48, 3),
  (237341, 'Tereyokhin', 'Vladimirskaya 1-1', 39, 1);

INSERT INTO services
VALUES ('Gaz', 'Montly payment for the gaz'),
  ('Water', 'Montly payment for the cold and hot water'),
  ('Light', 'Montly payment for the light'),
  ('Phone', 'Montly payment for the phone');

INSERT INTO receipt
VALUES (001, 526543, '2014-5-07', 5499),
  (002, 867534, '2014-12-02', 9543),
  (003, 175623, '2015-07-01', 4755);

INSERT INTO rate
VALUES (123, 'Gaz', 2013, 12, 50),
  (126, 'Gaz', 2013, 12, 55),
  (124, 'Light', 2013, 07, 70),
  (125, 'Phone', 2012, 5, 20);

INSERT INTO record
VALUES (001, 'Gaz', 123, 5, 250),
  (002, 'Gaz', 126, 7, 275),
  (003, 'Phone', 125, 3, 60);