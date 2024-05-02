package racingcar.dao;

import java.time.LocalDateTime;

public class RacingGameDao {
    private final Integer id;
    private final LocalDateTime createdAt;

    public RacingGameDao(int id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
