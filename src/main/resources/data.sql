-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAYER
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE RACING_GAME
(
    id         INT      NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAY_RESULT (
    id   INT NOT NULL AUTO_INCREMENT,
    player_id INT NOT NULL,
    racing_game_id INT NOT NULL,
    position INT NOT NULL default 0,
    PRIMARY KEY(id),
    FOREIGN KEY(player_id) REFERENCES PLAYER(id),
    FOREIGN KEY(racing_game_id) REFERENCES racing_game(id)
);
