--Плата по тарифу за услугу
SELECT
  rt.type_service      AS "Вид услуги",
  rt.code_rate         AS "Код тарифа",
  r.payment :: NUMERIC AS "Плата"
FROM rate rt
  INNER JOIN record r
    ON rt.code_rate = r.code_rate;

--Услуги  содержащие 'h', находящиеся в ценовом диапазоне от 10 до 80
SELECT r.type_service AS "Вид услуги"
FROM rate r
WHERE r.type_service LIKE '%h%'
      AND r.price :: NUMERIC BETWEEN 10 AND 80;

--Переименование Услуг
SELECT
  CASE WHEN type_service = 'Water'
    THEN 'Вода'
  WHEN type_service = 'Gaz'
    THEN 'Газ'
  WHEN type_service = 'Light'
    THEN 'Свет'
  WHEN type_service = 'Phone'
    THEN 'Телефон'
  END,
  namen
FROM services;

--Вывести максимальную плозадь, на которой проживают 3 человека
SELECT *
FROM tenant t;
SELECT
  t.num_residents "Кол-во жителей",
  MAX(t.square)   "Наибольшая площадь"
FROM tenant t
GROUP BY t.num_residents
HAVING MAX(t.num_residents) = 3;

--Платежки арендаторов за 2014	
SELECT
  t.num_passport AS "№ пасспорта",
  rt.payment     AS "Платеж"
FROM tenant AS t,
  (SELECT
     dated,
     payment,
     num_passport
   FROM receipt) AS rt
WHERE date_part('year', rt.dated) = 2014
      AND t.num_passport = rt.num_passport;
