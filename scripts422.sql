CREATE TABLE car
(
    id INTEGER PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER CHECK (price > 0)
);

CREATE TABLE users
(
    name TEXT PRIMARY KEY,
    age  INTEGER CHECK (age > 0),
    car_license BOOLEAN,
    car_id INTEGER REFERENCES car (id)
);
