INSERT INTO accident_type (id, name)
VALUES (1, 'Одна машина'),
       (2, 'Две машины'),
       (3, 'Машина и человек'),
       (4, 'Машина и велосипед');

INSERT INTO rule (id, name)
VALUES (1, 'Статья 1'),
       (2, 'Статья 2'),
       (3, 'Статья 3'),
       (4, 'Статья 4'),
       (5, 'Статья 5');

INSERT INTO accident (number, text, address, type_id)
VALUES ('number1', 'text1', 'address1', 1),
       ('number2', 'text2', 'address2', 2),
       ('number3', 'text5', 'address5', 3),
       ('number4', 'text4', 'address4', 4);

INSERT INTO accident_rule (rule_id, accident_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (4, 2),
       (4, 3),
       (3, 4);


