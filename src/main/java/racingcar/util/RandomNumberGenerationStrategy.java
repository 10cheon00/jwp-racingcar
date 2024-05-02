package racingcar.util;

import racingcar.util.NumberGenerationStrategy;

import java.util.Random;

public class RandomNumberGenerationStrategy implements NumberGenerationStrategy {
    private final Random random;

    public RandomNumberGenerationStrategy() {
        random = new Random();
    }

    public int getNumber() {
        return random.nextInt(10);
    }
}
