package racingcar.domain;

public class RacingCar {
    private final String name;
    private final Integer position;

    public RacingCar(String name, Integer position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
