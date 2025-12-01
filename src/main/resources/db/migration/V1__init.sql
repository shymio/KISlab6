CREATE TABLE IF NOT EXISTS settlements (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50),
    postal_index VARCHAR(6),
    population INTEGER,
    phone VARCHAR(50),
    area DOUBLE PRECISION,
    oktmo VARCHAR(11),
    density DOUBLE PRECISION
);

INSERT INTO settlements (name, type, postal_index, population, phone, area, oktmo, density)
VALUES ('Новая Деревня', 'деревня', '125080', 1200, '+7-999-123-45-67', 35.5, '45328000000', 34.0),
       ('Малое Село', 'село', '654321', 500, '+7-111-222-33-44', 10.0, '12345678901', 50.0),
       ('Большой Город', 'город', '101000', 500000, '+7-222-333-44-55', 150.0, '98765432100', 3333.3);
