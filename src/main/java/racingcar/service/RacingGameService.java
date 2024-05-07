package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.NestedPlayResultDao;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.RacingCar;
import racingcar.dto.PlayResultDto;
import racingcar.dto.RacingFormDto;
import racingcar.exception.InvalidFormException;
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
    private List<String> winners;

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

    public PlayResultDto race(RacingFormDto racingFormDto) {
        try {
            parsePlayers(racingFormDto.getNames());
            run(racingFormDto.getCount());
        } catch(Exception e) {
            throw new InvalidFormException();
        }
        findWinners();
        return saveResult();
    }

    private void parsePlayers(String names) {
        playerData = new HashMap<>();
        String[] parsedNames = names.replace(" ", "").split(",");
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

    private void findWinners() {
        winners = new ArrayList<>();
        int maxPosition = 0;
        for (String name : playerData.keySet()) {
            int position = playerData.get(name);
            if (position > maxPosition) {
                winners.clear();
                winners.add(name);
                maxPosition = position;
            } else if (position == maxPosition) {
                winners.add(name);
            }
        }
    }

    private PlayResultDto saveResult() {
        System.out.println("[LOG] ::: saveResult() ::: save RACING_GAME");
        LocalDateTime createdAt = LocalDateTime.now();
        RacingGameDao racingGameDao = racingGameRepository.save(createdAt);

        List<RacingCar> racingCars = new ArrayList<>();
        for (String name : playerData.keySet()) {
            System.out.println("[LOG] ::: saveResult() ::: get or create PLAYER");
            PlayerDao playerDao = getOrCreatePlayerDao(name);

            int position = playerData.get(name);
            boolean isWinner = winners.contains(name);
            System.out.println("[LOG] ::: saveResult() ::: save PLAY_RESULT");
            PlayResultDao playResultDao = playResultRepository.save(
                    playerDao.getId(),
                    racingGameDao.getId(),
                    position,
                    isWinner);
            racingCars.add(new RacingCar(name, playResultDao.getPosition()));
        }

        return new PlayResultDto(racingCars, winners);
    }

    private PlayerDao getOrCreatePlayerDao(String name) {
        System.out.println("[LOG] ::: getOrCreatePlayerDao() ::: exist check");
        if (playerRepository.isExistName(name)) {
            System.out.println("[LOG] ::: getOrCreatePlayerDao() ::: find PLAYER");
            return playerRepository.findByName(name);
        }
        System.out.println("[LOG] ::: getOrCreatePlayerDao() ::: save PLAYER");
        return playerRepository.save(name);
    }

    public List<PlayResultDto> getAllResult() {
        System.out.println("[LOG] ::: getAllResult() ::: find All with winner");
        List<NestedPlayResultDao> nestedPlayResultDaos = playResultRepository.findAllWithWinnerByRacingGame();
        // todo : modify PlayResultDto
        // todo : convert nestedPlayResultDaos to playResult dto

        // seems to inefficient.
        List<PlayResultDto> playResultDtos = new ArrayList<>();
        for (NestedPlayResultDao nestedPlayResultDao : nestedPlayResultDaos) {
            playResultDtos.add(new PlayResultDto(nestedPlayResultDao));
        }
        return playResultDtos;
    }
}
