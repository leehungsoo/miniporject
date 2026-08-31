package bitc.aws402.miniproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // http://localhost:8080/ 및 http://localhost:8080/index 둘 다 접속 가능
    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index"; // templates/index.html 파일을 리턴
    }
}