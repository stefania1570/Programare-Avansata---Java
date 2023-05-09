CREATE TABLE albums (
    id SERIAL PRIMARY KEY,
    release_year INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    artist VARCHAR(255) NOT NULL
);

CREATE TABLE artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE albums_genres (
    album_id INTEGER NOT NULL REFERENCES albums(id),
    genre_id INTEGER NOT NULL REFERENCES genres(id),
    PRIMARY KEY (album_id, genre_id)
);