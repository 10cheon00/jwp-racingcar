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
    is_winner BIT NOT NULL default 0,
    PRIMARY KEY(id),
    FOREIGN KEY(player_id) REFERENCES PLAYER(id),
    FOREIGN KEY(racing_game_id) REFERENCES racing_game(id)
);

INSERT INTO PLAYER(name) values('p1');
INSERT INTO PLAYER(name) values('p2');
INSERT INTO PLAYER(name) values('p3');
INSERT INTO RACING_GAME VALUES();
INSERT INTO RACING_GAME VALUES();
INSERT INTO PLAY_RESULT(player_id, racing_game_id, position, is_winner) VALUES(1, 1, 3, 0);
INSERT INTO PLAY_RESULT(player_id, racing_game_id, position, is_winner) VALUES(2, 1, 5, 1);
INSERT INTO PLAY_RESULT(player_id, racing_game_id, position, is_winner) VALUES(1, 2, 5, 0);
INSERT INTO PLAY_RESULT(player_id, racing_game_id, position, is_winner) VALUES(2, 2, 1, 0);
INSERT INTO PLAY_RESULT(player_id, racing_game_id, position, is_winner) VALUES(3, 2, 10, 1);