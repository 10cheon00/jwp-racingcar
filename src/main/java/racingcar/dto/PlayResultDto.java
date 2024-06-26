package racingcar.dto;

import racingcar.dao.NestedPlayResultDao;
import racingcar.domain.RacingCar;

import java.util.List;

public class PlayResultDto {
    private final List<RacingCar> racingCars;
    private final List<String> winners;

    public PlayResultDto(List<RacingCar> racingCars, List<String> winners) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public PlayResultDto(NestedPlayResultDao nestedPlayResultDao) {
        this.racingCars = nestedPlayResultDao.getRacingCars();
        this.winners = nestedPlayResultDao.getWinners();
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }
}
