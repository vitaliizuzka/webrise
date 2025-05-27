CREATE TABLE users
(
    id    BIGSERIAL PRIMARY KEY,
    email varchar(50) NOT NULL UNIQUE,
    name  varchar(50) NOT NULL
);

CREATE TABLE subscription
(
    id   SERIAL PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE user_subscription
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT REFERENCES users (id),
    subscription_id INT REFERENCES subscription (id),
    UNIQUE (user_id, subscription_id)
);

INSERT INTO users (email, name)
VALUES ('ivan@mail.ru', 'ivan'),
       ('vasya@gmail.com', 'vasya'),
       ('igor@tyandex.ru', 'igor'),
       ('tolya@mail.ru', 'tolya'),
       ('misha@gmail.com', 'misha'),
       ('vika@yandex.ru', 'vika'),
       ('anya@rambler.ru', 'anya');

INSERT INTO subscription (name)
VALUES ('Youtube Premium'),
       ('VK Музыка'),
       ('Yandex.Плюс'),
       ('Netflix');

INSERT INTO user_subscription (user_id, subscription_id)
VALUES (1, 1),
       (1, 4),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 4),
       (4, 3),
       (4, 2),
       (5, 1),
       (5, 2),
       (5, 3),
       (5, 4),
       (6, 1),
       (6, 2),
       (7, 1);