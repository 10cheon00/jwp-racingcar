package racingcar.dao;

public class PlayResultDao {
    private final Integer id;
    private final Integer playerId;
    private final Integer racingGameId;
    private final Integer position;
    private final Boolean isWinner;

    public PlayResultDao(Integer id, Integer playerId, Integer racingGameId, Integer position, Boolean isWinner) {
        this.id = id;
        this.playerId = playerId;
        this.racingGameId = racingGameId;
        this.position = position;
        this.isWinner = isWinner;
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

    public Boolean getWinner() {
        return isWinner;
    }

    public static class WinnersDao {
        private final Integer id;
        private final String winners;

        public WinnersDao(Integer id, String winners) {
            this.id = id;
            this.winners = winners;
        }

        public Integer getId() {
            return id;
        }

        public String getWinners() {
            return winners;
        }
    }
}
