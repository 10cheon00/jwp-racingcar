package racingcar.dto;

public class RacingFormDto {
    private final String names;
    private final Integer count;

    public RacingFormDto(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return this.names;
    }

    public Integer getCount() {
        return this.count;
    }
}
