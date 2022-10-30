package appsprototyping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherFormProtected {

    @GetMapping("/other-form-protected-login")
    public String loginForm() {
        return "other-form-protected-login";
    }

    @GetMapping("/other-form-protected/index")
    public String index() {
        return "other-form-protected-index";
    }

}
