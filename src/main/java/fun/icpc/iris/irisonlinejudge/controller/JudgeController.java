package fun.icpc.iris.irisonlinejudge.controller;


import fun.icpc.iris.irisonlinejudge.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/judge")
public class JudgeController {

    @Autowired
    private JudgeService judgeService;


    @PostMapping("/python/{code}/{input}")
    public String judgePython(@PathVariable String code, @PathVariable String input) {
        return judgeService.judgePython(code, input).message();
    }


}
