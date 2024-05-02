package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import racingcar.dao.PlayResultDao;
import racingcar.dto.PlayResultDto;
import racingcar.dto.RacingFormDto;
import racingcar.service.RacingGameService;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/plays")
public class RacingCarController {
    private final RacingGameService racingGameService;

    public RacingCarController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("")
    public String addPlayResult(Model model, @RequestBody RacingFormDto racingFormDto) {
        // todo: implementation
        List<PlayResultDto> playResultDtos = racingGameService.race(racingFormDto);

        // todo: must return path of thymeleaf template.
        model.addAttribute("playResultDtos", playResultDtos);
        return "add-result";
    }

    @GetMapping("")
    public List<PlayResultDto> getPlayResult() {
        // todo: implementation
        // return List<PlayResultDto>
        return null;
    }
}
