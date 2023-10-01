package fun.icpc.iris.irisonlinejudge.controller;


import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.enums.ExecCommandTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.record.RunningRequest;
import fun.icpc.iris.irisonlinejudge.domain.record.RunningResult;
import fun.icpc.iris.irisonlinejudge.service.JudgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/judge")
@RequiredArgsConstructor
public class JudgeController {

    private final JudgeService judgeService;

    @PostMapping("/run/{language}")
    public IrisMessage<RunningResult> judge(@PathVariable String language, @RequestBody RunningRequest runningRequestDTO) {
        ExecCommandTypeEnum commandTypeEnum = ExecCommandTypeEnum.fromLanguage(language);
        if(Objects.isNull(commandTypeEnum)) {
            return IrisMessageFactory.fail("unsupported language");
        }

        RunningResult runningResult = judgeService.run(runningRequestDTO.input(), runningRequestDTO.code(), commandTypeEnum);

        return IrisMessageFactory.success(runningResult);
    }
}
