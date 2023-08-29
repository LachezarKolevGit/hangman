ALTER TABLE stats
ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT NOW();

ALTER TABLE game
RENAME COLUMN player_id TO created_by;

ALTER TABLE game
ADD COLUMN played_by BIGINT;

ALTER TABLE game ADD CONSTRAINT fk_playedBy_game FOREIGN KEY (played_by) REFERENCES player(id);

