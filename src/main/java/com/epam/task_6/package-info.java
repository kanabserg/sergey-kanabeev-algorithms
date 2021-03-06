/**
 * Модель "справочник музыкального магазина"
 * ...
 * Исполнитель: <имя исполнителя>
 *     Альбом: <название>, <жанр>
 *         Композиция: <название>, <длительность>
 *         ...
 *     Альбом: ...
 * ...
 *
 *
 * 1. Создаём джавовую модель
 * 2. Создаём интерфейс сериализатора / десериализатора. Он будет сохранять данные в указанный текстовый формат.
 * 3. Создаём entity класс, имплементирующий Serializable
 * 4. Создаём ещё одну имплементацию сериализатора, в этот раз чтобы она сериализовала entity классы.
 * 5. При загрузках данные должны валидироваться: альбом без композиций - отмывание денег, а не музыка; исполнитель без альбомов - тоже странный (инди отбрасываются как погрешность)
 *
 * Дополнительное необязательное задание (может помочь понять смысл разделения модели и entity класса)
 * *. Декомпозируем сохраняемый результат так, чтобы одна и та же композиция встречалась в выходном файле лишь один раз, даже если она относится к двум исполнителям.
 * Текстовый формат вывода придумайте сами. Единственное условие: он должен быть полностью понятен человеку (то есть никакого перечисления непонятных полей без имён через запятую)
 *
 * Уточнение по домашней работе с сериализацией: мы формально делим приложение на 2 слоя - калькуляционный и слой хранения.
 * Весь калькуляционный слой работает с вашей джавовой моделью музыкантов, но как только мы хотим всё это сохранить,
 * то работа уходит на слой хранения, тот должен преобразовать вашу модель в entity модель и сохранять уже её.
 * С сохранением в текстовый формат своя отдельная entity модель не требуется, если что.
 *
 *
 * XML
 *
 * Задания:
 * Создаём xml файл содержащий нашу стуктуру данных из предыдущего задания.
 * Нам нужна абстрактная фабрика, которая будет создавать DAO объект, работающий с исполнителями.
 * В этом DAO должны быть методы: получение исполнителя по id, получение всех исполнителей, добавление исполнителя, удаление, апдейт.
 * Должна быть возможность получить суммамрную продолжительность песен исполнителя.
 * Ограничений на выбор средств реализации нет.
 *
 *
 *
 * Log4j and Junit
 *
 * Добавить логирование и тесты DAO
 *
 *
 * JDBC
 *
 * В рамках данного задания будет дорабатываться проект, работающий со справочником музыкального магазина, хранящимся в XML.
 * Цель доработки - миграция данных из XML в БД. Бизнес-логика приложения измениться при это не должна.
 * Результат выполнения задания: код + sql-скрипт.
 * 1. Скачать JDBC42 драйвер для postgreSQL. Добавить его в библиотеки проекта.
 * 2. Создать в БД необходимые для справочника таблицы.
 * 3. Создать новое DAO, по образу и подобию уже существующего.
 * Методы DAO должны быть реализованы посредством параметризованных SQL-запросов (PreparedStatement).
 * 4. Написать java-код, осуществляющий миграцию данных из XML-хранилища в БД.
 **/
package com.epam.task_6;