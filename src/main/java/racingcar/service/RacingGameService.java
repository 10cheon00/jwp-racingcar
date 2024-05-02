package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingGameDao;
import racingcar.dto.PlayResultDto;
import racingcar.dto.RacingFormDto;
import racingcar.repository.PlayResultRepository;
import racingcar.repository.PlayerRepository;
import racingcar.repository.RacingGameRepository;
import racingcar.util.NumberGenerationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RacingGameService {
    private final PlayerRepository playerRepository;
    private final RacingGameRepository racingGameRepository;
    private final PlayResultRepository playResultRepository;

    private final NumberGenerationStrategy numberGenerationStrategy;
    private HashMap<String, Integer> playerData;

    @Autowired
    public RacingGameService(
            PlayerRepository playerRepository,
            RacingGameRepository racingGameRepository,
            PlayResultRepository playResultRepository,
            NumberGenerationStrategy numberGenerationStrategy) {
        this.playerRepository = playerRepository;
        this.racingGameRepository = racingGameRepository;
        this.playResultRepository = playResultRepository;
        this.numberGenerationStrategy = numberGenerationStrategy;
    }

    public List<PlayResultDto> race(RacingFormDto racingFormDto) {
        parsePlayers(racingFormDto.getNames());
        run(racingFormDto.getCount());
        return saveResult();
    }

    private void parsePlayers(String names) {
        playerData = new HashMap<>();
        String[] parsedNames = names.split(",");
        for (String parsedName : parsedNames) {
            playerData.put(parsedName, 0);
        }
    }

    private void run(final int count) {
        for (int i = 0; i < count; i++) {
            moveAllCar();
        }
    }

    private void moveAllCar() {
        for (String key : playerData.keySet()) {
            int number = numberGenerationStrategy.getNumber();
            moveCarByValue(key, number);
        }
    }

    private void moveCarByValue(String key, int value) {
        if (value > 4) {
            playerData.put(key, playerData.get(key) + 1);
        }
    }

    private List<PlayResultDto> saveResult() {
        LocalDateTime createdAt = LocalDateTime.now();
        RacingGameDao racingGameDao = racingGameRepository.save(createdAt);

        List<PlayResultDto> playResultDtos = new ArrayList<>();
        for (String name : playerData.keySet()) {
            PlayerDao playerDao = getOrCreatePlayerDao(name);

            int position = playerData.get(name);
            PlayResultDao playResultDao = playResultRepository.save(
                    playerDao.getId(),
                    racingGameDao.getId(),
                    position);
            playResultDtos.add(new PlayResultDto(name, playResultDao.getPosition()));
        }

        return playResultDtos;
    }

    private PlayerDao getOrCreatePlayerDao(String name) {
        if (playerRepository.isExistName(name)) {
            return playerRepository.findByName(name);
        }
        return playerRepository.save(name);
    }
}
