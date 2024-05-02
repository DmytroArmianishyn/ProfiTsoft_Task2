CREATE TABLE IF NOT EXISTS Team (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(255) UNIQUE,
    city VARCHAR(255)
    );
CREATE UNIQUE INDEX idx_team_name ON Team (name);
    INSERT INTO Team (name, city) VALUES ('Los Angeles Lakers', 'Los Angeles'),
                                     ('Golden State Warriors', 'San Francisco'),
                                     ('Brooklyn Nets', 'New York');


CREATE TABLE IF NOT EXISTS Player (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(255),
    position VARCHAR(255),
    age INTEGER,
    team_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES Team(id)
    );
INSERT INTO Player (name, position, age, team_id) VALUES ('LeBron James', 'Forward', 36, 1),
                                                         ('Stephen Curry', 'Guard', 33, 2),
                                                         ('Kevin Durant', 'Forward', 33, 3),
                                                         ('Anthony Davis', 'Center', 28, 1),
                                                         ('Klay Thompson', 'Guard', 32, 2),
                                                         ('Kyrie Irving', 'Guard', 30, 3);