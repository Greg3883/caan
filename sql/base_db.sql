CREATE TABLE `movie` (
  `movie_id` int PRIMARY KEY AUTO_INCREMENT,
  `movie_full_name_en` varchar(255),
  `movie_full_name_fr` varchar(255),
  `movie_imdb_id` int,
  `movie_rating` float,
  `movie_release_date` datetime,
  `movie_poster` varchar(255),
  `movie_rating_date` datetime,
  `movie_created_at` timestamp,
  `movie_updated_at` timestamp
);

CREATE TABLE `genre` (
  `genre_id` int PRIMARY KEY AUTO_INCREMENT,
  `genre_name` varchar(255),
  `genre_created_at` timestamp,
  `genre_updated_at` timestamp
);

CREATE TABLE `country` (
  `country_id` int PRIMARY KEY AUTO_INCREMENT,
  `country_name` varchar(255),
  `country_created_at` timestamp,
  `country_updated_at` timestamp
);

CREATE TABLE `actor` (
  `actor_id` int PRIMARY KEY AUTO_INCREMENT,
  `actor_firstname` varchar(255),
  `actor_lastname` varchar(255),
  `actor_birthday` datetime,
  `actor_pk_country` int,
  `actor_average_rating` float,
  `actor_created_at` timestamp,
  `actor_updated_at` timestamp
);

CREATE TABLE `tvshow` (
  `tvshow_id` int PRIMARY KEY AUTO_INCREMENT,
  `tvshow_name_en` varchar(255),
  `tvshow_name_fr` varchar(255),
  `tvshow_imdb_id` int,
  `tvshow_rating` int,
  `tvshow_release_date` datetime,
  `tvshow_poster` varchar(255),
  `tvshow_rating_date` datetime,
  `tvshow_created_at` timestamp,
  `tvshow_updated_at` timestamp
);

CREATE TABLE `ext_genre_movie` (
  `ext_genre_movie_id` int PRIMARY KEY AUTO_INCREMENT,
  `ext_genre_movie_pk_movie` int,
  `ext_genre_movie_pk_genre` int
);

CREATE TABLE `ext_genre_tvshow` (
  `ext_genre_tvshow_id` int PRIMARY KEY AUTO_INCREMENT,
  `ext_genre_tvshow_pk_tvshow` int,
  `ext_genre_tvshow_pk_genre` int
);

CREATE TABLE `ext_actor_movie` (
  `ext_actor_movie_id` int PRIMARY KEY AUTO_INCREMENT,
  `ext_actor_movie_pk_actor` int,
  `ext_actor_movie_pk_movie` int
);

CREATE TABLE `ext_actor_tvshow` (
  `ext_actor_tvshow_id` int PRIMARY KEY AUTO_INCREMENT,
  `ext_actor_tvshow_pk_actor` int,
  `ext_actor_tvshow_pk_movie` int
);

CREATE TABLE `ext_country_movie` (
  `ext_country_movie_id` int PRIMARY KEY AUTO_INCREMENT,
  `ext_country_movie_pk_country` int,
  `ext_country_movie_pk_movie` int
);

CREATE TABLE `ext_country_tvshow` (
  `ext_country_tvshow_id` int PRIMARY KEY AUTO_INCREMENT,
  `ext_country_tvshow_pk_country` int,
  `ext_country_tvshow_pk_tvshow` int
);

ALTER TABLE `actor` ADD FOREIGN KEY (`actor_pk_country`) REFERENCES `country` (`country_id`);

ALTER TABLE `ext_genre_movie` ADD FOREIGN KEY (`ext_genre_movie_pk_movie`) REFERENCES `movie` (`movie_id`);

ALTER TABLE `ext_genre_movie` ADD FOREIGN KEY (`ext_genre_movie_pk_genre`) REFERENCES `genre` (`genre_id`);

ALTER TABLE `ext_genre_tvshow` ADD FOREIGN KEY (`ext_genre_tvshow_pk_tvshow`) REFERENCES `tvshow` (`tvshow_id`);

ALTER TABLE `ext_genre_tvshow` ADD FOREIGN KEY (`ext_genre_tvshow_pk_genre`) REFERENCES `genre` (`genre_id`);

ALTER TABLE `ext_actor_movie` ADD FOREIGN KEY (`ext_actor_movie_pk_actor`) REFERENCES `actor` (`actor_id`);

ALTER TABLE `ext_actor_movie` ADD FOREIGN KEY (`ext_actor_movie_pk_movie`) REFERENCES `movie` (`movie_id`);

ALTER TABLE `ext_actor_tvshow` ADD FOREIGN KEY (`ext_actor_tvshow_pk_actor`) REFERENCES `actor` (`actor_id`);

ALTER TABLE `ext_actor_tvshow` ADD FOREIGN KEY (`ext_actor_tvshow_pk_movie`) REFERENCES `tvshow` (`tvshow_id`);

ALTER TABLE `ext_country_movie` ADD FOREIGN KEY (`ext_country_movie_pk_country`) REFERENCES `country` (`country_id`);

ALTER TABLE `ext_country_movie` ADD FOREIGN KEY (`ext_country_movie_pk_movie`) REFERENCES `movie` (`movie_id`);

ALTER TABLE `ext_country_tvshow` ADD FOREIGN KEY (`ext_country_tvshow_pk_country`) REFERENCES `country` (`country_id`);

ALTER TABLE `ext_country_tvshow` ADD FOREIGN KEY (`ext_country_tvshow_pk_tvshow`) REFERENCES `tvshow` (`tvshow_id`);
