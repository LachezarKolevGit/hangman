ALTER TABLE game
RENAME COLUMN created_by TO created_by_player_id;

ALTER TABLE game
RENAME COLUMN played_by TO played_by_player_id;