ALTER TABLE stats
DROP CONSTRAINT fk_stats_game;
ALTER TABLE stats
DROP COLUMN game_id;

ALTER TABLE game
ADD COLUMN stats_id BIGINT UNIQUE,
ADD CONSTRAINT fk_game_stats FOREIGN KEY (stats_id) REFERENCES stats(id) ON DELETE CASCADE;