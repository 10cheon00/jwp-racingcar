package racingcar.dao;

import racingcar.domain.RacingCar;

import java.util.List;

public class NestedPlayResultDao {
    private final List<RacingCar> racingCars;
    private final List<String> winners;

    public NestedPlayResultDao(List<RacingCar> racingCars, List<String> winners) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }
}
