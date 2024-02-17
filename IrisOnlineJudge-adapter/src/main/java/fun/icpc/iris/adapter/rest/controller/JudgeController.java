package fun.icpc.iris.adapter.rest.controller;


import fun.icpc.iris.application.command.RunningRequest;
import fun.icpc.iris.application.command.RunningResult;
import fun.icpc.iris.application.service.applicationservice.JudgeService;
import fun.icpc.iris.sharedkernel.enums.ExecCommandTypeEnum;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
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
