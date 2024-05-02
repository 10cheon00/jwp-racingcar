package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.RacingGameDao;

import javax.sql.DataSource;
import java.sql.Statement;
import java.time.LocalDateTime;

@Repository
public class RacingGameRepository {
    private final JdbcTemplate jdbcTemplate;

    public RacingGameRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RacingGameDao save(LocalDateTime createdAt) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO RACING_GAME values ()";
        jdbcTemplate.update(con -> con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS), generatedKeyHolder);
        return new RacingGameDao((Integer) generatedKeyHolder.getKeys().get("ID"), createdAt);
    }

    public RacingGameDao findByCreatedAt(LocalDateTime createdAt) {
        String sql = "SELECT * FROM RACING_GAME WHERE created_at = ?";
        return jdbcTemplate.queryForObject(sql, RacingGameDao.class, createdAt);
    }
}
