package racingcar.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.NestedPlayResultDao;
import racingcar.dao.PlayResultDao;
import racingcar.domain.RacingCar;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayResultRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayResultRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlayResultDao save(Integer playerId, Integer racingGameId, Integer playerPosition, Boolean isWinner) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO PLAY_RESULT(player_id, racing_game_id, position, is_winner) values (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setInt(1, playerId);
            ps.setInt(2, racingGameId);
            ps.setInt(3, playerPosition);
            ps.setBoolean(4, isWinner);
            return ps;
        }, generatedKeyHolder);
        return new PlayResultDao(generatedKeyHolder.getKey().intValue(), playerId, racingGameId, playerPosition, isWinner);
    }

    public List<NestedPlayResultDao> findAllWithWinnerByRacingGame() {
        String sql = "SELECT r.id AS id, name, position, is_winner FROM RACING_GAME r JOIN PLAY_RESULT pr ON r.id = pr.racing_game_id JOIN PLAYER p ON pr.player_id = p.id;";
        return jdbcTemplate.query(sql,
                new ResultSetExtractor<List<NestedPlayResultDao>>() {
                    @Override
                    public List<NestedPlayResultDao> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        Map<Integer, NestedPlayResultDao> map = new HashMap<>();
                        while (rs.next()) {
                            Integer racingGameid = rs.getInt("id");
                            String name = rs.getString("name");
                            Integer position = rs.getInt("position");
                            boolean isWinner = rs.getBoolean("is_winner");

                            NestedPlayResultDao nestedPlayResultDao = map.get(racingGameid);
                            if (nestedPlayResultDao == null) {
                                nestedPlayResultDao = new NestedPlayResultDao(new ArrayList<>(), new ArrayList<>());
                                map.put(racingGameid, nestedPlayResultDao);
                            }
                            if (isWinner) {
                                nestedPlayResultDao.getWinners().add(name);
                            }
                            RacingCar racingCar = new RacingCar(name, position);
                            nestedPlayResultDao.getRacingCars().add(racingCar);
                        }

                        return new ArrayList<>(map.values());
                    }
                });

    }
}
/*


/*

SELECT * FROM PLAY_RESULT; SELECT rc.names, w.winners
FROM RACING_GAME r
JOIN
(SELECT
  GROUP_CONCAT(name) AS names, racing_game_id
  FROM PLAY_RESULT pr1
  JOIN PLAYER p ON pr1.player_id = p.id
  GROUP BY racing_game_id
  ORDER BY racing_game_id
) AS rc
ON r.id = rc.racing_game_id
JOIN
  (SELECT
    GROUP_CONCAT(CASE WHEN is_winner = true THEN p.name ELSE NULL END) AS winners, racing_game_id
    FROM PLAY_RESULT pr2
    JOIN PLAYER p ON pr2.player_id = p.id
    GROUP BY racing_game_id
    ORDER BY racing_game_id
) AS w
ON r.id = w.racing_game_id


 */
