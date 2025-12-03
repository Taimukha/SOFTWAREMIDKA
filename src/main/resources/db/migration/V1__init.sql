CREATE TABLE directors (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255)
);

CREATE TABLE genres (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255)
);

CREATE TABLE movies (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(255),
                        year INT,
                        rating DOUBLE PRECISION,
                        director_id BIGINT REFERENCES directors(id)
);

CREATE TABLE movie_genres (
                              movie_id BIGINT REFERENCES movies(id),
                              genre_id BIGINT REFERENCES genres(id),
                              PRIMARY KEY (movie_id, genre_id)
);
