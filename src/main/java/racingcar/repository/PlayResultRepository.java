package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.PlayResultDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class PlayResultRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayResultRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlayResultDao save(Integer playerId, Integer racingGameId, Integer playerPosition) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO PLAY_RESULT(player_id, racing_game_id, position) values (?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setInt(1, playerId);
            ps.setInt(2, racingGameId);
            ps.setInt(3, playerPosition);
            return ps;
        }, generatedKeyHolder);
        return new PlayResultDao(generatedKeyHolder.getKey().intValue(), playerId, racingGameId, playerPosition);
    }
}
