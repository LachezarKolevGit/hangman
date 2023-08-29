DELETE FROM player WHERE player.id = 3;

ALTER TABLE player
ADD CONSTRAINT unique_player_name UNIQUE (name);