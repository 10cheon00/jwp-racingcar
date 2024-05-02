package racingcar.dao;

public class PlayResultDao {
    private final Integer id;
    private final Integer playerId;
    private final Integer racingGameId;
    private final Integer position;

    public PlayResultDao(Integer id, Integer playerId, Integer racingGameId, Integer position) {
        this.id = id;
        this.playerId = playerId;
        this.racingGameId = racingGameId;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public Integer getRacingGameId() {
        return racingGameId;
    }

    public Integer getPosition() {
        return position;
    }
}
