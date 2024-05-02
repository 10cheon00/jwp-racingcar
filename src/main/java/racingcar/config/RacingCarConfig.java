package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.util.RandomNumberGenerationStrategy;
import racingcar.util.NumberGenerationStrategy;

@Configuration
public class RacingCarConfig {
    @Bean
    public NumberGenerationStrategy getNumberGenerationStrategy() {
        return new RandomNumberGenerationStrategy();
    }
}

