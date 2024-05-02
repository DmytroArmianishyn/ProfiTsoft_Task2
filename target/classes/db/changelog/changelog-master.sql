CREATE TABLE IF NOT EXISTS Team (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    city VARCHAR(255)
    );



CREATE TABLE IF NOT EXISTS Player (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255),
    position VARCHAR(255),
    age INTEGER,
    team_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES Team(id)
    );



