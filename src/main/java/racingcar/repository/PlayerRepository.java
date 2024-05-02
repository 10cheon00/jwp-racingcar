package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.PlayerDao;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class PlayerRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlayerDao save(String name) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO PLAYER( name ) values( ? )";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, name);
            return ps;
        }, generatedKeyHolder);
        return new PlayerDao(generatedKeyHolder.getKey().intValue(), name);
    }

    public boolean isExistName(String name) {
        // todo: implement check existence query.
        String sql = "SELECT COUNT( name ) FROM PLAYER WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, name) > 0;
    }

    public PlayerDao findByName(String name) {
        // todo: implement search query.
        String sql = "SELECT * FROM PLAYER WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, PlayerDao.class, name);
    }
}
