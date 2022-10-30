package appsprototyping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormProtected {

    @GetMapping("/form-protected-login")
    public String loginForm() {
        return "form-protected-login";
    }

    @GetMapping("/form-protected/index")
    public String index() {
        return "form-protected-index";
    }

}
