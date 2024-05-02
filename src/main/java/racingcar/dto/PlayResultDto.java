package racingcar.dto;

import racingcar.dao.PlayResultDao;

import java.util.List;

/*
return PlayResultDto : {
  "racingCars": [
    {
      "name": "",
      "position": #
    },
  ],
  "winners": html codes?
}
*/
public class PlayResultDto {
    private final String name;
    private final Integer position;

    public PlayResultDto(String name, Integer position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
