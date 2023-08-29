ALTER TABLE game
    RENAME COLUMN created_by_id TO created_by_player_id;

ALTER TABLE game
    RENAME COLUMN played_by_id TO played_by_player_id;