package racingcar.dao;

public class PlayerDao {
    private final Integer id;
    private final String name;

    public PlayerDao(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
}
