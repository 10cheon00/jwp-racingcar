package racingcar.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.PlayResultDto;
import racingcar.dto.RacingFormDto;
import racingcar.service.RacingGameService;

import java.util.List;

@RestController
@RequestMapping("/plays")
public class RacingCarController {
    private final RacingGameService racingGameService;

    public RacingCarController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("")
    public PlayResultDto addPlayResult(Model model, @RequestBody RacingFormDto racingFormDto) {
        return racingGameService.race(racingFormDto);
    }

    @GetMapping("")
    public List<PlayResultDto> getPlayResult(Model model) {
        return racingGameService.getAllResult();
    }
}
