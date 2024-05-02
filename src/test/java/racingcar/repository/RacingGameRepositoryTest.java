package racingcar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingGameDao;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class RacingGameRepositoryTest {
    RacingGameRepository racingGameRepository;

    @Autowired
    public RacingGameRepositoryTest(RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    @Test
    void 저장() {
        // given
        // when
        LocalDateTime createdAt = LocalDateTime.now();
        RacingGameDao racingGameDao = racingGameRepository.save(createdAt);
        // then
        assertThat(racingGameDao).isNotNull();
        assertThat(racingGameDao.getId()).isEqualTo(1);
        assertThat(racingGameDao.getCreatedAt()).isEqualTo(createdAt);
    }
}
