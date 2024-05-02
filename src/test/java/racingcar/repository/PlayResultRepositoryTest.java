package racingcar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingGameDao;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class PlayResultRepositoryTest {
    final PlayResultRepository playResultRepository;
    final RacingGameRepository racingGameRepository;
    final PlayerRepository playerRepository;

    @Autowired
    public PlayResultRepositoryTest(PlayResultRepository playResultRepository, RacingGameRepository racingGameRepository, PlayerRepository playerRepository) {
        this.playResultRepository = playResultRepository;
        this.racingGameRepository = racingGameRepository;
        this.playerRepository = playerRepository;
    }

    @Test
    void 저장() {
        // given
        LocalDateTime createdAt = LocalDateTime.now();
        PlayerDao p1 = playerRepository.save("p1");
        PlayerDao p2 = playerRepository.save("p2");
        RacingGameDao r1 = racingGameRepository.save(createdAt);
        // when
        PlayResultDao result1 = playResultRepository.save(p1.getId(), r1.getId(), 3);
        PlayResultDao result2 = playResultRepository.save(p2.getId(), r1.getId(), 5);
        // then
        assertThat(result1.getRacingGameId()).isEqualTo(result2.getRacingGameId());
        assertThat(result1.getPlayerId()).isEqualTo(p1.getId());
        assertThat(result2.getPlayerId()).isEqualTo(p2.getId());
    }
}
