CREATE TABLE player (
 id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
 name VARCHAR(255) NOT NULL
);

CREATE TABLE ranking (
 id BIGINT GENERATED ALWAYS AS IDENTITY,
 CONSTRAINT pk_ranking PRIMARY KEY(id),
 rank VARCHAR(255) NOT NULL,
 player_id BIGINT,
 CONSTRAINT fk_ranking_player FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE
);

CREATE TABLE game (
 id BIGINT GENERATED ALWAYS AS IDENTITY,
 word VARCHAR(50) NOT NULL,
 progress boolean[] NOT NULL,
 lives_remaining INT NOT NULL,
 state VARCHAR(50) NOT NULL,
 player_id BIGINT,
 CONSTRAINT pk_game PRIMARY KEY(id),
 CONSTRAINT fk_game_player FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE SET NULL
);

CREATE TABLE stats (
 id BIGINT GENERATED ALWAYS AS IDENTITY,
 lives_remaining INT NOT NULL,
 ranking_id BIGINT,
 game_id BIGINT,
 CONSTRAINT pk_stats PRIMARY KEY(id),
 CONSTRAINT fk_stats_ranking FOREIGN KEY (ranking_id) REFERENCES ranking(id),
 CONSTRAINT fk_stats_game FOREIGN KEY (game_id) REFERENCES game(id) ON DELETE CASCADE
);

CREATE TABLE input_history(
 stats_id BIGINT,
 input_history CHAR(1),
 CONSTRAINT pk_input_history PRIMARY KEY(stats_id),
 CONSTRAINT fk_input_history_stats FOREIGN KEY (stats_id) REFERENCES stats(id)
);

