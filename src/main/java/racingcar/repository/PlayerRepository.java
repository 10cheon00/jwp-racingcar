package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayerDao;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

@Repository
@Transactional
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
        String sql = "SELECT COUNT( name ) FROM PLAYER WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, name) > 0;
    }

    public PlayerDao findByName(String name) {
        String sql = "SELECT id, name FROM PLAYER WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> new PlayerDao(rs.getInt("id"), rs.getString("name")));
    }
}
